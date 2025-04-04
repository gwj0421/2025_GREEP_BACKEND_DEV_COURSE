package org.example.jpa.nPlus1;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ThrowableAssert;
import org.example.jpa.QueryCountInspector;
import org.example.jpa.TestConfig;
import org.example.jpa.mapping.simplex.n_to_one.eager.Player;
import org.example.jpa.mapping.simplex.n_to_one.eager.PlayerRepository;
import org.example.jpa.mapping.simplex.n_to_one.eager.Team;
import org.example.jpa.mapping.simplex.n_to_one.lazy.Caffeine;
import org.example.jpa.mapping.simplex.n_to_one.lazy.CaffeineRepository;
import org.example.jpa.mapping.simplex.n_to_one.lazy.Coffee;
import org.example.jpa.mapping.simplex.one_to_n.apply_batch_size.Crew;
import org.example.jpa.mapping.simplex.one_to_n.apply_batch_size.CrewRepository;
import org.example.jpa.mapping.simplex.one_to_n.apply_batch_size.Ship;
import org.example.jpa.mapping.simplex.one_to_n.apply_batch_size.ShipRepository;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.list_to_set.A1;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.list_to_set.A1Repository;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.list_to_set.B1;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.list_to_set.C1;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.problem.A;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.problem.ARepository;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.problem.B;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.problem.C;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.use_batch_size.A2;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.use_batch_size.A2Repository;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.use_batch_size.B2;
import org.example.jpa.mapping.simplex.one_to_n.multiple_bag.use_batch_size.C2;
import org.example.jpa.mapping.simplex.one_to_n.without_middle_table.*;
import org.hibernate.loader.MultipleBagFetchException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
@Import(TestConfig.class)
class NPlus1Test {
    @Autowired
    private EntityManager entityManager;

    @AfterEach
    void tearDown() {
        QueryCountInspector.resetQueryCount();
    }

