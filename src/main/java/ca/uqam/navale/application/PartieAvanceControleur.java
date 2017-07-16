package ca.uqam.navale.application;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.time.Duration;
import javax.xml.bind.annotation.*;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.json.simple.parser.ParseException;

import ca.uqam.navale.fondation.*;
import ca.uqam.navale.domaine.*;

@XmlRootElement
public class PartieAvanceControleur implements PartieControleur {
 
    private Flotte flotteJoueur;
    private Flotte flotteAdversaire;
    private LocalDateTime heureDebut;
    private int nbSecondesPartie;
    private TourListe tours;
    private TourIterateur tourIter;
    private List<Case>  attaquesAdversaire;
    private boolean joueurCommence;

    public PartieAvanceControleur() {
        nbSecondesPartie = 0;
    	flotteJoueur = new Flotte();
        flotteAdversaire = new Flotte();
        heureDebut = new LocalDateTime();
        tours = new TourListe();
        tourIter = tours.creerIterateur();
        attaquesAdversaire= new ArrayList<Case>();
    }

    public boolean init() {
    
        
        setJoueurCommence(Math.random() < 0.5);
        joueurCommence = getJoueurCommence();
        flotteAdversaire = Flotte.genererFlotteAleatoire();
        tours.ajouter(new Tour(flotteJoueur, flotteAdversaire));        
        heureDebut = LocalDateTime.now();

        return joueurCommence;
    }
    

