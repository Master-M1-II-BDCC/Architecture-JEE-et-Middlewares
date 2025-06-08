package net.ikrame.pres;

import net.ikrame.ext.DaoImplV2;
import net.ikrame.metier.MetierImpl;

public class Pres1 {
    public static void main(String[] args) {
        DaoImplV2 d = new DaoImplV2();
        //DaoImpl d = new DaoImpl();
        MetierImpl metier = new MetierImpl(d);
        //metier.setDao(d); // injection de dépendences via le setter
        System.out.println("RES= "+metier.calcul());
    }
}
