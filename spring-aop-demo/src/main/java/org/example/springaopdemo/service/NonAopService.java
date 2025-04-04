package org.example.springaopdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springaopdemo.repository.AopRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NonAopService {
    private final AopRepository aopRepository;

    public void logic() {
        log.info("*** non aop service start ***");
        log.info("*** non aop service end ***");
    }

    public void specialLogic() {

    }
}
