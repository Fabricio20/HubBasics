package net.notfab.hubbasics.spigot.utils;

import net.notfab.hubbasics.spigot.managers.HBLogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

public class ReflectionUtils {

    private static final HBLogger logger = HBLogger.getLogger(ReflectionUtils.class);

    public static Class<?> findClass(String fqn) {
        try {
            return Class.forName(fqn);
        } catch (Exception ex) {
            logger.warn("No class found for name " + fqn);
            return null;
        }
    }

    public static List<Method> findMethods(String clazzName) {
        Class<?> clazz = findClass(clazzName);
        if (clazz == null) {
            return new ArrayList<>();
        }
        return findMethods(clazz);
    }

    public static List<Method> findMethods(Class<?> clazz) {
        List<Method> methodList = new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods()));
        if (clazz.getSuperclass() != null
                && clazz.getSuperclass() != Object.class) {
            methodList.addAll(findMethods(clazz.getSuperclass()));
        }
        if (clazz.getInterfaces() != null
                && clazz.getInterfaces().length > 0) {
            for (Class<?> aClass : clazz.getInterfaces()) {
                methodList.addAll(findMethods(aClass));
            }
        }
        return methodList;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Finds a method on a class (or it's superclasses).
     *
     * @param clazzName  - Fully qualified name of the class.
     * @param name       - Name of the method.
     * @param parameters - List of parameters (if any).
     * @return Method or null.
     */
    public static Method findMethod(String clazzName, String name, Object... parameters) {
        Class<?>[] classes = Arrays.stream(parameters)
                .map(c -> (Class<?>) c.getClass())
                .toArray((IntFunction<Class<?>[]>) Class[]::new);
        return findMethod(clazzName, name, classes);
    }

    /**
     * Finds a method on a class (or it's superclasses).
     *
     * @param clazzName  - Fully qualified name of the class.
     * @param name       - Name of the method.
     * @param parameters - List of parameter classes (if any).
     * @return Method or null.
     */
    public static Method findMethod(String clazzName, String name, Class<?>... parameters) {
        Class<?> clazz = findClass(clazzName);
        if (clazz == null) {
            return null;
        }
        return findMethod(clazz, name, parameters);
    }

    /**
     * Finds a method on a class (or it's superclasses).
     *
     * @param clazz      - Class.
     * @param name       - Name of the method.
     * @param parameters - List of parameters (if any).
     * @return Method or null.
     */
    public static Method findMethod(Class<?> clazz, String name, Object... parameters) {
        Class<?>[] classes = Arrays.stream(parameters)
                .map(c -> (Class<?>) c.getClass())
                .toArray((IntFunction<Class<?>[]>) Class[]::new);
        return findMethod(clazz, name, classes);
    }

    /**
     * Finds a method on a class (or it's superclasses).
     *
     * @param clazz      - Class.
     * @param name       - Name of the method.
     * @param parameters - List of parameter classes (if any).
     * @return Method or null.
     */
    public static Method findMethod(Class<?> clazz, String name, Class<?>... parameters) {
        int bestScore = 0;
        Method bestMatch = null;
        List<Class<?>> methodParams = Arrays.asList(parameters);
        List<Method> allMethods = findMethods(clazz);
        for (Method method : allMethods) {
            int score = 1;
            if (!name.equalsIgnoreCase(method.getName())) {
                continue;
            }
            for (Parameter parameter : method.getParameters()) {
                if (methodParams.contains(parameter.getType())) {
                    score++;
                }
            }
            if (score > bestScore) {
                bestMatch = method;
            }
        }
        return bestMatch;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static <T> T invokeMethod(Object clazz, String methodName, Object... params) {
        String clazzName = clazz.getClass().getName();
        Class<?>[] classes = Arrays.stream(params)
                .map(c -> (Class<?>) c.getClass())
                .toArray((IntFunction<Class<?>[]>) Class[]::new);
        Method method = findMethod(clazzName, methodName, classes);
        if (method == null) {
            logger.warn("No method found named " + methodName + " on " + clazzName + " with params " + Arrays.toString(classes));
            return null;
        }
        return invokeMethod(clazz, method, params);
    }

    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Object clazz, Method method, Object... params) {
        boolean accessible = method.isAccessible();
        if (!accessible) {
            method.setAccessible(true);
        }
        try {
            return (T) method.invoke(clazz, params);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.error("Error while invoking method on " + clazz.getClass().getName(), ex);
            return null;
        } finally {
            if (!accessible) {
                method.setAccessible(false);
            }
        }
    }

}