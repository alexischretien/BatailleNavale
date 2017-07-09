package ca.uqam.navale.domaine; 

import java.lang.String;
 
public class Records {

    String nomRecordJoueur;
    String nomRecordDebutant;
    String nomRecordAvance;
    int tempsRecordJoueur;
    int tempsRecordDebutant;
    int tempsRecordAvance;


    public Records(String nomRecordJoueur, String nomRecordDebutant, String nomRecordAvance,
                   int tempsRecordJoueur, int tempsRecordDebutant, int tempsRecordAvance) {
        this.nomRecordJoueur = nomRecordJoueur;
        this.nomRecordDebutant = nomRecordDebutant;
        this.nomRecordAvance = nomRecordAvance;
        this.tempsRecordJoueur = tempsRecordJoueur;
        this.tempsRecordDebutant = tempsRecordDebutant;
        this.tempsRecordAvance = tempsRecordAvance;
    } 

    public String getNomRecordJoueur() {
        return nomRecordJoueur;
    }
    public String getNomRecordDebutant() {
        return nomRecordDebutant;
    }
    public String getNomRecordAvance() {
        return nomRecordAvance;
    }
    public int getTempsRecordJoueur() {
        return tempsRecordJoueur;
    }
    public int getTempsRecordDebutant() {
        return tempsRecordDebutant;
    }
    public int getTempsRecordAvance() {
        return tempsRecordAvance;
    }

    
    public void setNomRecordJoueur(String nom) {
        nomRecordJoueur = nom;
    }
    public void setNomRecordDebutant(String nom) {
        nomRecordDebutant = nom;
    }
    public void setNomRecordAvance(String nom) {
        nomRecordAvance = nom;
    }
    public void setTempsRecordJoueur(int temps) {
        tempsRecordJoueur = temps;
    }
    public void setTempsRecordDebutant(int temps) {
        tempsRecordDebutant = temps;
    }
    public void setTempsRecordAvance(int temps) {
        tempsRecordAvance = temps;
    }
}
