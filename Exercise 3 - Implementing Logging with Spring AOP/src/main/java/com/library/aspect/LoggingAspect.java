package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

    /**
     * Around advice that logs the execution time of every method
     * in the com.library.service package.
     */
    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        String className  = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println("[LoggingAspect] START -> " + className + "." + methodName + "()");
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();  // Execute the actual method

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("[LoggingAspect] END   -> " + className + "." + methodName
                + "() | Execution Time: " + executionTime + " ms");

        return result;
    }
}
