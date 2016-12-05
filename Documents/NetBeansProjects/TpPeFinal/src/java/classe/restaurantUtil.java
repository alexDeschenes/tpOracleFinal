/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alexandre
 */
public class restaurantUtil {
     Session session = null;

    public void ajouterResto(String description, String nom,String siteweb, int prix,String img,Typecuisine type )
    {
            Restaurant unResto = new Restaurant();
            unResto.setDescription(description);
            unResto.setNom(nom);
            unResto.setSiteweb(siteweb);
            unResto.setPrixmoyen(prix);
            unResto.setImage(img);
            unResto.setTypecuisine(type);
          
                  
            Transaction tx = null;
            this.session = HibernateUtil.getSessionFactory().openSession();
        
        try{    
            tx = session.beginTransaction();
			
	    // l'ajout ne se fait pas car il reste des champs null qui ne doivent pas l'être
            
            session.saveOrUpdate(unResto);
            tx.commit();
        }
        catch(Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
        
        this.session.close();
        
    }
    
    
    public List<Restaurant>  listeRestaurant()
    {
        List<Restaurant> listeResto = null;
        
        Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            
            tx = session.beginTransaction();
            
            // Liste de tous les livres
            Query Resto = session.createQuery("from Restaurant");
           
            listeResto = Resto.list();
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        this.session.close();
        return listeResto;
    
    }
       
    public String  supResto(int id)
    {
        String message;
        Transaction tx = null;
        List<Restaurant> listeResto = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            
            tx = session.beginTransaction();
            
            // Liste de tous les livres
            Query Resto = session.createQuery("from Restaurant where id ="+id);
           
            listeResto = Resto.list();
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listeResto.size()!=0)
        {
            session.delete(listeResto.get(0));
            this.session.close();
            return "le restaurant a bien été supprimer";
            
        }
        else
        {
          this.session.close();
          return "Restaurant introuvable";
        }
    
    }
    
    public List<Restaurant> RestosRécents() {
        List<Restaurant> listeResto = null;
        
        Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            
            tx = session.beginTransaction();
            
            Query requete = session.createQuery("FROM Restaurant");
            requete.setFirstResult(0);
            requete.setMaxResults(3);
           
            listeResto = requete.list();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        this.session.close();
        return listeResto;
    }
    
     public List<Restaurant> RechercherRestos(String infos){
        List<Restaurant> listeResto = null;
        
        Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().openSession();
       
        try {
            tx = session.beginTransaction();
            
            Query requete = session.createQuery("FROM Restaurant where lower(nom) like '%infos%' OR lower(typecuisine) like '%infos%'");
            // Requête qui cherche un resto selon son nom ou son type de cuisine
           
            listeResto = requete.list();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        this.session.close();
        return listeResto;
    }
    
    
    
    
}
