package com.pokotylov.testtask.framework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import java.util.ArrayList;
import java.util.List;

class AspectHelper {

    private AspectHelper() {
    }

    static String getMethodNameAndClass(JoinPoint joinPoint) {
        String methodName = splitCamelCase(joinPoint.getSignature().getName());
        String methodClass = splitCamelCase(joinPoint.getThis().getClass().getSimpleName());
        return String.format("%s: %s", methodClass, methodName);
    }

    static String getMethodArguments(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        if (params.length == 0) {
            return "";
        } else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String[] paramsNames = signature.getParameterNames();
            List<String> result = new ArrayList<>(params.length);
            for (int i = 0; i < params.length; i++) {
                result.add(String.format("%s: %s", paramsNames[i], String.valueOf(params[i])));
            }
            return result.toString();
        }
    }

    private static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }
}
