package ca.uqam.navale.domaine;
 
import java.util.*;

public class Flotte {

    List<Case> porte_avion;
    List<Case> croiseur;
    List<Case> contre_torpilleurs;
    List<Case> sous_marin;
    List<Case> torpilleur;

    public Flotte() {
        porte_avion        = new ArrayList<Case>();
        croiseur           = new ArrayList<Case>();
        contre_torpilleurs = new ArrayList<Case>();
        sous_marin         = new ArrayList<Case>();
        torpilleur         = new ArrayList<Case>();
    }

    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId) {

        boolean positionnementValide = true;
        int longueur = -1;
        List<Case> navire = new ArrayList<Case>();
        List<Case> casesOccupees = new ArrayList<Case>();

        casesOccupees.addAll(porte_avion);
        casesOccupees.addAll(croiseur);
        casesOccupees.addAll(contre_torpilleurs);
        casesOccupees.addAll(sous_marin);
        casesOccupees.addAll(torpilleur);

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

        for (Case caseNavire : navire) {
            if (casesOccupees.contains(caseNavire) ||
                    caseNavire.get_i() < 0 || caseNavire.get_i() > 9 ||
                    caseNavire.get_j() < 0 || caseNavire.get_j() > 9) {
                positionnementValide = false;
                break;
            }
        }
         
        if (positionnementValide == true) {
            if (navireId == 0) porte_avion = navire;
            else if (navireId == 1) croiseur = navire;
            else if (navireId == 2) contre_torpilleurs = navire;
            else if (navireId == 3) sous_marin = navire;
            else if (navireId == 4) torpilleur = navire;

           casesOccupees.addAll(navire);
        }
        return casesOccupees;
                
    }

    public String attaquer(Case c) {
        // à compléter
        return null;
    }
}
