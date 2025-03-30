package net.ikrame.framework;

import net.ikrame.annotations.Component;
import net.ikrame.annotations.Inject;
import org.reflections.Reflections;
import java.lang.reflect.*;
import java.util.*;

public class MyIoCContainer2 {

    private Map<String, Object> beansById = new HashMap<>();
    private Map<Class<?>, Object> beansByType = new HashMap<>();

    public void loadBeansFromAnnotations(String basePackage) throws Exception {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> clazz : types) {
            Component annotation = clazz.getAnnotation(Component.class);
            String id = annotation.value().isEmpty() ? clazz.getSimpleName() : annotation.value();
            Object instance = clazz.getDeclaredConstructor().newInstance();

            beansById.put(id, instance);
            beansByType.put(clazz, instance);

            for (Class<?> interf : clazz.getInterfaces()) {
                beansByType.put(interf, instance);
            }
        }

        for (Object bean : beansById.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    Object dependency = beansByType.get(field.getType());
                    field.setAccessible(true);
                    field.set(bean, dependency);
                }
            }
        }
        System.out.println("Beans chargés automatiquement via annotations !");
    }

    public Object getBean(String id) {
        return beansById.get(id);
    }

}
