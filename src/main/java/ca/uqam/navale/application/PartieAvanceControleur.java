package ca.uqam.navale.application; 

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
 
import ca.uqam.navale.domaine.*;
import ca.uqam.navale.fondation.*;
import org.json.simple.parser.ParseException;
 
public class PartieAvanceControleur implements PartieControleur {
 
    Flotte flotteJoueur;
    Flotte flotteAdversaire;
    LocalDateTime heureDebut;
    int nbSecondesPartie;
    TourListe tours;
    TourIterateur tourIter;

    public PartieAvanceControleur() {
 
        // à compléter
    }
    public boolean init() {
    	return false;
        // à compléter
    }
    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId) {
        // à compléter
        return null;
    }
    
    /* Attaque une case adverse et retourne le resultat
     * @param la case adverse cible
     * @return le tour courant mis a jour
     * @see ca.uqam.navale.application.PartieControleur#attaquerAdversaire(ca.uqam.navale.domaine.Case)
     */
    public Tour attaquerAdversaire(Case c) {
    	String messageAttaque = this.flotteAdversaire.attaquer(c);    	
    
        return null;	
   // 	this.tours.getElement(this.tourIter.courant().setChampsAdversaire(c,messageAttaque));        
   //     return this.tourIter.courant();
    }
    
    public Tour getAttaqueAdversaire() {
        // à completer
        return null;
    }
    
    /* Retourne le tour precedent
     * @return le tour precedent
     * @see ca.uqam.navale.application.PartieControleur#getTourPrecedent()
     */
     public Tour getTourPrecedent() {           	
         return this.tourIter.precedent();
     }
     
     /*Retourne le tour suivant
     * @return le tour suivant
      * @see ca.uqam.navale.application.PartieControleur#getTourSuivant()
      */
     public Tour getTourSuivant() {        
         return this.tourIter.suivant();
     }
     
     /* Met a jour le document de record
      * @param nom Le nom du detenteur du nouveau record
      * @param temps Le temps obtenu
      * @see ca.uqam.navale.application.PartieControleur#miseAJourRecords(java.lang.String, int)
      */
     public void miseAJourRecords(String nom) throws IOException, ParseException {
         Records r = EntreeSortieFichier.recupererRecords();
         r.setNomRecordAvance(nom);
         r.setTempsRecordAvance(nbSecondesPartie);
         EntreeSortieFichier.ecrireRecords(r);
      
     }
}

