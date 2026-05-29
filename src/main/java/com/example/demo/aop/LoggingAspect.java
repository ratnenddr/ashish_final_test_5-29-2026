package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around(
            "execution(* com.example.demo.service..*(..)) || " +
                    "execution(* com.example.demo.controller..*(..))"
    )
    public Object logMethodExecution(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        String className =
                joinPoint.getTarget()
                        .getClass()
                        .getSimpleName();

        String methodName =
                joinPoint.getSignature()
                        .getName();

        Object[] args = joinPoint.getArgs();

        log.info(
                "METHOD STARTED -> {}.{} with arguments {}",
                className,
                methodName,
                Arrays.toString(args)
        );

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info(
                "METHOD COMPLETED -> {}.{} executed in {} ms",
                className,
                methodName,
                (endTime - startTime)
        );

        return result;
    }
}
