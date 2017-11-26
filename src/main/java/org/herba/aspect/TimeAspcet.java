package org.herba.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class TimeAspcet {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(TimeAspcet.class);

    @Around("execution(* org.herba.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        long start = new Date().getTime();
        Object object = pjp.proceed();
        logger.info(pjp.getTarget().getClass().getName() + "类的" + method.getName() + "方法执行时间为" + (new Date().getTime() - start) + "毫秒");

        return object;
    }
}
