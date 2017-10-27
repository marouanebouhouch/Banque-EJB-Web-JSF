package web.mb;

import metier.BanqueLocal;
import metier.entities.Compte;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class ComptesMB {

    @EJB
    private BanqueLocal metier;

    private double solde;
    private double montant;
    private String operation;
    private Compte compte;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

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
        setSolde(0);
        return metier.addCompte(c);
    }

    public Compte getCompte(Long code) {
        return metier.getCompte(code);
    }

    public String getOperation(){
        return this.operation;
    }

    public String goToOperation(Long code, String op){
        compte = getCompte(code);
        operation = op;
        return "operations.xhtml";
    }

    public void doOperation(){
        if(operation.equals("v"))
            metier.verser(compte.getCode(), montant);
        else
            metier.retirer(compte.getCode(), montant);
        compte = metier.getCompte(compte.getCode());
        setMontant(0);
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}

