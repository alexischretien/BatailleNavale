/**
 * 'e' == case d'eau
 * 'd' == case d'eau découverte/touché
 * 'n' == case navire
 * 't' == case navire touché
 *
 */

package ca.uqam.navale.domaine;

import java.util.*;

public class Tour {

    char[][] champJoueur = new char[10][10];
    char[][] champAdversaire = new char[10][10];
    String evenement;
       

    public Tour (Flotte flotteInitialeJoueur, Flotte flotteInitialeAdversaire) {
     
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
            System.out.println(c.get_i() + " " + c.get_j());
            champAdversaire[c.get_i()][c.get_j()] = 'n';
        }

        evenement = "premier tour";
    }

    public Tour (Tour t) {
        this.champJoueur = t.champJoueur;
        this.champAdversaire = t.champAdversaire;
        this.evenement = t.evenement;
    }

    public char[][] getChampJoueur() {
        return champJoueur;
    }

    public void setChampJoueur(char[][] champJoueur) {
        this.champJoueur = champJoueur;
    }

    public char[][] getChampAdversaire() {
        return champAdversaire;
    }

    public void setChampAdversaire(char[][] champAdversaire) {
        this.champAdversaire = champAdversaire;
    }

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    public int setChampsJoueur(Case caseTemporaire, String messageAttaque) {
        // TODO Auto-generated method stub
        return 0;
    }
        
    public int setChampsAdversaire(Case caseTemporaire, String messageAttaque) {
       // TODO Auto-generated method stub
        return 0;
    }
        
}
 