    @Nested
    class Should_nPlus1 {
        @Test
        void when_EagerManyToOne(@Autowired PlayerRepository playerRepository) {
            // given
            for (int i = 0; i < 3; i++) {
                Team team = new Team("t");
                for (int j = 0; j < 2; j++) {
                    playerRepository.save(new Player("p" + j, team));
                }
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            playerRepository.findAll();
            int queryCnt = QueryCountInspector.getQueryCount();

            // then
            assertThat(queryCnt).isEqualTo(1 + 3);
        }

        @Test
        void when_LazyManyToOne(@Autowired CaffeineRepository caffeineRepository) {
            // given
            for (int i = 0; i < 3; i++) {
                Coffee coffee = new Coffee("t");
                for (int j = 0; j < 2; j++) {
                    caffeineRepository.save(new Caffeine("p" + j, coffee));
                }
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            caffeineRepository.findAll().stream().map(Caffeine::getCoffee).map(Coffee::getName).collect(Collectors.toList());
            int queryCnt = QueryCountInspector.getQueryCount();

            // then
            assertThat(queryCnt).isEqualTo(1 + 3);
        }

        @Test
        void when_OneToMany(@Autowired NationRepository nationRepository) {
            // given
            for (int i = 0; i < 3; i++) {
                Nation nation = new Nation("n" + i);
                for (int j = 0; j < 2; j++) {
                    nation.getRegions().add(new Region("r" + j));
                }
                nationRepository.save(nation);
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            List<Nation> nations = nationRepository.findAll();
            nations.stream().map(Nation::getRegions)
                    .flatMap(it -> it.stream().map(Region::getName))
                    .collect(Collectors.toList());

            int queryCnt = QueryCountInspector.getQueryCount();

            // then
            assertThat(queryCnt).isEqualTo(1 + nations.size());
        }
    }

    @Nested
    class ShouldSolve_nPlus1 {
        @Autowired
        private NationRepository nationRepository;

        @Test
        void when_useFetchJoin(@Autowired NationRepository nationRepository) {
            // given
            for (int i = 0; i < 3; i++) {
                Nation nation = new Nation("n" + i);
                for (int j = 0; j < 2; j++) {
                    nation.getRegions().add(new Region("r" + j));
                }
                nationRepository.save(nation);
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            List<Nation> nations = nationRepository.findAllWithFetchJoin();
            nations.stream().map(Nation::getRegions)
                    .flatMap(it -> it.stream().map(Region::getName))
                    .collect(Collectors.toList());
            int queryCnt = QueryCountInspector.getQueryCount();

            // then
            assertThat(queryCnt).isEqualTo(1);
        }

        @Test
        void when_useEntityGraph(@Autowired NationRepository nationRepository) {
            // given
            for (int i = 0; i < 3; i++) {
                Nation nation = new Nation("n" + i);
                for (int j = 0; j < 2; j++) {
                    nation.getRegions().add(new Region("r" + j));
                }
                nationRepository.save(nation);
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            List<Nation> nations = nationRepository.findAllWithEntityGraph();
            nations.stream().map(Nation::getRegions)
                    .flatMap(it -> it.stream().map(Region::getName))
                    .collect(Collectors.toList());
            int queryCnt = QueryCountInspector.getQueryCount();

            // then
            assertThat(queryCnt).isEqualTo(1);
        }

        @Test
        void when_useBatchSize(@Autowired ShipRepository shipRepository) {
            // given
            for (int i = 0; i < 30; i++) {
                Ship ship = new Ship("s" + i);
                for (int j = 0; j < 3; j++) {
                    ship.getCrews().add(new Crew("c" + j, ship));
                }
                shipRepository.save(ship);
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            List<Ship> ships = shipRepository.findAllWithBatch();
            ships.forEach(it -> it.getCrews().size());

            int queryCnt = QueryCountInspector.getQueryCount();

            // then
            assertThat(queryCnt).isEqualTo(1 + 3);
        }

    }

    @Nested
    class disadvantage_fetchJoin {
        @Test
        void 중복_발생_및_해결(@Autowired NationRepository nationRepository, @Autowired RegionRepository regionRepository) {
            // given
            for (int i = 0; i < 30; i++) {
                Nation nation = new Nation("n" + i);
                nationRepository.save(nation);
                for (int j = 0; j < 2; j++) {
                    nation.getRegions().add(new Region(nation.getId() + " " + j));
                }
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            // 문제 발생 : fetch join시 nation 쪽에서 중복 데이터 발생
            double timeFetchJoin = measureExecutionTime(() -> nationRepository.findAllWithFetchJoin());

//            List<Nation> nationsWithFetchJoin = nationRepository.findAllWithFetchJoin();

            // 해결책 1 : distinct를 사용하여 중복 제거 , 하지만 region 쪽에서 중복 발생 가능성 있음
//            List<Nation> nationsWithDistinctFetchJoin = nationRepository.findAllWithDistinctFetchJoin();
            double timeDistinctFetchJoin = measureExecutionTime(() -> nationRepository.findAllWithDistinctFetchJoin());

            // 해결책 2 : DTO를 활용하여 중복 제거
            double timeDto = measureExecutionTime(() -> {
                List<Long> nationIds = nationRepository.findAllNationIds();
                List<Region> regions = regionRepository.findRegionsByNationsIds(nationIds);

                Map<Long, List<RegionDto>> regionMap = regions.stream()
                        .map(region -> new RegionDto(region.getRegionId(), region.getNationId(), region.getName()))
                        .collect(Collectors.groupingBy(RegionDto::getNationId));
                List<NationDto> nations = nationIds.stream()
                        .map(nationId -> new NationDto(nationId, regionMap.get(nationId)))
                        .collect(Collectors.toList());
            });
            flushAndClear();

            log.info(timeFetchJoin + " ");
            log.info(timeDistinctFetchJoin + " ");
            log.info(timeDto + " ");
            System.out.println();
        }

        /***
         * 2025-04-03T22:54:22.700+09:00  WARN 78426 --- [    Test worker] org.hibernate.orm.query                  : HHH90003004: firstResult/maxResults specified with collection fetch; applying in memory
         * Hibernate: select n1_0.id,n1_0.name,r1_0.nation_id,r1_0.region_id,r1_0.name from nation n1_0 join region r1_0 on n1_0.id=r1_0.nation_id order by n1_0.name
         * Hibernate: select count(n1_0.id) from nation n1_0 join region r1_0 on n1_0.id=r1_0.nation_id
         * 2025-04-03T22:54:22.819+09:00  WARN 78426 --- [    Test worker] org.hibernate.orm.query                  : HHH90003004: firstResult/maxResults specified with collection fetch; applying in memory
         * Hibernate: select n1_0.id,n1_0.name,r1_0.nation_id,r1_0.region_id,r1_0.name from nation n1_0 left join region r1_0 on n1_0.id=r1_0.nation_id order by n1_0.name
         * Hibernate: select count(n1_0.id) from nation n1_0
         * @param nationRepository
         */
        @Test
        void 페이징_불가능(@Autowired NationRepository nationRepository) {
            // given
            for (int i = 0; i < 30; i++) {
                Nation nation = new Nation("n" + i);
                for (int j = 0; j < 2; j++) {
                    nation.getRegions().add(new Region("r" + j));
                }
                nationRepository.save(nation);
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            nationRepository.findAllWithFetchJoin(PageRequest.of(0, 3, Sort.by("name").ascending()));
            List<String> queriesAppliedFetchJoin = QueryCountInspector.getExecutedQueries();

            QueryCountInspector.resetQueryCount();
            nationRepository.findAllWithEntityGraph(PageRequest.of(0, 3, Sort.by("name").ascending()));
            List<String> queriesAppliedEntityGraph = QueryCountInspector.getExecutedQueries();

            // then
            assertThat(queriesAppliedFetchJoin).hasSize(2).first().matches(query -> !query.toLowerCase().contains("limit"), "fetch join을 사용하면 limit를 사용하지 않기 때문에 전체 데이터를 다 가져옴");
            assertThat(queriesAppliedEntityGraph).hasSize(2).first().matches(query -> !query.toLowerCase().contains("limit"), "EntityGraph를 사용하면 limit를 사용하지 않기 때문에 전체 데이터를 다 가져옴");
        }

        @Test
        void 페이징_해결(@Autowired ShipRepository shipRepository, @Autowired CrewRepository crewRepository) {
            // given
            for (int i = 0; i < 30; i++) {
                Ship ship = new Ship("s" + i);
                for (int j = 0; j < 3; j++) {
                    ship.getCrews().add(new Crew("c" + j, ship));
                }
                shipRepository.save(ship);
            }

            // when
            flushAndClear();
            QueryCountInspector.resetQueryCount();
            // 해결책 1 : ~ToOne 에서만 사용
            crewRepository.findAll(PageRequest.of(0, 3, Sort.by("name").ascending()));
            List<String> queriesWhenUseManyToOne = QueryCountInspector.getExecutedQueries();
            // 해결책 2 : @BatchSize 사용 혹은 batchSize 설정
            shipRepository.findAll(PageRequest.of(0, 3, Sort.by("name").ascending()));
            List<String> queriesWhenUseBatchSize = QueryCountInspector.getExecutedQueries();

        }

        @Nested
        class MultipleBagFetchExceptionSituation {
            @Test
            void situation(@Autowired ARepository aRepository) {
                // given
                A a = new A();
                for (int i = 0; i < 3; i++) {
                    a.getBs().add(new B());
                    a.getCs().add(new C());
                }
                aRepository.save(a);

                // when
                ThrowableAssert.ThrowingCallable fetchJoin = () -> aRepository.findAllFetchJoin();

                // then
                assertThatThrownBy(fetchJoin).isInstanceOf(InvalidDataAccessApiUsageException.class)
                        .hasRootCauseInstanceOf(MultipleBagFetchException.class);
            }

            @Test
            void solve1_List_to_Set(@Autowired A1Repository a1Repository) {
                // given
                A1 a1 = new A1();
                for (int i = 0; i < 3; i++) {
                    a1.getB1s().add(new B1());
                    a1.getC1s().add(new C1());
                }
                a1Repository.save(a1);

                // when
                ThrowableAssert.ThrowingCallable fetchJoin = () -> a1Repository.findAllFetchJoin();

                // then
                checkNotOccurException(fetchJoin);
            }

            @Test
            void solve2_useOrderColumn(@Autowired A2Repository a2Repository) {
                // given
                A2 a = new A2();
                for (int i = 0; i < 3; i++) {
                    a.getBs().add(new B2());
                    a.getCs().add(new C2());
                }
                a2Repository.save(a);

                // when
                ThrowableAssert.ThrowingCallable fetchJoin = () -> a2Repository.findAllFetchJoin();

                // then
                checkNotOccurException(fetchJoin);
            }

            private void checkNotOccurException(ThrowableAssert.ThrowingCallable code) {
                assertThatCode(code).doesNotThrowAnyException();
            }
        }
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    public static double measureExecutionTime(Runnable task) {
        long startTime = System.nanoTime(); // 시작 시간 측정
        task.run(); // 실행할 코드 실행
        long endTime = System.nanoTime(); // 종료 시간 측정

        return (endTime - startTime) / 1_000_000.0; // 밀리초 변환
    }

}