package com.example;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAOP {

    private static final Logger logger = LoggerFactory.getLogger(TestAOP.class);

    @Around("execution(* com.example.SampleController.*(..))")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        // Log request details
        logger.info("Request to method: {}", joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("Request argument: {}", arg);
        }

        // Proceed with method execution
        Object result = joinPoint.proceed();

        // Log response details
        logger.info("Response from method: {}", joinPoint.getSignature().toShortString());
        logger.info("Response: {}", result);

        return result;
    }
}
