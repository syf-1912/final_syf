package com.syf.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//定义运行时使用注解
@Retention(RetentionPolicy.RUNTIME)
//定义注解可以加在方法和类上
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface LogAnnotation {
    //注解的属性
    String name();
}
