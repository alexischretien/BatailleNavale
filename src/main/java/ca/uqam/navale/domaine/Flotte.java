package ca.uqam.navale.domaine;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
public class Flotte {

    List<Case> porte_avion;
    List<Case> croiseur;
    List<Case> contre_torpilleurs;
    List<Case> sous_marin;
    List<Case> torpilleur;
    List<Case> cases_attaquees;

    public Flotte() {
        porte_avion        = new ArrayList<Case>();
        croiseur           = new ArrayList<Case>();
        contre_torpilleurs = new ArrayList<Case>();
        sous_marin         = new ArrayList<Case>();
        torpilleur         = new ArrayList<Case>();
        cases_attaquees    = new ArrayList<Case>();
    }

    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId) {

        int longueur = -1;
        List<Case> navire = new ArrayList<Case>();
        List<Case> casesOccupees = this.getCasesOccupees();

        if (navireId == 0) {
            casesOccupees.removeAll(porte_avion);
            longueur = 5;
        }
        else if (navireId == 1) {
            casesOccupees.removeAll(croiseur);
            longueur = 4;
        }
        else if (navireId == 2) {
            casesOccupees.removeAll(contre_torpilleurs);
            longueur = 3;
        }
        else if (navireId == 3) {
            casesOccupees.removeAll(sous_marin);
            longueur = 3;
        }
        else if (navireId == 4) {
            casesOccupees.removeAll(torpilleur);
            longueur = 2;
        }

        while (longueur > 0) {
            navire.add(new Case(i, j));            
            if (horizontal)  ++j; 
            else             ++i;
            --longueur;
        }

        // verification que les cases du nouveau navire sont valides
        for (Case caseNavire : navire) {
            if (caseNavire.get_i() < 0 || caseNavire.get_i() > 9 ||
                    caseNavire.get_j() < 0 || caseNavire.get_j() > 9) {

                return casesOccupees;
            }
            for (Case c : casesOccupees) {
                if (caseNavire.equals(c)) {
                    return casesOccupees;
                }
            }
        }
        // si positionnement valide, mise à jour de la Flotte
        if (navireId == 0) porte_avion = navire;
        else if (navireId == 1) croiseur = navire;
        else if (navireId == 2) contre_torpilleurs = navire;
        else if (navireId == 3) sous_marin = navire;
        else if (navireId == 4) torpilleur = navire;

        casesOccupees.addAll(navire);
  
        return casesOccupees;
                
    }

    public String attaquer(Case c) {
           
         List<List<Case>> listeNavires = getListeNavires();

         for (Case case_attaquee : cases_attaquees) {
            if (c.equals(case_attaquee)) {
                return "Déjà attaquée"; 
            }
         }
         cases_attaquees.add(c);

         for (List<Case> navire : listeNavires) {
             for (int i = 0; i < navire.size(); i++) {
                 if (c.equals(navire.get(i))) {
                     navire.remove(i);
                     if (navire.isEmpty()) {
                         listeNavires.remove(navire);
                         if (listeNavires.isEmpty()) {
                             return "Partie terminée";
                         }
                         else{
                             return "Coulé"; 
                         }
                     }
                     return "Touché";
                 }
             }
        }
        return "Dans l'eau";
    }

    public static Flotte genererFlotteAleatoire() {

        boolean horizontal;
        int i;
        int j;
        int[] tailles   = {5, 9, 12, 15, 17};
        Flotte flotteAleatoire = new Flotte();
        
        for (int k = 0 ; k < 5 ; ++k) {
            while (flotteAleatoire.getCasesOccupees().size() != tailles[k]) {
                horizontal = Math.random() > 0.5;
                i = new Random().nextInt(10);
                j = new Random().nextInt(10);  
                flotteAleatoire.positionnerNavire(i, j, horizontal, k);
            }
        }
        return flotteAleatoire;
    }

    public List<Case> getCasesOccupees() {

        List<Case> casesOccupees = new ArrayList<Case>();

        casesOccupees.addAll(porte_avion);
        casesOccupees.addAll(croiseur);
        casesOccupees.addAll(contre_torpilleurs);
        casesOccupees.addAll(sous_marin);
        casesOccupees.addAll(torpilleur);
        return casesOccupees;
    }

    private List<List<Case>> getListeNavires() {
    
        List<List<Case>> l = new ArrayList<List<Case>>();
        if (!porte_avion.isEmpty()) 
            l.add(porte_avion);
        if (!croiseur.isEmpty())
            l.add(croiseur);
        if (!contre_torpilleurs.isEmpty())
            l.add(contre_torpilleurs);
        if (!sous_marin.isEmpty())
            l.add(sous_marin);
        if (!torpilleur.isEmpty())
            l.add(torpilleur);
        return l;
    }

    // getters
    public List<Case> getPorte_avion() {
        return porte_avion;
    }
    public List<Case> getCroiseur() {
        return croiseur;
    }
    public List<Case> getContre_torpilleurs() {
        return contre_torpilleurs;
    }
    public List<Case> getSous_marin() {
        return sous_marin;
    }
    public List<Case> getTorpilleur() {
        return torpilleur;
    }

    // setters
    @XmlElement
    public void setPorte_avion(List<Case> porte_avion) {
        this.porte_avion = porte_avion;
    }
    @XmlElement
    public void setCroiseur(List<Case> croiseur) {
        this.croiseur = croiseur;
    }
    @XmlElement
    public void setContre_torpilleurs(List<Case> contre_torpilleurs) {
        this.contre_torpilleurs =  contre_torpilleurs;
    }
    @XmlElement
    public void setSous_marin(List<Case> sous_marin) {
        this.sous_marin = sous_marin;
    }
    @XmlElement
    public void setTorpilleur(List<Case> torpilleur) {
        this.torpilleur = torpilleur;
    }
}
