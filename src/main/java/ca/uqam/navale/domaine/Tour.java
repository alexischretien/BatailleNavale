/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * Tour.java - Fichier source de la classe Tour offrant une représentation
 *             de l'état de la partie pouvant être utilisée par la couche
 *             présentation afin de mettre à jour l'interface graphique
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
public class Tour {

    char[][] champJoueur;
    char[][] champAdversaire;
    String evenement;

    /*
     * Constructeur
     */       
    public Tour () {
        champJoueur = new char[10][10];
        champAdversaire = new char[10][10];
    }

    public Tour (Flotte flotteInitialeJoueur, Flotte flotteInitialeAdversaire) {
     
        champJoueur = new char[10][10];
        champAdversaire = new char[10][10];
        evenement = "Premier tour";

        List<Case> casesOccupeesJoueur = new ArrayList<Case>();
        List<Case> casesOccupeesAdversaire = new ArrayList<Case>();

        casesOccupeesJoueur.addAll(flotteInitialeJoueur.getPorte_avion());
        casesOccupeesJoueur.addAll(flotteInitialeJoueur.getCroiseur());
        casesOccupeesJoueur.addAll(flotteInitialeJoueur.getContre_torpilleurs());
        casesOccupeesJoueur.addAll(flotteInitialeJoueur.getSous_marin());
        casesOccupeesJoueur.addAll(flotteInitialeJoueur.getTorpilleur());

        casesOccupeesAdversaire.addAll(flotteInitialeAdversaire.getPorte_avion());
        casesOccupeesAdversaire.addAll(flotteInitialeAdversaire.getCroiseur());
        casesOccupeesAdversaire.addAll(flotteInitialeAdversaire.getContre_torpilleurs());
        casesOccupeesAdversaire.addAll(flotteInitialeAdversaire.getSous_marin());
        casesOccupeesAdversaire.addAll(flotteInitialeAdversaire.getTorpilleur()); 

        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                champJoueur[i][j] = 'e';
                champAdversaire[i][j] = 'e'; 
            }
        }
        for (Case c : casesOccupeesJoueur) {
            champJoueur[c.get_i()][c.get_j()] = 'n';
        }
        for (Case c : casesOccupeesAdversaire) {
            champAdversaire[c.get_i()][c.get_j()] = 'n';
        }
    }

    public Tour (Tour t) {

        this.champJoueur = new char[10][10];
        this.champAdversaire = new char[10][10];

        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                this.champJoueur[i][j] = t.getChampJoueur(i, j);
                this.champAdversaire[i][j] = t.getChampAdversaire(i, j);
            }
        }
        this.evenement = t.evenement;
    }

    /*
     * Determine s'il s'agit du premier tour
     *
     * @return  "true" si le tour represente le premier tour, "false" sinon
     */
    public boolean estPremierTour() {
        return evenement.equals("Premier tour");
    }

    /*
     * Determine s'il s'agit du dernier tour
     *
     * @return  "true" si le tour represente le dernier tour, "false" sinon
     */
    public boolean estDernierTour() {
        return (evenement.equals("Vous avez gagné") ||
                evenement.equals("Vous avez perdu") ||
                evenement.equals("Partie terminée") ||
                evenement.equals("Nouveau record"));
    }


    // getters
    public char getChampJoueur(int i, int j) {
        return champJoueur[i][j];
    }
    public char getChampAdversaire(int i, int j) {
        return champAdversaire[i][j];
    }
    public char[][] getChampJoueur() {
        return champJoueur;
    }
    public char[][] getChampAdversaire() {
        return champAdversaire;
    }
    public String getEvenement() {
        return evenement;
    }

    // setters
    public void setChampJoueur(int i, int j, char c) {
        this.champJoueur[i][j] = c;
    }
    public void setChampAdversaire(int i, int j, char c) {
        this.champAdversaire[i][j] = c;
    }
    @XmlElement
    public void setChampJoueur(char[][] champJoueur) {
        this.champJoueur = champJoueur;
    }
    @XmlElement
    public void setChampAdversaire(char[][] champAdversaire) {
        this.champAdversaire = champAdversaire;
    }
    @XmlElement
    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }     
}
 
