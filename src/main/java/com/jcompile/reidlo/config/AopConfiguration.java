//package com.jcompile.reidlo.config;
//
//import lombok.extern.log4j.Log4j2;
//import org.junit.Before;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
//@Log4j2
//@Aspect
//@Component
//public class AopConfiguration {
//
//    @Pointcut("execution(* com.jcompile.reidlo.*.controller..*.*(..))")
//    private void cut() {}
//
//    @Before("cut()")
//    public void before(JoinPoint joinPoint) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        log.info("AOP BEFORE Method Name : " + method.getName());
//
//        Object[] args = joinPoint.getArgs();
//
//        for (Object obj : args) {
//            log.info("AOP BEFORE Type : " + obj.getClass().getSimpleName());
//            log.info("AOP BEFORE Value : " + obj);
//        }
//    }
//
//    @AfterReturning(value = "cut()", returning = "returnObj")
//    public void afterRun(JoinPoint joinPoint, Object returnObj) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        log.info("Return Obj returnObj : " + method.getName() + ", " + returnObj);
//    }
//}
