package ca.uqam.navale.domaine;
 
import java.util.*;

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

    //    boolean positionnementValide = true;
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
                return "déjà attaquée"; 
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
                             return "partie terminée";
                         }
                         else{
                             return "coulé"; 
                         }
                     }
                     return "touché";
                 }
             }
        }
        return "dans l'eau";
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
        l.add(porte_avion);
        l.add(croiseur);
        l.add(contre_torpilleurs);
        l.add(sous_marin);
        l.add(torpilleur);
        return l;
    }
/*
    private static List<Case> genererNavireAleatoire(int longueur) {
        
        int i;
        int j;
        int i_max;
        int j_max;
        List<Case> navire = new ArrayList<Case>();
        boolean horizontal = Math.random() < 0.5;
        Random rand1 = new Random(); 
        Random rand2 = new Random();
        
        i_max = (horizontal ? 10 : 10 - longueur);
        j_max = (horizontal ? 10 - longueur : 10);

        i = rand1.nextInt(i_max);
        j = rand2.nextInt(j_max);

        while (longueur > 0) {
            navire.add(new Case(i, j));
            if (horizontal) ++j;
            else            ++i;
        --longueur;
        }
    }*/
}
