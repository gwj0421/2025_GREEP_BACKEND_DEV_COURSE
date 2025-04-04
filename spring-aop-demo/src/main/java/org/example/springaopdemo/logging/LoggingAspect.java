package org.example.springaopdemo.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
//    @Around("execution(public void org.example.springaopdemo.service.AopService.logic())")
//    @Around("execution(public * *.example.springaopdemo.service.AopService.logic())")
    @Around("@annotation(org.example.springaopdemo.logging.Logging)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("*** 횡단 관심사 로깅 start ***");
        Object result = joinPoint.proceed();
        log.info("*** 횡단 관심사 로깅 end ***");
        return result;
    }
}
