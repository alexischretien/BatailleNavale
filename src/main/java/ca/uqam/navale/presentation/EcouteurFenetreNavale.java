package ca.uqam.navale.presentation;

import java.awt.event.*;
import ca.uqam.navale.application.*;

public class EcouteurFenetreNavale implements ActionListener {

    private FenetreNavale fenetre;
    PartieControleur  partieControleur;
    
    public EcouteurFenetreNavale(FenetreNavale f) {
        this.fenetre = f;
    }

    public void actionPerformed(ActionEvent evt) {
        
        Object source = evt.getSource();

        if (source == fenetre.boutonPartie) {
            System.out.println("Demande de jouer partie");
        } 
        else if (source == fenetre.boutonRecharger) {
            System.out.println("Demande de recharge de partie");
        }
        else if (source == fenetre.boutonRecords) {
            System.out.println("Demande Records");
        }
        else if (source == fenetre.boutonFermer) {
            fenetre.dispose();
        }
    }
}
