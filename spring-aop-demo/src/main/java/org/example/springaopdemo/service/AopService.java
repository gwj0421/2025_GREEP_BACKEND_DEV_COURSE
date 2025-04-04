package org.example.springaopdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springaopdemo.logging.Logging;
import org.example.springaopdemo.repository.AopRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AopService {
    private final AopRepository aopRepository;

    @Logging
    public void logic() {
        log.info("*** aop service start ***");
        log.info("*** aop service end ***");
    }
}
