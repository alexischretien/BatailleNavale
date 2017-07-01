package ca.uqam.navale.presentation;

import java.util.*;
import java.awt.*;
import javax.swing.*;

import ca.uqam.navale.domaine.Case;
import ca.uqam.navale.domaine.Tour;
import ca.uqam.navale.domaine.Records;

public class FenetreNavale extends JFrame {

    private EcouteurFenetreNavale ecouteur;

    JPanel panneauEntete;
    GridLayout gridPanneauEntete = new GridLayout(2, 2);

    JPanel panneauBoutons;
    GridLayout gridPanneauBoutons = new GridLayout(1, 2);
    JButton boutonPartie;
    JButton boutonRecharger;
    JButton boutonRecords;
    JButton boutonFermer;

    public FenetreNavale() {
        ecouteur = new EcouteurFenetreNavale(this);
        init();
    }

    public void activerSauvegarde(boolean b) {

    // à compléter

    }

    public void initFenetreMenu() {
    
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
    private void init() {

        setTitle("Bataille Navale");
        setBounds(500, 230, 500, 300);
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // création du panneau de l'entete
        panneauEntete = new JPanel();
        panneauEntete.setLayout(gridPanneauEntete);

        // création du panneau des boutons
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(gridPanneauBoutons);
        
        boutonPartie    = new JButton("Nouvelle partie");
        boutonRecharger = new JButton("Recharger une partie");
        boutonRecords   = new JButton("Meilleurs temps");
        boutonFermer    = new JButton("Fermer");

        panneauBoutons.add(boutonPartie);
        panneauBoutons.add(boutonRecharger);
        panneauBoutons.add(boutonRecords);
        panneauBoutons.add(boutonFermer);
       
        add(panneauEntete);
        add(panneauBoutons);
    }
}
