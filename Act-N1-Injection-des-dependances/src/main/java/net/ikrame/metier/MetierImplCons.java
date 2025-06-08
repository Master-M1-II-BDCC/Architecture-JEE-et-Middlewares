package net.ikrame.metier;

import net.ikrame.annotations.Component;
import net.ikrame.annotations.Inject;
import net.ikrame.dao.IDao;

@Component("metierCons")
public class MetierImplCons implements IMetier {
    private final IDao dao;

    @Inject
    public MetierImplCons(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getData() * 2;
    }
}
