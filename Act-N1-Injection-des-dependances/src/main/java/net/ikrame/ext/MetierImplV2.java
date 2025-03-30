package net.ikrame.ext;

import net.ikrame.annotations.Component;
import net.ikrame.annotations.Inject;
import net.ikrame.dao.IDao;
import net.ikrame.metier.IMetier;

@Component("metier")
public class MetierImplV2 implements IMetier {

    @Inject
    private IDao dao;

    public MetierImplV2(IDao dao) {
        this.dao = dao;
    }

    public MetierImplV2() {}

    @Override
    public double calcul() {
        double t = dao.getData();
        double res = t*12 *Math.PI/2 *Math.cos(t);
        return res;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
