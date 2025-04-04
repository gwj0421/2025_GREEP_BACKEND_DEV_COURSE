package org.example.jpa.mapping;

import jakarta.persistence.EntityManager;
import org.example.jpa.QueryCountInspector;
import org.example.jpa.TestConfig;
import org.example.jpa.mapping.simplex.n_to_one.eager.Player;
import org.example.jpa.mapping.simplex.n_to_one.eager.PlayerDto;
import org.example.jpa.mapping.simplex.n_to_one.eager.PlayerRepository;
import org.example.jpa.mapping.simplex.n_to_one.eager.Team;
import org.example.jpa.mapping.simplex.one_to_n.projection.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class RepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @AfterEach
    void tearDown() {
        QueryCountInspector.resetQueryCount();
    }

    /***
     * Hibernate: insert into team (name) values (?)
     * Hibernate: insert into player (name,team_id) values (?,?)
     * Hibernate: insert into player (name,team_id) values (?,?)
     * Hibernate: insert into player (name,team_id) values (?,?)
     * Hibernate: select p1_0.id,p1_0.name,p1_0.team_id from player p1_0 left join team t1_0 on t1_0.id=p1_0.team_id where t1_0.id=?
     * @param playerRepository
     */
    @Test
    void should_join_when_repositoryMethod(@Autowired PlayerRepository playerRepository) {
        // given
        Team t = new Team("t");
        for (int i = 0; i < 3; i++) {
            playerRepository.save(new Player("p" + i, t));
        }

        // when
        QueryCountInspector.resetQueryCount();
        playerRepository.findPlayersByTeam_Id(t.getId());

        // then
        assertThat(QueryCountInspector.getExecutedQueries()).first().matches(sql -> sql.contains("join"));
    }

    /***
     * Hibernate: insert into team (name) values (?)
     * Hibernate: insert into player (name,team_id) values (?,?)
     * Hibernate: insert into player (name,team_id) values (?,?)
     * Hibernate: insert into player (name,team_id) values (?,?)
     * Hibernate: insert into player (name,team_id) values (?,?)
     * Hibernate: insert into player (name,team_id) values (?,?)
     * Hibernate: select p1_0.id,p1_0.name,p1_0.team_id from player p1_0 where p1_0.name=? order by p1_0.id limit ?
     * Hibernate: select count(p1_0.id) from player p1_0 where p1_0.name=?
     * Hibernate: select p1_0.id,p1_0.name,p1_0.team_id from player p1_0 where p1_0.name=? order by p1_0.id limit ?
     *
     * public interface Page<T> extends Slice<T> {
     *     int getTotalPages();
     *     long getTotalElements();
     * }
     *
     * public interface Slice<T> extends Streamable<T> {
     *     int getNumber();
     *     int getSize();
     *     int getNumberOfElements();
     *     List<T> getContent();
     *     ...
     * }
     *
     * @param playerRepository
     */
    @Test
    void compare_PageAndSlice(@Autowired PlayerRepository playerRepository) {
        // given
        Team t = new Team("t");
        for (int i = 0; i < 5; i++) {
            playerRepository.save(new Player("홍길동", t));
        }

        // when
        QueryCountInspector.resetQueryCount();
        playerRepository.getPlayersByName("홍길동", PageRequest.of(0, 3, Sort.by("id").ascending()));
        int queryCntUseGet = QueryCountInspector.getQueryCount();

        QueryCountInspector.resetQueryCount();
        playerRepository.readPlayersByName("홍길동", PageRequest.of(0, 3, Sort.by("id").ascending()));
        int queryCntUseRead = QueryCountInspector.getQueryCount();

        assertThat(queryCntUseGet).isEqualTo(2);
        assertThat(queryCntUseRead).isEqualTo(1);
    }

    @Nested
    class Projection {
        /***
         * Hibernate: insert into team (name) values (?)
         * Hibernate: insert into player (name,team_id) values (?,?)
         * Hibernate: insert into player (name,team_id) values (?,?)
         * Hibernate: insert into player (name,team_id) values (?,?)
         * Hibernate: insert into player (name,team_id) values (?,?)
         * Hibernate: insert into player (name,team_id) values (?,?)
         * Hibernate: select p1_0.name from player p1_0 where p1_0.name=?
         * @param playerRepository
         */
        @Test
        void useDTO(@Autowired PlayerRepository playerRepository) {
            // given
            Team t = new Team("t");
            for (int i = 0; i < 5; i++) {
                playerRepository.save(new Player("홍길동", t));
            }

            // when
            QueryCountInspector.resetQueryCount();
            Collection<PlayerDto> players = playerRepository.findPlayersByName("홍길동");

            // then
            assertThat(players).hasSize(5);
        }

        @Test
        void useInterface(@Autowired CompanyRepository companyRepository, @Autowired DepartmentRepository departmentRepository, @Autowired EmployeeRepository employeeRepository, @Autowired PetRepository petRepository) {
            // given
            for (int i = 0; i < 3; i++) {
                Company company = new Company("samsung");
                for (int j = 0; j < 5; j++) {
                    Department department = new Department("d " + j, company);
                    for (int k = 0; k < 3; k++) {
                        Employee employee = new Employee("e" + k, department);
                        for (int l = 0; l < 2; l++) {
                            employee.getPets().add(new Pet("dog " + l, employee));
                        }
                        employeeRepository.save(employee);
                    }
                    departmentRepository.save(department);
                }
                companyRepository.save(company);
            }
            entityManager.flush();

            // when
            List<CompanyProjection> companyProjections = companyRepository.findAllProjection();
            List<DepartmentProjection> departmentProjections = departmentRepository.findAllProjection();
            List<EmployeeProjection> employeeProjections = employeeRepository.findAllProjection();
            List<PetProjection> petProjections = petRepository.findAllProjection();

            // then
            assertThat(companyProjections).hasSize(3);
            assertThat(departmentProjections).hasSize(3 * 5);
            for (DepartmentProjection departmentProjection : departmentProjections) {
                assertThat(departmentProjection.getCompany().getName()).isEqualTo("samsung");
            }
            assertThat(employeeProjections).hasSize(3 * 5 * 3);
            for (EmployeeProjection employeeProjection : employeeProjections) {
                assertThat(employeeProjection.getDepartment().getCompany().getName()).isEqualTo("samsung");
                assertThat(employeeProjection.getPets()).hasSize(2);
            }
            assertThat(petProjections).hasSize(3 * 5 * 3 * 2);
            for (PetProjection petProjection : petProjections) {
                assertThat(petProjection.getEmployee().getName()).startsWith("e");
            }
        }
    }
}
