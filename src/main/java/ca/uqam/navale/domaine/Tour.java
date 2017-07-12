/**
 * 'e' == case d'eau
 * 'd' == case d'eau découverte/touché
 * 'n' == case navire
 * 't' == case navire touché
 *
 */

package ca.uqam.navale.domaine;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
public class Tour {

    char[][] champJoueur;
    char[][] champAdversaire;
    String evenement;
       
    public Tour () {
        champJoueur = new char[10][10];
        champAdversaire = new char[10][10];
    }
    public Tour (Flotte flotteInitialeJoueur, Flotte flotteInitialeAdversaire) {
     
        champJoueur = new char[10][10];
        champAdversaire = new char[10][10];
        evenement = "Premier tour";

        List<Case> casesOccupeesJoueur     = flotteInitialeJoueur.getCasesOccupees();
        List<Case> casesOccupeesAdversaire = flotteInitialeAdversaire.getCasesOccupees();

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

    public boolean estPremierTour() {
        return evenement.equals("Premier tour");
    }
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
 
