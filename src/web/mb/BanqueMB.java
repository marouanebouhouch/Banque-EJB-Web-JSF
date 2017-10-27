package web.mb;

import metier.BanqueLocal;
import metier.entities.Compte;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
public class BanqueMB {

    @EJB
    private BanqueLocal metier;

    public List<Compte> getListComptes() {
        return metier.listComptes();
    }
}
