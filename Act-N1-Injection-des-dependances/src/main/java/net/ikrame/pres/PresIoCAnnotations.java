package net.ikrame.pres;

import net.ikrame.framework.MyIoCContainer2;
import net.ikrame.metier.IMetier;

public class PresIoCAnnotations {
    public static void main(String[] args) throws Exception {
        MyIoCContainer2 container = new MyIoCContainer2();
        container.loadBeansFromAnnotations("net.ikrame");

        IMetier metier = (IMetier) container.getBean("metier");
        System.out.println("Résultat (annotations) : " + metier.calcul());
    }
}
