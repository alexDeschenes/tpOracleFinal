/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import classe.Membre;
import classe.MembreUtil;
import classe.Restaurant;
import classe.Typemembre;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kfiliatreault
 */
@ManagedBean
@RequestScoped
@SessionScoped
public class ajouterUtilisateur {

    private MembreUtil MembreUtil;
    private String nomUtil;
    private String email;
    private String Mdp;
    private String MdpConf;
    private Typemembre Type;
    private String message;
  
    /**
     * Creates a new instance of ajouterClient
     */
    public ajouterUtilisateur() {
        MembreUtil = new MembreUtil();
    }
    
    public String getMessage() {
        return message;
    }
    
    public void ajouterMembre()
    {
       
        MembreUtil.ajouterClient(nomUtil, MdpConf, email, Mdp);
        message = "Le client a bien été ajouté!";
    }
    public Membre getMembreCon()
    {
        Membre unMem ;
        unMem=MembreUtil.getClientConnexion(nomUtil,Mdp);
        if(unMem == null)
        {
            
          message = "Aucun membre trouvé";
        }
        else
        {  
          message = "Le client a été trouvé!";
          FacesContext facesContext = FacesContext.getCurrentInstance();
           HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
           session.setAttribute("id", unMem.getIdmembre());
           session.setAttribute("nom", unMem.getIdmembre());
        }
  
        return unMem;
       
    }
   public void ajoutCommentaire(String Contenu,Date dateCreation,Membre mem,int note,Restaurant resto)
    {
        MembreUtil.ajouterCommentaire(Contenu, dateCreation, mem, note, resto);
        message ="le commentaire a été ajouté";
    }


   
    public void setNom(String nom) {
        this.nomUtil = nom;
    }
    public void setEmail(String Email) {
        this.email = Email;
    }
     public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }
    public void setMdpConf(String Mdp) {
        this.MdpConf = Mdp;
    }
     public void setType(Typemembre Type) {
        this.Type = Type;
    }
      public String getNom() {
        return this.nomUtil ;
    }
    public String getEmail() {
       return this.email;
    }
     public String getMdp() {
       return this.Mdp;
    }
  
     public Typemembre getType() {
        return this.Type;
    }
    public String getMdpConf()
    {
        return this.MdpConf;
    }

}
