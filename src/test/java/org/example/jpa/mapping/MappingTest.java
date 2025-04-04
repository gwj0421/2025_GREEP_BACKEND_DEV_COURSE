package org.example.jpa.mapping;

import jakarta.persistence.EntityManager;
import org.example.jpa.QueryCountInspector;
import org.example.jpa.TestConfig;
import org.example.jpa.mapping.duplex.Account;
import org.example.jpa.mapping.duplex.Bank;
import org.example.jpa.mapping.duplex.BankRepository;
import org.example.jpa.mapping.simplex.n_to_one.eager.Player;
import org.example.jpa.mapping.simplex.n_to_one.eager.PlayerRepository;
import org.example.jpa.mapping.simplex.n_to_one.eager.Team;
import org.example.jpa.mapping.simplex.one_to_n.with_middle_table.Classroom;
import org.example.jpa.mapping.simplex.one_to_n.with_middle_table.ClassroomRepository;
import org.example.jpa.mapping.simplex.one_to_n.with_middle_table.Student;
import org.example.jpa.mapping.simplex.one_to_n.without_middle_table.Nation;
import org.example.jpa.mapping.simplex.one_to_n.without_middle_table.NationRepository;
import org.example.jpa.mapping.simplex.one_to_n.without_middle_table.Region;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class MappingTest {
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
     * @param playerRepository
     */
    @Test
    void simplex_ManyToOne(@Autowired PlayerRepository playerRepository) {
        // given
        Team team = new Team("t");

        Player p1 = new Player("p1", team);
        Player p2 = new Player("p2", team);

        // when
        playerRepository.saveAll(List.of(p1, p2));
        int queryCnt = QueryCountInspector.getQueryCount();

        // then
        assertThat(queryCnt).isEqualTo(3);
    }

    /***
     * Hibernate: insert into classroom (name) values (?)
     * Hibernate: insert into classroom (name) values (?)
     * Hibernate: insert into classroom (name) values (?)
     * Hibernate: insert into student (name) values (?)
     * Hibernate: insert into student (name) values (?)
     * Hibernate: insert into student (name) values (?)
     * Hibernate: insert into student (name) values (?)
     * Hibernate: insert into student (name) values (?)
     * Hibernate: insert into student (name) values (?)
     * Hibernate: insert into classroom_students (classroom_id,students_id) values (?,?)
     * Hibernate: insert into classroom_students (classroom_id,students_id) values (?,?)
     * Hibernate: insert into classroom_students (classroom_id,students_id) values (?,?)
     * Hibernate: insert into classroom_students (classroom_id,students_id) values (?,?)
     * Hibernate: insert into classroom_students (classroom_id,students_id) values (?,?)
     * Hibernate: insert into classroom_students (classroom_id,students_id) values (?,?)
     * @param classroomRepository
     */
    @Test
    void simplex_withMiddleTable_OneToMany(@Autowired ClassroomRepository classroomRepository) {
        // given
        for (int i = 0; i < 3; i++) {
            Classroom classroom = new Classroom("c");
            classroomRepository.save(classroom);
            for (int j = 0; j < 2; j++) {
                classroom.getStudents().add(new Student("s" + j));
            }
        }

        // when
        flushAndClear();
        int queryCnt = QueryCountInspector.getQueryCount();

        // then
        assertThat(queryCnt).isEqualTo(3 + 3 * 2 + 3 * 2);
    }

    /***
     * Hibernate: insert into nation (name) values (?)
     * Hibernate: insert into region (name,nation_id) values (?,?)
     * Hibernate: insert into region (name,nation_id) values (?,?)
     * Hibernate: insert into nation (name) values (?)
     * Hibernate: insert into region (name,nation_id) values (?,?)
     * Hibernate: insert into region (name,nation_id) values (?,?)
     * Hibernate: insert into nation (name) values (?)
     * Hibernate: insert into region (name,nation_id) values (?,?)
     * Hibernate: insert into region (name,nation_id) values (?,?)
     * Hibernate: update region set nation_id=? where region_id=?
     * Hibernate: update region set nation_id=? where region_id=?
     * Hibernate: update region set nation_id=? where region_id=?
     * Hibernate: update region set nation_id=? where region_id=?
     * Hibernate: update region set nation_id=? where region_id=?
     * Hibernate: update region set nation_id=? where region_id=?
     * @param nationRepository
     */
    @Test
    void simplex_withoutMiddleTable_OneToMany(@Autowired NationRepository nationRepository) {
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
        int queryCnt = QueryCountInspector.getQueryCount();

        // then
        assertThat(queryCnt).isEqualTo(3 + 3 * 2 + 3 * 2);
    }


    /***
     * Hibernate: insert into bank (name) values (?)
     * Hibernate: insert into account (bank_id,name) values (?,?)
     * Hibernate: insert into account (bank_id,name) values (?,?)
     * Hibernate: insert into bank (name) values (?)
     * Hibernate: insert into account (bank_id,name) values (?,?)
     * Hibernate: insert into account (bank_id,name) values (?,?)
     * Hibernate: insert into bank (name) values (?)
     * Hibernate: insert into account (bank_id,name) values (?,?)
     * Hibernate: insert into account (bank_id,name) values (?,?)
     * @param bankRepository
     */
    @Test
    void duplex_OneToMany(@Autowired BankRepository bankRepository) {
        // given
        for (int i = 0; i < 3; i++) {
            Bank bank = new Bank("b" + i);
            for (int j = 0; j < 2; j++) {
                bank.getAccounts().add(new Account("a" + j));
            }
            bankRepository.save(bank);
        }

        // when
        flushAndClear();
        int queryCnt = QueryCountInspector.getQueryCount();

        // then
        assertThat(queryCnt).isEqualTo(3 * (1 + 2));
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}