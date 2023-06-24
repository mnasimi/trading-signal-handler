package com.tradingsignal.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper function to create map of trading actions.
 */
@Component
public class MethodMapBuilder {
    public Map<String, Method> build(Class<?> clazz) {
        Map<String, Method> methodMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            String methodName = method.getName();
            methodMap.put(methodName, method);
        }
        return methodMap;
    }
}
