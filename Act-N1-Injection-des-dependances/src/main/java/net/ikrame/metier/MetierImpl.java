package net.ikrame.metier;

import net.ikrame.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {

    @Autowired
    private IDao dao; //coupliage faible

    /**
     * Pour injecter dans l'attribut dao
     * un objet d'une classe qui implemente l'interface IDAO
     * au moment de l'instatiation
     */
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    public MetierImpl() {
    }

    @Override
    public double calcul() {
        double t = dao.getData();
        double res = t*12 *Math.PI/2 *Math.cos(t);
        return res;
    }

    /**
     * Pour injecter dans l'attribut dao
     * un objet d'une classe qui implemente l'interface IDAO
     * aprés instantiation
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
