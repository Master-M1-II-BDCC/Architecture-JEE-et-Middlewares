package net.ikrame.framework;

import net.ikrame.annotations.Component;
import net.ikrame.annotations.Inject;
import org.reflections.Reflections;

import java.lang.reflect.*;
import java.util.*;

public class MyIoCContainer3 {

    private final Map<String, Object> beansById = new HashMap<>();
    private final Map<Class<?>, Object> beansByType = new HashMap<>();

    public void loadBeansFromAnnotations(String basePackage) throws Exception {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> clazz : types) {
            Component annotation = clazz.getAnnotation(Component.class);
            String id = annotation.value().isEmpty() ? clazz.getSimpleName() : annotation.value();

            Object instance;
            try {
                instance = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                instance = null;
            }

            beansById.put(id, instance);
            beansByType.put(clazz, instance);

            for (Class<?> interf : clazz.getInterfaces()) {
                beansByType.put(interf, instance);
            }
        }

        for (Class<?> clazz : types) {
            String id = clazz.getAnnotation(Component.class).value();
            Object bean = beansById.get(id.isEmpty() ? clazz.getSimpleName() : id);

            if (bean == null || hasConstructorInjection(clazz)) {
                bean = createBeanWithInjection(clazz);
                beansById.put(id.isEmpty() ? clazz.getSimpleName() : id, bean);
                beansByType.put(clazz, bean);

                for (Class<?> interf : clazz.getInterfaces()) {
                    beansByType.put(interf, bean);
                }
            } else {
                injectSetterAndField(bean);
            }
        }

        System.out.println("Beans chargés avec succès avec les 3 injections !");
    }

    private boolean hasConstructorInjection(Class<?> clazz) {
        for (Constructor<?> cons : clazz.getDeclaredConstructors()) {
            if (cons.isAnnotationPresent(Inject.class)) {
                return true;
            }
        }
        return false;
    }

    private Object createBeanWithInjection(Class<?> clazz) throws Exception {
        Constructor<?> injectConstructor = null;
        for (Constructor<?> cons : clazz.getDeclaredConstructors()) {
            if (cons.isAnnotationPresent(Inject.class)) {
                injectConstructor = cons;
                break;
            }
        }

        if (injectConstructor != null) {
            Class<?>[] paramTypes = injectConstructor.getParameterTypes();
            Object[] dependencies = Arrays.stream(paramTypes)
                    .map(beansByType::get)
                    .toArray();
            return injectConstructor.newInstance(dependencies);
        }

        return clazz.getDeclaredConstructor().newInstance();
    }

    private void injectSetterAndField(Object instance) throws Exception {
        for (Method method : instance.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inject.class)) {
                Class<?> dependencyType = method.getParameterTypes()[0];
                Object dependency = beansByType.get(dependencyType);
                method.setAccessible(true);
                method.invoke(instance, dependency);
            }
        }

        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Object dependency = beansByType.get(field.getType());
                field.setAccessible(true);
                field.set(instance, dependency);
            }
        }
    }

    public Object getBean(String id) {
        return beansById.get(id);
    }

}
