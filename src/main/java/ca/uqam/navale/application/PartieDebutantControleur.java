package ca.uqam.navale.application;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

import ca.uqam.navale.domaine.*;
import ca.uqam.navale.fondation.*;
import org.json.simple.parser.ParseException;

public class PartieDebutantControleur implements PartieControleur {
 
    private Flotte flotteJoueur;
    private Flotte flotteAdversaire;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private boolean joueurCommence;
    private TourListe tours;
    private TourIterateur tourIter;

    public PartieDebutantControleur() {
    	init();
    	
    	
        // à compléter
    }
    public void init() {
        flotteJoueur = new Flotte();
        flotteAdversaire = new Flotte();
        tours = new TourListe();
        tourIter = tours.creerIterateur();
    	joueurCommence= Math.random()< 0.5;    	
        
    }
    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId) {

        return flotteJoueur.positionnerNavire(i, j, horizontal, navireId);

       
    }
    
    /* Attaque une case adverse et retourne le resultat
     * @param la case adverse cible
     * @return le tour courant mis a jour
     * @see ca.uqam.navale.application.PartieControleur#attaquerAdversaire(ca.uqam.navale.domaine.Case)
     */
    public Tour attaquerAdversaire(Case c) {
    	String messageAttaque = this.flotteAdversaire.attaquer(c);    	
    	
    	this.tours.getElement(this.tourIter.courant().setChampsAdversaire(c,messageAttaque));        
        return this.tourIter.courant();
    }
   
    /*
     * Tir au hazard jusqu'a ce qu'il touche une nouvelle case et met a jour le tour courant
     * @return le tour courant mis a jour
     * @see ca.uqam.navale.application.PartieControleur#getAttaqueAdversaire()
     */
    public Tour getAttaqueAdversaire() {
    	String messageAttaque = "deja attaquer";
    	Case caseTemporaire=new Case((int) (Math.random()* 10), (int) (Math.random()* 10));
    	
    	while(messageAttaque=="deja attaquer"){
    		caseTemporaire.set_i((int) (Math.random()* 10));
        	caseTemporaire.set_j((int) (Math.random()* 10));
        	messageAttaque=this.flotteJoueur.attaquer(caseTemporaire);
    	}
    	
    	this.tours.getElement(this.tourIter.courant().setChampsJoueur(caseTemporaire,messageAttaque));        
        return this.tourIter.courant();
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
    public void miseAJourRecords(String nom, int temps) throws IOException, ParseException  {
        Records r = EntreeSortieFichier.recupererRecords();
        r.setNomRecordDebutant(nom);
        r.setTempsRecordDebutant(temps);
        EntreeSortieFichier.ecrireRecords(r);  
        
    }
}
