package org.example.jpa;

import jakarta.persistence.EntityManager;
import org.example.jpa.transactional.v0.MemberV0;
import org.example.jpa.transactional.v0.MemberV0Repository;
import org.example.jpa.transactional.v0.MemberV0Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class JpaApplicationTests {
    @Nested
    class V0Test {
        @Autowired
        private MemberV0Service memberV0Service;
        @Autowired
        private MemberV0Repository memberV0Repository;
        @Autowired
        private EntityManager entityManager;

        @BeforeEach
        void setUp() {
            memberV0Repository.deleteAll();
        }

        @Test
        void should_rollback_when_transactional() {
            assertThatThrownBy(() -> memberV0Service.withTransactional()).isInstanceOf(DataIntegrityViolationException.class);
            entityManager.clear();
            List<MemberV0> all = memberV0Repository.findAll();
            assertThat(all).isEmpty();
        }

        @Test
        void should_notRollback_when_withoutTransactional() {
            assertThatThrownBy(() -> memberV0Service.withoutTransactional()).isInstanceOf(DataIntegrityViolationException.class);
            entityManager.clear();
            List<MemberV0> all = memberV0Repository.findAll();
            assertThat(all).hasSize(1);
        }
    }

}
