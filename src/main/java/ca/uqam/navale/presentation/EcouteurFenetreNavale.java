package ca.uqam.navale.presentation;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.*;
import javax.xml.bind.JAXBException;
import javax.swing.JOptionPane;
import ca.uqam.navale.application.*;
import ca.uqam.navale.domaine.Case;
import ca.uqam.navale.domaine.Tour;
import ca.uqam.navale.domaine.Records;
import ca.uqam.navale.fondation.EntreeSortieFichier;
import org.json.simple.parser.ParseException;

public class EcouteurFenetreNavale implements ActionListener {

    private static final int   NAVIRE_INVALIDE_ID    = -1;
    private static final int   PORTE_AVION_ID        =  0;
    private static final int   CROISEUR_ID           =  1;
    private static final int   CONTRE_TORPILLEURS_ID =  2;
    private static final int   SOUS_MARIN_ID         =  3;
    private static final int   TORPILLEUR_ID         =  4;

    private FenetreNavale fenetre;
    PartieControleur  partieControleur;
    
    public EcouteurFenetreNavale(FenetreNavale f) {
        this.fenetre = f;
    }

    public void actionPerformed(ActionEvent evt) {
        
        Object source = evt.getSource();

        // lancement d'une partie
        if (source == fenetre.getBoutonDemarrer()) {

            if (fenetre.getBoutonRadioDebutant().isSelected()) {
                partieControleur = new PartieDebutantControleur();
                fenetre.initFenetrePlacementNavires();
            }
            else if (fenetre.getBoutonRadioAvance().isSelected()) {
                partieControleur = new PartieAvanceControleur();
                fenetre.initFenetrePlacementNavires();
            }
            else {
                JOptionPane.showMessageDialog(fenetre, "Le jeu en ligne n'est pas " +
                                                       "encore implémenté");
            }
        } 
        // Sauvegarde de la partie
        else if (source == fenetre.getBoutonSauvegarde()) {
            try {
                EntreeSortieFichier.ecrireSauvegarde(partieControleur);
                JOptionPane.showMessageDialog(fenetre, "Partie sauvegardée");
            } 
            catch (IOException e) {
                Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, e);
            }
            catch (JAXBException e) {
                Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        // recharger une partie sauvegardée
        else if (source == fenetre.getBoutonRecharger()) {
            
            partieControleur = EntreeSortieFichier.chargerSauvegarde();
                  
            if (partieControleur == null) {
                JOptionPane.showMessageDialog(fenetre, "Aucune partie n'a été sauvegardée");
            }
            else {
                fenetre.initFenetrePartie();
                fenetre.miseAJourPartie(partieControleur.getDernierTour(), true);
            }   
        }
        // voir les records
        else if (source == fenetre.getBoutonRecords()) {
            try {
                Records records = EntreeSortieFichier.recupererRecords();
                fenetre.initFenetreRecords(records);
            }
            catch(IOException e) {
                Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, e);
            }
            catch(ParseException e) {
                Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        // Retour au menu
        else if (source == fenetre.getBoutonMenu()) {
            fenetre.initFenetreMenu();
        }
        // lancement de la partie apres positionnement des navires
        else if (source == fenetre.getBoutonJouer()) {

            boolean joueurCommence = partieControleur.init();
            fenetre.initFenetrePartie();          
            fenetre.miseAJourPartie(partieControleur.getDernierTour(), joueurCommence);
            if (joueurCommence == false) {
                Tour tour = partieControleur.getAttaqueAdversaire();
                fenetre.miseAJourPartie(tour, true);
            }
        }
        // début de la visualisation de la partie
        else if (source == fenetre.getBoutonVisualisation()) {
            fenetre.initFenetreVisualisation();
            fenetre.miseAJourVisualisation(partieControleur.getPremierTour());
        }
        // visualisation du tour précédent
        else if (source == fenetre.getBoutonTourPrecedent()) {
            fenetre.miseAJourVisualisation(partieControleur.getTourPrecedent());
        }
        // visualisation du tour suivant
        else if (source == fenetre.getBoutonTourSuivant()) {
            fenetre.miseAJourVisualisation(partieControleur.getTourSuivant());
        }
        // fermeture du programme
        else if (source == fenetre.getBoutonFermer()) {
            fenetre.dispose();
        }
        else {
            for (int i = 0 ; i < 10 ; ++i) {
                for (int j = 0 ; j < 10 ; ++j) {
                    // placement d'un navire en debutant de partie
                    if (source == fenetre.getGrilleJoueur(i, j)) {
                       List<Case> casesOccupees = partieControleur.positionnerNavire(i, j, 
                                                                              estNavireHorizontal(), 
                                                                              getNavireId()); 
                       fenetre.miseAJourPlacementsNavires(casesOccupees);        
                       return; 
                    }
                    // le joueur attaque une case de l'adversaire
                    else if (source == fenetre.getGrilleAdversaire(i, j)) {
                        Tour tour = partieControleur.attaquerAdversaire(i, j);
                        fenetre.miseAJourPartie(tour, false);
                         
                        if (!tour.estDernierTour()) {
                            tour = partieControleur.getAttaqueAdversaire();
                            fenetre.miseAJourPartie(tour, true);
                        }
                        else if (tour.getEvenement().equals("Nouveau record")) {
                            String nom = JOptionPane.showInputDialog(fenetre,
                                                 "Vous avez battu le meilleur temps!\n " +
                                                 "Veuillez entrer votre nom.", null);
                            if (nom == null) {
                                nom = "Sans nom";
                            }
                            try {
                                partieControleur.miseAJourRecords(nom);
                            }
                            catch(IOException e) {
                                Logger.getLogger(EntreeSortieFichier.class.getName())
                                                          .log(Level.SEVERE, null, e);
                            }
                            catch(ParseException e) {
                                Logger.getLogger(EntreeSortieFichier.class.getName())
                                                         .log(Level.SEVERE, null, e);
                                
                            }
                        }
                        return;
                    }
                }
            }
        }
    }

    private boolean estNavireHorizontal() {

        return fenetre.getBoutonHor().isSelected();
    }

    private int getNavireId() {

        if (fenetre.getBoutonPorteAvion().isSelected())        return PORTE_AVION_ID;
        if (fenetre.getBoutonCroiseur().isSelected())          return CROISEUR_ID;
        if (fenetre.getBoutonContreTorpilleurs().isSelected()) return CONTRE_TORPILLEURS_ID;
        if (fenetre.getBoutonSousMarin().isSelected())         return SOUS_MARIN_ID;
        if (fenetre.getBoutonTorpilleur().isSelected())        return TORPILLEUR_ID;
        return NAVIRE_INVALIDE_ID;
    }

}
