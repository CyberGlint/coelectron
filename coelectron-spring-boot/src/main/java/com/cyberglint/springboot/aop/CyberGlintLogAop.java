package com.cyberglint.springboot.aop;

import com.alibaba.fastjson.JSON;
import com.cyberglint.common.execption.CyberGlintBizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 使用AspectJ的注解@Aspect声明是一个切面
 * 使用Spring的注解@Component，将这个类定义成Bean
 * 使用Lombok的注解@Slf4j，为这个类添加一个日志器(logger)
 */
@Aspect
@Component
@Slf4j
public class CyberGlintLogAop {
    
    /**
     * 使用ThreadLocal安全地记录请求的开始时间
     */
    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();
    
    /**
     * 定义切入点，表示匹配com.cyberglint.*.controller包下面的任何方法
     */
    @Pointcut("execution(* com.cyberglint.*.controller..*.*(..))")
    public void controllerMethod() {
    
    }
    
    /**
     * 在切入点的方法前执行的通知，用来记录请求开始的信息和时间
     */
    @Before("controllerMethod()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("request-start:{}#{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime);
        try {
            logArguments(joinPoint.getArgs());
        } catch (Exception e) {
            log.error("[logBefore-error]:", e);
        }
    }
    
    /**
     * 用来记录请求的参数
     */
    private void logArguments(Object[] arguments) {
        for(Object arg: arguments) {
            log.info("request-args:{}", JSON.toJSONString(arg));
        }
    }
    
    /**
     * 在切入点的方法后执行的通知，用来记录请求结束的时间和执行时间
     */
    @After("controllerMethod()")
    public void logAfter(JoinPoint joinPoint) {
        try {
            long endTime = System.currentTimeMillis();
            log.info("request-end:{}#{}:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), (endTime - startTimeThreadLocal.get()) + " ms");
        } finally {
            startTimeThreadLocal.remove();
        }
    }
    
    /**
     * 在切入点的方法正常返回后执行的通知，用来记录请求的响应内容
     */
    @AfterReturning(pointcut = "controllerMethod()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        try {
            log.info("request-response:{}#{}:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("[logAfterReturning-error]:", e);
        }
    }
    
    /**
     * 在切入点的方法抛出GlintBizException异常后执行的通知，用来记录异常信息
     */
    @AfterThrowing(pointcut = "controllerMethod()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, CyberGlintBizException ex) {
        log.error("[logAfterThrowing-error]:{}#{}:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), JSON.toJSONString(ex.getMessage()));
    }
}