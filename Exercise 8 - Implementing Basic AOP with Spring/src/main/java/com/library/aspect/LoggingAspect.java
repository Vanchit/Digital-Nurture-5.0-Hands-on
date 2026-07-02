package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    /**
     * Before advice - logs before any method in com.library.service executes.
     */
    @Before("execution(* com.library.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className  = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("[AOP - Before] -> " + className + "." + methodName + "() is about to execute.");
    }

    /**
     * After advice - logs after any method in com.library.service completes.
     */
    @After("execution(* com.library.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className  = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("[AOP - After]  -> " + className + "." + methodName + "() has finished execution.");
    }
}
