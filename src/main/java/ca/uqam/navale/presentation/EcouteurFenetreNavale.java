package ca.uqam.navale.presentation;

import java.util.*;
import java.awt.event.*;
import ca.uqam.navale.application.*;
import ca.uqam.navale.domaine.Case;
import ca.uqam.navale.domaine.Tour;

public class EcouteurFenetreNavale implements ActionListener {

    private FenetreNavale fenetre;
    PartieControleur  partieControleur;
    
    public EcouteurFenetreNavale(FenetreNavale f) {
        this.fenetre = f;
    }

    public void actionPerformed(ActionEvent evt) {
        
        Object source = evt.getSource();

        if (source == fenetre.boutonPartie) {

            if (fenetre.boutonRadioDebutant.isSelected() == true) {
                partieControleur = new PartieDebutantControleur();
            }
            else {
                partieControleur = new PartieAvanceControleur();
            }
            fenetre.initFenetrePlacementNavires();
        } 
        else if (source == fenetre.boutonRecharger) {
            System.out.println("Demande de recharge de partie");
        }
        else if (source == fenetre.boutonRecords) {
            System.out.println("Demande Records");
        }
        else if (source == fenetre.boutonMenu) {
            fenetre.initFenetreMenu();
        }
        else if (source == fenetre.boutonJouer) {

            boolean joueurCommence = partieControleur.init();
            fenetre.initFenetrePartie(joueurCommence);          
          
            if (joueurCommence == false) {
                Tour tour = partieControleur.getAttaqueAdversaire();
                fenetre.miseAJourPartie(tour, true);
            }
            
        }
        else if (source == fenetre.boutonFermer) {
            fenetre.dispose();
        }
        else {
            for (int i = 0 ; i < 10 ; ++i) {
                for (int j = 0 ; j < 10 ; ++j) {
                    if (source == fenetre.grilleJoueur[i][j]) {
                       List<Case> casesOccupees = partieControleur.positionnerNavire(i, j, 
                                                                             fenetre.estNavireHorizontal(), 
                                                                             fenetre.getNavireId()); 
                       fenetre.miseAJourPlacementsNavires(casesOccupees);         
                    }
                    else if (source == fenetre.grilleAdversaire[i][j]) {
                        Tour tour = partieControleur.attaquerAdversaire(new Case(i, j));
                        fenetre.miseAJourPartie(tour, false);
                         
                        if (!tour.getEvenement().equals("partie terminée")) {
                            tour = partieControleur.getAttaqueAdversaire();
                            fenetre.miseAJourPartie(tour, true);
                        }
                    }
                }
            }
        }
    }
}
