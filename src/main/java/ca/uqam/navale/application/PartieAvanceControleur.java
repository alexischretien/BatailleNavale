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
        // à compléter
    }
    public void init() {
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
        // à compléter
        return null;
    }
    public Tour getTourPrecedent() {
        // à compléter
    	return tours.getElement(tourIter.precedent());
    }
    public Tour getTourSuivant() {
        // à compléter
    	return tours.getElement(tourIter.suivant());
    }
    public void miseAJourRecords(String nom, int temps) {
    	recordAvanceCourant.setNomRecordAvance(nom);
    	recordAvanceCourant.setTempsRecordAvance(temps);
    	EntreeSortieFichier.ecrireRecords(recordAvanceCourant);
        // à compléter
    }
}

