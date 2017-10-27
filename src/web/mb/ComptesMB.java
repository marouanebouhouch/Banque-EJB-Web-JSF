package web.mb;

import metier.BanqueLocal;
import metier.entities.Compte;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ManagedBean
public class ComptesMB {

    @EJB
    private BanqueLocal metier;

    private double solde;

    public List<Compte> getListComptes() {
        return metier.listComptes();
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Compte addCompte() {
        Compte c = new Compte();
        c.setSolde(solde);
        c.setDate_creation(new Date());
        return metier.addCompte(c);
    }

    public Compte getCompte() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return metier.getCompte(Long.parseLong(params.get("code")));
    }

    public String getOperation(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return params.get("op");
    }
}
