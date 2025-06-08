package net.ikrame.pres;

import net.ikrame.framework.MyIoCContainer;

public class PresIoCXML {
    public static void main(String[] args) {
        MyIoCContainer container = new MyIoCContainer();
        container.loadBeansFromXML("src/main/resources/config-ioc.xml");

        System.out.println("Résultat du parsing JAXB :");
        container.getBeans().getBeans().forEach(bean ->
                System.out.println("Bean ID: " + bean.getId() + ", Class: " + bean.getClassName())
        );
    }
}
