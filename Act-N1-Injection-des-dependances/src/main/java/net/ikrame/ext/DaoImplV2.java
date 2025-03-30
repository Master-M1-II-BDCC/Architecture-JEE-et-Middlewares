package net.ikrame.ext;

import net.ikrame.annotations.Component;
import net.ikrame.dao.IDao;

@Component("dao")
public class DaoImplV2 implements IDao {

    @Override
    public double getData() {
        System.out.println("Version capteurs..........");
        double t = 12;
        return t;
    }
}
