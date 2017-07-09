package ca.uqam.navale.presentation;

import java.util.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import ca.uqam.navale.domaine.Case;
import ca.uqam.navale.domaine.Tour;
import ca.uqam.navale.domaine.Records;

public class FenetreNavale extends JFrame {

    private EcouteurFenetreNavale ecouteur;

    GridLayout gridPanneauBoutons;
    JPanel panneauBoutons;
    JRadioButton boutonRadioDebutant;
    JRadioButton boutonRadioAvance;
    JButton boutonPartie;
    JButton boutonRecharger;
    JButton boutonRecords;
    JButton boutonJouer;
    JButton boutonTourPrecedent;
    JButton boutonTourSuivant;
    JButton boutonMenu;
    JButton boutonFermer;
    JRadioButton boutonHor;
    JRadioButton boutonVer;
    JRadioButton boutonPorteAvion;
    JRadioButton boutonCroiseur;
    JRadioButton boutonContreTorpilleurs;
    JRadioButton boutonSousMarin;
    JRadioButton boutonTorpilleur;
    JButton grilleJoueur[][] = new JButton[10][10]; 
   
    public FenetreNavale() {

        ecouteur = new EcouteurFenetreNavale(this);

        boutonRadioDebutant = new JRadioButton("Débutant");
        boutonRadioAvance   = new JRadioButton("Avancé");

        boutonPartie    = new JButton("Nouvelle partie");
        boutonRecharger = new JButton("Recharger une partie");
        boutonRecords   = new JButton("Meilleurs temps");
        boutonJouer     = new JButton("Jouer");
        boutonFermer    = new JButton("Fermer");
        boutonTourPrecedent = new JButton("Tour précédent");
        boutonTourSuivant   = new JButton("Tour suivant");
        boutonMenu = new JButton("Retour au menu");

        boutonRadioDebutant.addActionListener(ecouteur);
        boutonRadioAvance.addActionListener(ecouteur);

        boutonPartie.addActionListener(ecouteur);
        boutonRecharger.addActionListener(ecouteur);
        boutonRecords.addActionListener(ecouteur);
        boutonJouer.addActionListener(ecouteur);
        boutonFermer.addActionListener(ecouteur);
        boutonTourPrecedent.addActionListener(ecouteur);
        boutonTourSuivant.addActionListener(ecouteur);
        boutonMenu.addActionListener(ecouteur);

        setTitle("Bataille Navale");
        setBounds(400, 200, 1024, 720);
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initFenetreMenu();
    }

    public void activerSauvegarde(boolean b) {

    // à compléter

    }

    public void initFenetreAttente() {

    // à compléter
    }

    public void initFenetrePlacementNavires() {
    
        getContentPane().removeAll();

        
        ButtonGroup boutonsNavires = new ButtonGroup();
        ButtonGroup boutonsOrientation = new ButtonGroup();

        boutonPorteAvion = new JRadioButton("Porte-avion (5 cases)");
        boutonCroiseur = new JRadioButton("Croiseur (4 cases)");
        boutonContreTorpilleurs = new JRadioButton("Contre-torpilleurs (3 cases)");
        boutonSousMarin = new JRadioButton("Sous-marin (3 cases)");
        boutonTorpilleur = new JRadioButton("Torpilleur (2 cases)");

        boutonPorteAvion.setSelected(true); 

        boutonsNavires.add(boutonPorteAvion);
        boutonsNavires.add(boutonCroiseur);
        boutonsNavires.add(boutonContreTorpilleurs);
        boutonsNavires.add(boutonSousMarin);
        boutonsNavires.add(boutonTorpilleur);

        boutonHor = new JRadioButton("Horizontal");
        boutonVer = new JRadioButton("Vertical");

        boutonHor.setSelected(true);

        boutonsOrientation.add(boutonHor);
        boutonsOrientation.add(boutonVer);

        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new GridLayout(1, 2));        


        JPanel panneauOrientation = new JPanel();
        panneauOrientation.setLayout(new GridLayout(3, 1));

        panneauOrientation.add(new JLabel("Orientation"));
        panneauOrientation.add(boutonHor);
        panneauOrientation.add(boutonVer);
 
        JPanel panneauNavires = new JPanel();
        panneauNavires.setLayout(new GridLayout(6, 1));

        panneauNavires.add(new JLabel("Navires"));
        panneauNavires.add(boutonPorteAvion);
        panneauNavires.add(boutonCroiseur);
        panneauNavires.add(boutonContreTorpilleurs);
        panneauNavires.add(boutonSousMarin);
        panneauNavires.add(boutonTorpilleur);
        
        boutonJouer.setEnabled(false);

        panneauBoutons.add(boutonJouer);
        panneauBoutons.add(boutonMenu);
         
        JPanel panneauGrilleJoueur = new JPanel();
        panneauGrilleJoueur.setLayout(new GridLayout(10, 10));

        
        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
             //   System.out.println("i = " + i + ", j = " + j);
                grilleJoueur[i][j] = new JButton();
                grilleJoueur[i][j].setBackground(Color.blue);
                grilleJoueur[i][j].setPreferredSize(new Dimension(30,30));
                grilleJoueur[i][j].addActionListener(ecouteur);
                panneauGrilleJoueur.add(grilleJoueur[i][j]);
            }
        }
        setLayout(new GridLayout(2, 2));
        add(panneauNavires);
        add(panneauOrientation); 
        add(panneauGrilleJoueur);
        add(panneauBoutons, BorderLayout.SOUTH);
        pack();
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
    public void initFenetreMenu() {
       
        getContentPane().removeAll();

        panneauBoutons = new JPanel();
        gridPanneauBoutons = new GridLayout(1, 5);   
        panneauBoutons.setLayout(gridPanneauBoutons);

        JPanel panneauBoutonsRadio = new JPanel();
        GridLayout gridPanneauBoutonsRadio = new GridLayout(2, 1);
        panneauBoutonsRadio.setLayout(gridPanneauBoutonsRadio);

        ButtonGroup boutonsRadio = new ButtonGroup();

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
    
    public void miseAJourPlacementsNavires(List<Case> casesOccupees) {

        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                System.out.println(i + " " + j);
                grilleJoueur[i][j].setBackground(Color.blue);
            }
        }
        for (Case c : casesOccupees) {
            grilleJoueur[c.get_i()][c.get_j()].setBackground(Color.gray);
        }
    }

    public boolean estNavireHorizontal() {
        
        return boutonHor.isSelected(); 
    }

    public int getNavireId() {
        
        if (boutonPorteAvion.isSelected()) return 0;
        if (boutonCroiseur.isSelected()) return 1;
        if (boutonContreTorpilleurs.isSelected()) return 2;
        if (boutonSousMarin.isSelected()) return 3;
        if (boutonTorpilleur.isSelected()) return 4;
        return -1;
    }
}
