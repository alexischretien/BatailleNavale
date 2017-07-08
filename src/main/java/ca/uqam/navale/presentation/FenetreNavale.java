package ca.uqam.navale.presentation;

import java.util.*;
import java.awt.*;
import javax.swing.*;

import ca.uqam.navale.domaine.Case;
import ca.uqam.navale.domaine.Tour;
import ca.uqam.navale.domaine.Records;

public class FenetreNavale extends JFrame {

    private EcouteurFenetreNavale ecouteur;

    JRadioButton boutonRadioDebutant;
    JRadioButton boutonRadioAvance;
    JButton boutonPartie;
    JButton boutonRecharger;
    JButton boutonRecords;
    JButton boutonFermer;
    
    public FenetreNavale() {
        ecouteur = new EcouteurFenetreNavale(this);
        initFenetreMenu();
    }

    public void activerSauvegarde(boolean b) {

    // à compléter

    }

    public void initFenetreAttente() {

    // à compléter
    }

    public void initFenetrePlacementNavire() {
    
    // à compléter
    }

    public void initFenetrePartie(Tour tour, boolean auTourDuJoueur) {
    
    // à compléter
    }

    public void initFenetreFinPartie(Tour tour, boolean gagne) {
        
    // à compléter
    }

    public void initFenetreEntrerNom() {

    // à compléter
    }

    public void initFenetreRecords(Records records) {

    // à compléter
    }

    public void initFenetreVisualisationTour(Tour tour) {

    // à compléter
    }
    private void initFenetreMenu() {

        JPanel panneauBoutons = new JPanel();
        GridLayout gridPanneauBoutons = new GridLayout(1, 5);   

        JPanel panneauBoutonsRadio = new JPanel();
        GridLayout gridPanneauBoutonsRadio = new GridLayout(1, 2);

        ButtonGroup boutonsRadio = new ButtonGroup();

        boutonRadioDebutant = new JRadioButton("Débutant");
        boutonRadioAvance   = new JRadioButton("Avancé");

        boutonPartie    = new JButton("Nouvelle partie");
        boutonRecharger = new JButton("Recharger une partie");
        boutonRecords   = new JButton("Meilleurs temps");
        boutonFermer    = new JButton("Fermer");

        setTitle("Bataille Navale");
        setBounds(400, 200, 1024, 720);
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panneauBoutons.setLayout(gridPanneauBoutons);
 
        boutonRadioDebutant.setSelected(true);
       
        boutonsRadio.add(boutonRadioDebutant);
        boutonsRadio.add(boutonRadioAvance);

        panneauBoutonsRadio.add(boutonRadioDebutant);
        panneauBoutonsRadio.add(boutonRadioAvance);

        panneauBoutons.add(boutonPartie);
        panneauBoutons.add(panneauBoutonsRadio);
        panneauBoutons.add(boutonRecharger);
        panneauBoutons.add(boutonRecords);
        panneauBoutons.add(boutonFermer);

        add(panneauBoutons, BorderLayout.SOUTH);
        pack(); 

    }
}
