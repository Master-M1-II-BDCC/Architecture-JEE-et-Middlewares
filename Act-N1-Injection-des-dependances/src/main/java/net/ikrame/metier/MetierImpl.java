package net.ikrame.metier;

import net.ikrame.dao.IDao;

public class MetierImpl implements IMetier {

    private IDao dao; //coupliage faible

    @Override
    public double calcul() {
        double t = dao.getData();
        double res = t*12 *Math.PI/2 *Math.cos(t);
        return res;
    }

}
