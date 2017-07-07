package ca.uqam.navale.application; 

import java.util.*;
import java.lang.String;
import java.time.LocalDateTime;
 
import ca.uqam.navale.domaine.*;
import ca.uqam.navale.fondation.*;

 
public class PartieAvanceControleur implements PartieControleur {
 
    Flotte flotteJoueur;
    Flotte flotteAdversaire;
    LocalDateTime heureDebut;
    LocalDateTime heureFin;
    int nbSecondesPartie;
    boolean joueurCommence;
    TourListe tours;
    TourIterateur tourIter;
    Records recordAvanceCourant;

    public PartieAvanceControleur() {
    	init();
        // à compléter
    }
    public void init() {
    	this.joueurCommence= Math.random()< 0.5;
        // à compléter
    }
    public Tour initFlottes(List<List<Case>> flotte) {
        // à compléter
        return null;
    }
    public Tour attaquerAdversaire(Case c) {
        // à compléter
        return null;
    }
    public Tour getAttaqueAdversaire() {
        // à completer
        return null;
    }
    public Tour getTourPrecedent() {
        // à completer
    	return tourIter.precedent();
    }
    public Tour getTourSuivant() {
        // à compléter
    	return tourIter.suivant();
    }
    public void miseAJourRecords(String nom, int temps) {
    	this.recordAvanceCourant.setNomRecordAvance(nom);
    	this.recordAvanceCourant.setTempsRecordAvance(temps);
    	EntreeSortieFichier.ecrireRecords(this.recordAvanceCourant);
        // à compléter
    }
}

