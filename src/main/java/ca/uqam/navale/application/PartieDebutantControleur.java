/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * PartieDebutantControleur.java - Fichier source de la classe PartieDebutantControleur
 *                                 offrant les méthodes pour manipuler la logique du
 *                                 du domaine liée à une partie contre ordinateur de
 *                                 niveau debutant.
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.application;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.time.Duration;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.json.simple.parser.ParseException;

import ca.uqam.navale.fondation.*;
import ca.uqam.navale.domaine.*;

@XmlRootElement
public class PartieDebutantControleur implements PartieControleur {
 
    private Flotte flotteJoueur;
    private Flotte flotteAdversaire;
    private LocalDateTime heureDebut;
    private int nbSecondesPartie;
    private Liste<Tour> tours;
    private Iterateur<Tour> tourIter;

    // Constructeur
    public PartieDebutantControleur() {
        nbSecondesPartie = 0;
    	flotteJoueur = new Flotte();
        flotteAdversaire = new Flotte();
        heureDebut = new LocalDateTime();
        tours = new TourListe();
        tourIter = tours.creerIterateur();
    }

   /*
    * Initialise les donnees de la partie
    *
    * @return "true" si le joueur commence, "false" sinon
    */
    public boolean init() {
    
        boolean joueurCommence;
        
        joueurCommence = Math.random() < 0.5;
        flotteAdversaire = Flotte.genererFlotteAleatoire();
        tours.ajouter(new Tour(flotteJoueur, flotteAdversaire));        
        heureDebut = LocalDateTime.now();

        return joueurCommence;
    }
    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId) {

        return flotteJoueur.positionnerNavire(i, j, horizontal, navireId);       
    }
   
    /* Attaque une case adverse et retourne le resultat
     * 
     * @param i  la coordonnee en i de la case de l'attaque
     * @param j  la coordonnee en j de la case de l'attaque
     * @return le tour courant mis a jour
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
                nbSecondesRecord = EntreeSortieFichier.recupererRecords().getTempsRecordDebutant();
                nbSecondesPartie = Seconds.secondsBetween(heureDebut, LocalDateTime.now()).getSeconds();
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
     * Tir au hazard jusqu'a ce qu'il touche une nouvelle case et met a jour le tour courant
     *
     * @return le tour courant mis a jour
     */
    public Tour getAttaqueAdversaire() {

        Case c;
        Tour tour;
    	String evenement;

        do {
             c = new Case((int) (Math.random()* 10), (int) (Math.random()* 10));
             evenement = flotteJoueur.attaquer(c);

    	} while(evenement.equals("Déjà attaquée"));
    	
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

    	return tour;
    }
    
   /* Retourne le tour precedent
    *
    * @return le tour precedent
    */
    public Tour getTourPrecedent() {           	
        return this.tourIter.precedent();
    }
    
    /* Retourne le tour suivant
     *
     * @return le tour suivant
     */
    public Tour getTourSuivant() {        
        return this.tourIter.suivant();
    }
    
    /* Retourne le premier tour
     *
     * @return  le premier tour
     */
    public Tour getPremierTour() {
        return this.tourIter.premier();
    } 

    /* Retourne le dernier tour
     *
     * @return  le dernier tour
     */
    public Tour getDernierTour() { 
        this.tourIter = tours.creerIterateur();  
        return this.tourIter.dernier();
    } 

    /* Met a jour le document de record
     *
     * @param nom   Le nom du detenteur du nouveau record
     */
    public void miseAJourRecords(String nom) throws IOException, ParseException  {
        Records r = EntreeSortieFichier.recupererRecords();
        r.setNomRecordDebutant(nom);
        r.setTempsRecordDebutant(nbSecondesPartie);
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
    public Liste<Tour> getTours() {
        return tours;
    } 
    public Iterateur<Tour> getTourIter() {
        return tourIter;
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
    @XmlElementRefs({@XmlElementRef(type=TourListe.class)})
    public void setTours(Liste<Tour> tours) {
        this.tours = tours;
    }
    @XmlElementRefs({@XmlElementRef(type=TourIterateur.class)})
    public void setTourIter(Iterateur<Tour> tourIter) {
        this.tourIter = tourIter;
    }
}
