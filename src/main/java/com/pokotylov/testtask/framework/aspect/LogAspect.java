package com.pokotylov.testtask.framework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.testng.Reporter;

@Aspect
public class LogAspect {
    @Before("@annotation(Loggable) && execution(* *(..))")
    public void beforeAdvice(final JoinPoint joinPoint) {
        String name = AspectHelper.getMethodNameAndClass(joinPoint);
        String params = AspectHelper.getMethodArguments(joinPoint);
        if(!params.isEmpty())
            params = "with params " + params;
        Reporter.log(String.format("<br>%s %s</br>", name, params));
    }
}