	public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId) {

        return flotteJoueur.positionnerNavire(i, j, horizontal, navireId);       
    }
   
    /* Attaque une case adverse et retourne le resultat
     * @param la case adverse cible
     * @return le tour courant mis a jour
     * @see ca.uqam.navale.application.PartieControleur#attaquerAdversaire(ca.uqam.navale.domaine.Case)
     */
    public Tour attaquerAdversaire(int i, int j) {

        Tour tour;
    	String evenement;
    	   	
        evenement = flotteAdversaire.attaquer(new Case(i, j));    	
    	tour = new Tour(tourIter.dernier());    
        tour.setEvenement(evenement);

        if (evenement.equals("Dans l'eau")) {
            tour.setChampAdversaire(i, j, 'd');
        }
        else if (evenement.equals("Touché") || evenement.equals("Coulé")) {
            tour.setChampAdversaire(i, j, 't');
        }
        else if (evenement.equals("Partie terminée")) {

            int nbSecondesRecord = 0;

            try {
                nbSecondesPartie = Seconds.secondsBetween(heureDebut, LocalDateTime.now()).getSeconds();
                nbSecondesRecord = EntreeSortieFichier.recupererRecords().getTempsRecordAvance();
            
            }
            catch (IOException e) {
                Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, e);
            }
            catch (ParseException e) {
                Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, e);
            }

            if (nbSecondesPartie < nbSecondesRecord) {
                tour.setEvenement("Nouveau record");
            }
            else {
                tour.setEvenement("Vous avez gagné");
            }
            tour.setChampAdversaire(i, j, 't');    
        }
        tours.ajouter(tour);
        return tour;
    }
   
    /*
     * Tir au hazard jusqu'a ce qu'il touche un navire
     * Lorsqu'il touche un navire, il cherche les cases environnantes jusqu'a ce qu'il coule un navire
     * @return le tour courant mis a jour
     * @see ca.uqam.navale.application.PartieControleur#getAttaqueAdversaire()
     */
    public Tour getAttaqueAdversaire() {

        Case c = null;
        Tour tour;
    	String evenement ="Déjà attaquée";
        List<Case>  attaquesPrecedentes;
        Tour tourPrecedent;
        boolean attaqueEncours=false;
        int i=0;
    	
    	attaquesPrecedentes=getAttaquesAdverse();
    	
    	if(attaquesPrecedentes.size()-1<0){    		
    		c = new Case((int) (Math.random()* 10), (int) (Math.random()* 10));
            evenement = flotteJoueur.attaquer(c);
            attaqueEncours=true;
    	}    	
    	else{
    	tourPrecedent= tourIter.courant();
    	i = attaquesPrecedentes.size()-1;
    		while(i>=0 && !attaqueEncours){
    			if(!getTourPrecedent().estPremierTour()){   				
    				attaquesPrecedentes.get(i);    				
    				if(tourPrecedent.estPremierTour()){
    					if(joueurCommence){
    						tourPrecedent=getTourSuivant();
    						tourPrecedent=getTourSuivant(); 
    					}
    					else{
    						tourPrecedent=getTourSuivant(); ;
    					}    					    					
    				}    				
    				if(tourPrecedent.getEvenement().equals("Coulé")){        				
        				i=-1;
        			}
        			else if(tourPrecedent.getEvenement().equals("Touché")){        				
        				 if(attaquesPrecedentes.get(i).get_i()-1>=0 ){
        					c = new Case(attaquesPrecedentes.get(i).get_i()-1, attaquesPrecedentes.get(i).get_j());
        					evenement = flotteJoueur.attaquer(c);        					
        					if(!evenement.equals("Déjà attaquée")){        						
        						attaqueEncours=true;
        					}    	                 
        				 }
        				 if(attaquesPrecedentes.get(i).get_j()-1>=0 && !attaqueEncours){
        					c = new Case(attaquesPrecedentes.get(i).get_i(), attaquesPrecedentes.get(i).get_j()-1);
        					evenement = flotteJoueur.attaquer(c);        					
        					if(!evenement.equals("Déjà attaquée")){        						
        						attaqueEncours=true;
        					}
        				 }    				
        				 if(attaquesPrecedentes.get(i).get_i()+1<10 && !attaqueEncours){
        					c = new Case(attaquesPrecedentes.get(i).get_i()+1, attaquesPrecedentes.get(i).get_j());
        					evenement = flotteJoueur.attaquer(c);        					
        					if(!evenement.equals("Déjà attaquée")){        						
        						attaqueEncours=true;
        					}
        				 }    				
        				 if(attaquesPrecedentes.get(i).get_j()+1<10 && !attaqueEncours){
        					c = new Case(attaquesPrecedentes.get(i).get_i(), attaquesPrecedentes.get(i).get_j()+1);
        					evenement = flotteJoueur.attaquer(c);        					
        					if(!evenement.equals("Déjà attaquée")){        						
        						attaqueEncours=true;
        					}
        				 }        				 
        			}        			
    			}
    			--i;
    			tourPrecedent=getTourPrecedent();
    		}
    		if(!attaqueEncours){    		    
    			do {
    			       c = new Case((int) (Math.random()* 10), (int) (Math.random()* 10));
    			       evenement = flotteJoueur.attaquer(c);
    			} while(evenement.equals("Déjà attaquée"));
    		}    			    		
    }    	
    tour = new Tour(tourIter.dernier());
    tour.setEvenement(evenement);

    if (evenement.equals("Touché") || evenement.equals("Coulé")) {
        tour.setChampJoueur(c.get_i(), c.get_j(), 't');
    }
    else if (evenement.equals("Partie terminée")) {
        tour.setChampJoueur(c.get_i(), c.get_j(), 't');
        tour.setEvenement("Vous avez perdu");
    }
    else if (evenement.equals("Dans l'eau")) {
        tour.setChampJoueur(c.get_i(), c.get_j(), 'd');
     }
     tours.ajouter(tour);
     attaquesPrecedentes.add(attaquesPrecedentes.size(), c);
     setAttaquesAdverse(attaquesPrecedentes);     
     return tour; 
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
    
    public Tour getPremierTour() {
        return this.tourIter.premier();
    } 
    public Tour getDernierTour() { 
        this.tourIter = tours.creerIterateur();  
        return this.tourIter.dernier();
    } 

    /* Met a jour le document de record
     * @param nom Le nom du detenteur du nouveau record
     * @param temps Le temps obtenu
     * @see ca.uqam.navale.application.PartieControleur#miseAJourRecords(java.lang.String, int)
     */
    public void miseAJourRecords(String nom) throws IOException, ParseException  {
        Records r = EntreeSortieFichier.recupererRecords();
        r.setNomRecordAvance(nom);
        r.setTempsRecordAvance(nbSecondesPartie);
        EntreeSortieFichier.ecrireRecords(r);      
    }

    // getters
    public Flotte getFlotteJoueur() {
        return flotteJoueur;
    } 
    public Flotte getFlotteAdversaire() {
        return flotteAdversaire;
    }
    public LocalDateTime getHeureDebut() {
        return heureDebut;
    } 
    public int getNbSecondesPartie() {
        return nbSecondesPartie;
    }
    public TourListe getTours() {
        return tours;
    } 
    public TourIterateur getTourIter() {
        return tourIter;
    }
    public List<Case> getAttaquesAdverse() {
        return attaquesAdversaire;
    }
    private boolean getJoueurCommence() {		
		return joueurCommence;
	}

    // setters
    @XmlElement
    public void setFlotteJoueur(Flotte flotteJoueur) {
        this.flotteJoueur = flotteJoueur;
    }
    @XmlElement
    public void setFlotteAdversaire(Flotte flotteAdversaire) {
        this.flotteAdversaire = flotteAdversaire;
    }
    @XmlElement
    public void setHeureDebut(LocalDateTime heureDebut) {
        this.heureDebut = heureDebut;
    }
    @XmlElement
    public void setNbSecondesPartie(int nbSecondesPartie) {
        this.nbSecondesPartie = nbSecondesPartie;
    }
    @XmlElement
    public void setTours(TourListe tours) {
        this.tours = tours;
    }
    @XmlElement
    public void setTourIter(TourIterateur tourIter) {
        this.tourIter = tourIter;
    }
    public void setAttaquesAdverse(List<Case> attaquesAdversaire) {
        this.attaquesAdversaire=attaquesAdversaire;
    }
    public void setJoueurCommence(boolean joueurCommence) {
        this.joueurCommence=joueurCommence;
    }
}
