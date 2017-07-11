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

    private FenetreNavale fenetre;
    PartieControleur  partieControleur;
    
    public EcouteurFenetreNavale(FenetreNavale f) {
        this.fenetre = f;
    }

    public void actionPerformed(ActionEvent evt) {
        
        Object source = evt.getSource();

        // lancement d'une partie
        if (source == fenetre.boutonPartie) {

            if (fenetre.boutonRadioDebutant.isSelected() == true) {
                partieControleur = new PartieDebutantControleur();
            }
            else {
                partieControleur = new PartieAvanceControleur();
            }
            fenetre.initFenetrePlacementNavires();
        } 
        // Sauvegarde de la partie
        else if (source == fenetre.boutonSauvegarde) {
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
        else if (source == fenetre.boutonRecharger) {
            System.out.println("Demande de recharge de partie");
        }
        // voir les records
        else if (source == fenetre.boutonRecords) {
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
        else if (source == fenetre.boutonMenu) {
            fenetre.initFenetreMenu();
        }
        // lancement de la partie apres positionnement des navires
        else if (source == fenetre.boutonJouer) {

            boolean joueurCommence = partieControleur.init();
            fenetre.initFenetrePartie(joueurCommence);          
          
            if (joueurCommence == false) {
                Tour tour = partieControleur.getAttaqueAdversaire();
                fenetre.miseAJourPartie(tour, true);
            }
        }
        // fermeture du programme
        else if (source == fenetre.boutonFermer) {
            fenetre.dispose();
        }
        else {
            for (int i = 0 ; i < 10 ; ++i) {
                for (int j = 0 ; j < 10 ; ++j) {
                    // placement d'un navire en debutant de partie
                    if (source == fenetre.grilleJoueur[i][j]) {
                       List<Case> casesOccupees = partieControleur.positionnerNavire(i, j, 
                                                                  fenetre.estNavireHorizontal(), 
                                                                  fenetre.getNavireId()); 
                       fenetre.miseAJourPlacementsNavires(casesOccupees);        
                       return; 
                    }
                    // le joueur attaque une case de l'adversaire
                    else if (source == fenetre.grilleAdversaire[i][j]) {
                        Tour tour = partieControleur.attaquerAdversaire(new Case(i, j));
                        fenetre.miseAJourPartie(tour, false);
                         
                        if (!tour.partieEstTerminee()) {
                            tour = partieControleur.getAttaqueAdversaire();
                            fenetre.miseAJourPartie(tour, !tour.partieEstTerminee());
                        }
                        else if (tour.getEvenement() == "Nouveau record") {
                            String nom = JOptionPane.showInputDialog(fenetre,
                                                 "Vous avez battu le meilleur temps!\n " +
                                                 "Veuillez entrer votre nom.", null);
                            if (nom != null) {
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
                        }
                        return;
                    }
                }
            }
        }
    }
}
