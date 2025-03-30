package net.ikrame.metier;

import net.ikrame.annotations.Component;
import net.ikrame.annotations.Inject;
import net.ikrame.dao.IDao;

@Component("metierSetter")
public class MetierImplSetter implements IMetier {
    private IDao dao;

    public MetierImplSetter() {}

    @Inject
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getData() * 3;
    }
}

