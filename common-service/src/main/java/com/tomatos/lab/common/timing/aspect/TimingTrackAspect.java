package com.tomatos.lab.common.timing.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class TimingTrackAspect {

    @Around("@annotation(com.tomatos.lab.common.timing.annotation.TimingTrack)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.nanoTime();
            long elapsed = (endTime - startTime) / 1_000_000;
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            log.info("[{}.{}] elapsed={}ms", className, methodName, elapsed);
        }
    }
}
