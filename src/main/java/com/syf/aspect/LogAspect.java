package com.syf.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Configuration
@Aspect
public class LogAspect {
    @Around("@annotation(LogAnnotation)")
    public void log(ProceedingJoinPoint proceedingJoinPoint) {
        //获取request 或 session
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        //获取session作用域里的用户信息
        String user = (String) session.getAttribute("user");
        //获取系统当前时间
        Date date = new Date();

        //获取方法方法对象
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //获取方法的注解
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        String name = annotation.name();

        //放行目标方法
        boolean b = false;
        try {
            Object proceed = proceedingJoinPoint.proceed();
            b = true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(b);
    }
}
