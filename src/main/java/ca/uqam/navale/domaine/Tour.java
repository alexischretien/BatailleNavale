package ca.uqam.navale.domaine;

import java.lang.String;

public class Tour {

    char[][] champJoueur = new char[10][10];
    char[][] champAdversaire = new char[10][10];
    String evenement;

    Tour(char[][] champJoueur, char[][] champAdversaire, String evenement) {
        this.champJoueur = champJoueur;
        this.champAdversaire = champAdversaire;
        this.evenement = evenement;
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
 
