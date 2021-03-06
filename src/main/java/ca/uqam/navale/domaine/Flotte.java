/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * Flotte.java - Fichier source de la classe Flotte, offrant une représentation 
 *               de l'état de la flotte d'une joueur en conservant les cases 
 *               non-attaquées et occupées par un navire du joueur, ainsi que les
 *               cases qui ont été attaquées par l'adversaire. 
 *    
 *               La classe offre également des méthodes pour placer un navire, pour
 *               générer une instance de flotte valide aléatoire et pour mettre à
 *               jour l'état de la flotte suite à une attaque en renvoyant un message
 *               d'événement. 
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.domaine;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
public class Flotte {

    private static final int[] LONGUEUR = {5, 4, 3, 3, 2};
    private static final int   NAVIRE_INVALIDE_ID    = -1;
    private static final int   PORTE_AVION_ID        =  0;
    private static final int   CROISEUR_ID           =  1;
    private static final int   CONTRE_TORPILLEURS_ID =  2;
    private static final int   SOUS_MARIN_ID         =  3;
    private static final int   TORPILLEUR_ID         =  4;

    List<Case> porte_avion;
    List<Case> croiseur;
    List<Case> contre_torpilleurs;
    List<Case> sous_marin;
    List<Case> torpilleur;
    List<Case> cases_attaquees;

    // Constructeur
    public Flotte() {
        porte_avion        = new ArrayList<Case>();
        croiseur           = new ArrayList<Case>();
        contre_torpilleurs = new ArrayList<Case>();
        sous_marin         = new ArrayList<Case>();
        torpilleur         = new ArrayList<Case>();
        cases_attaquees    = new ArrayList<Case>();
    }

   /* Postionne un navire dans la flotte si les donnees de postionnement sont valides.
    *
    * @param i, j        position initial du navire en coordonne(i,j)
    * @param horizontal  vrai s'il  est horizontal, faux si vertical
    * @param navireId    integer du type navire
    * @return liste de case occupe par les navires de la flotte
    */

    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId) {

        int longueur = NAVIRE_INVALIDE_ID;
        List<Case> navire = new ArrayList<Case>();
        List<Case> casesOccupees = this.getCasesOccupees();
    
        if (navireId >= PORTE_AVION_ID && navireId <= TORPILLEUR_ID) {
            if (navireId == PORTE_AVION_ID) {
                casesOccupees.removeAll(porte_avion);
            }
            else if (navireId == CROISEUR_ID) {
                casesOccupees.removeAll(croiseur);
            }
            else if (navireId == CONTRE_TORPILLEURS_ID) {
                casesOccupees.removeAll(contre_torpilleurs);
            }
            else if (navireId == SOUS_MARIN_ID) {
                casesOccupees.removeAll(sous_marin);
            }
            else {
                casesOccupees.removeAll(torpilleur);
            }
            longueur = LONGUEUR[navireId];
            
        }
        else {
            longueur = -1;
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
        if (navireId == PORTE_AVION_ID)             porte_avion = navire;
        else if (navireId == CROISEUR_ID)           croiseur = navire;
        else if (navireId == CONTRE_TORPILLEURS_ID) contre_torpilleurs = navire;
        else if (navireId == SOUS_MARIN_ID)         sous_marin = navire;
        else if (navireId == TORPILLEUR_ID)         torpilleur = navire;

        casesOccupees.addAll(navire);

        return casesOccupees;
                
    }

    /* Determine le resultat d'une attaque par torpille et renvoie le message
     * de l'evenement associe
     *
     * @param c   la case de l'attaque
     * @return    le message de l'evenement
     */
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

    /*
     * Genere une instance de flotte initiale aleatoire et valide
     *
     * @return   une nouvelle flotte aleatoire et valide
     */
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

    /*
     * Retourne toutes les positions des cases occupees par les differents 
     * navires de la flotte
     *
     * @return   la liste des cases occupees par les navires de la flotte
     */
    private List<Case> getCasesOccupees() {

        List<Case> casesOccupees = new ArrayList<Case>();

        casesOccupees.addAll(porte_avion);
        casesOccupees.addAll(croiseur);
        casesOccupees.addAll(contre_torpilleurs);
        casesOccupees.addAll(sous_marin);
        casesOccupees.addAll(torpilleur);
        return casesOccupees;
    }

    /*
     * Retourne la liste des navires/attributs de la flotte
     *
     * @return la liste de liste de cases occupees par chaque navire
     */
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
    public List<Case> getCases_attaquees() {
        return cases_attaquees;
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
    @XmlElement
    public void setCases_attaquees(List<Case> cases_attaquees) {
        this.cases_attaquees = cases_attaquees;
    }
}
