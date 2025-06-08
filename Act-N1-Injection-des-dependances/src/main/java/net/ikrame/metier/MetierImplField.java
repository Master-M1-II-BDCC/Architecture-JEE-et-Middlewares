package net.ikrame.metier;

import net.ikrame.annotations.Component;
import net.ikrame.annotations.Inject;
import net.ikrame.dao.IDao;

@Component("metierField")
public class MetierImplField implements IMetier {
    @Inject
    private IDao dao;

    @Override
    public double calcul() {
        return dao.getData() * 4;
    }
}
