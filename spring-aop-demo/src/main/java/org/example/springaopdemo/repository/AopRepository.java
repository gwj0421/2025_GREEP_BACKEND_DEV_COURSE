package org.example.springaopdemo.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AopRepository {
    public void logic() {
        log.info("*** aop repository start ***");
        log.info("*** aop repository end ***");
    }
}
