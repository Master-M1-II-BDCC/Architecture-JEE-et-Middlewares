package net.ikrame.pres;

import net.ikrame.dao.IDao;
import net.ikrame.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pres2 {
    public static void main(String[] args) throws Exception{

        Scanner scanner =  new Scanner(new File("config.txt"));

        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao d = (IDao) cDao.newInstance();

        String metierClassName = scanner.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getConstructor(IDao.class).newInstance(d);

        System.out.println("Res ="+metier.calcul());
    }
}
