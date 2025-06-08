package net.ikrame.pres;

import net.ikrame.framework.MyIoCContainer3;
import net.ikrame.metier.IMetier;

public class PresIoCAnnotationsFinal {
    public static void main(String[] args) throws Exception {
        MyIoCContainer3 container = new MyIoCContainer3();
        container.loadBeansFromAnnotations("net.ikrame");

        IMetier metierCons = (IMetier) container.getBean("metierCons");
        System.out.println("Constructeur: " + metierCons.calcul());

        IMetier metierSetter = (IMetier) container.getBean("metierSetter");
        System.out.println("Setter: " + metierSetter.calcul());

        IMetier metierField = (IMetier) container.getBean("metierField");
        System.out.println("Attribut: " + metierField.calcul());
    }
}
