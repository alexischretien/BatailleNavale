package ca.uqam.navale.presentation;

import java.util.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.*;

import ca.uqam.navale.domaine.Case;
import ca.uqam.navale.domaine.Tour;
import ca.uqam.navale.domaine.Records;

public class FenetreNavale extends JFrame {

    private static final Color BLEU        = new Color(0, 0, 230);
    private static final Color BLEU_FONCE  = new Color(0, 0, 102);
    private static final Color ROUGE       = new Color(230, 0, 0);
    private static final Color ROUGE_FONCE = new Color(102, 0, 0);
    private static final Color GRIS        = new Color(102, 102, 153);
    private static final Color GRIS_FONCE  = new Color(41, 41, 61);

    private EcouteurFenetreNavale ecouteur;

    GridLayout gridPanneauBoutons;
    JPanel panneauBoutons;
    JPanel panneauGrilleJoueur;
    JPanel panneauGrilleAdversaire;
    JPanel panneauEvenement;
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
    JButton boutonSauvegarde;
    JButton boutonVisualisation;
    JRadioButton boutonHor;
    JRadioButton boutonVer;
    JRadioButton boutonPorteAvion;
    JRadioButton boutonCroiseur;
    JRadioButton boutonContreTorpilleurs;
    JRadioButton boutonSousMarin;
    JRadioButton boutonTorpilleur;
    JButton grilleJoueur[][];
    JButton grilleAdversaire[][];
    JTextArea messageEvenement;

    public FenetreNavale() {

        ecouteur = new EcouteurFenetreNavale(this);

        boutonRadioDebutant = new JRadioButton("Débutant");
        boutonRadioAvance   = new JRadioButton("Avancé");

        boutonPartie    = new JButton("Nouvelle partie");
        boutonRecharger = new JButton("Recharger une partie");
        boutonRecords   = new JButton("Meilleurs temps");
        boutonJouer     = new JButton("Jouer");
        boutonFermer    = new JButton("Fermer");
        boutonSauvegarde = new JButton("Sauvegarder");
        boutonVisualisation = new JButton("Visualiser la partie");
        boutonTourPrecedent = new JButton("Tour précédent");
        boutonTourSuivant   = new JButton("Tour suivant");
        boutonMenu = new JButton("Retour au menu");
        grilleJoueur = new JButton[10][10];
        grilleAdversaire = new JButton[10][10];  
        messageEvenement = new JTextArea();
   
        boutonRadioDebutant.addActionListener(ecouteur);
        boutonRadioAvance.addActionListener(ecouteur);

        boutonPartie.addActionListener(ecouteur);
        boutonRecharger.addActionListener(ecouteur);
        boutonRecords.addActionListener(ecouteur);
        boutonJouer.addActionListener(ecouteur);
        boutonFermer.addActionListener(ecouteur);
        boutonSauvegarde.addActionListener(ecouteur);
        boutonVisualisation.addActionListener(ecouteur);
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

    // Pas à implémenter pour l'instant, ne sert que pour les parties en ligne.
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
        panneauBoutons.setLayout(new GridLayout(1, 1));        
        
        JTextArea message = new JTextArea(
                        "Veuillez placer les 5 navires sur la grille.\n Les navires ne peuvent " +
                        "se chevaucher.\nLa case sélectionnée pour le placement sera la case\nla " +
                        "plus au nord ou la plus à l'ouest dépendamment\nde l'orientation du navire.");
        message.setEditable(false);
 
        JPanel panneauTexte = new JPanel();
        panneauTexte.setLayout(new GridLayout(1,1));
        panneauTexte.add(message);

        JPanel panneauOrientation = new JPanel();
        panneauOrientation.setLayout(new GridLayout(2, 1));
        panneauOrientation.setBorder(new TitledBorder("Orientation"));
 
        panneauOrientation.add(boutonHor);
        panneauOrientation.add(boutonVer);
 
        JPanel panneauNavires = new JPanel();
        panneauNavires.setLayout(new GridLayout(5, 1));
        panneauNavires.setBorder(new TitledBorder("Navires"));

        panneauNavires.add(boutonPorteAvion);
        panneauNavires.add(boutonCroiseur);
        panneauNavires.add(boutonContreTorpilleurs);
        panneauNavires.add(boutonSousMarin);
        panneauNavires.add(boutonTorpilleur);

        JPanel panneauRadio = new JPanel();
        panneauRadio.setLayout(new GridLayout(1,2)); 

        panneauRadio.add(panneauNavires);
        panneauRadio.add(panneauOrientation);
       
        boutonJouer.setEnabled(false);

        panneauBoutons.add(boutonJouer);
        panneauBoutons.add(boutonMenu);
         
        panneauGrilleJoueur = new JPanel();
        panneauGrilleJoueur.setLayout(new GridLayout(10, 10));
        panneauGrilleJoueur.setBorder(new TitledBorder("Votre grille"));
        
        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                grilleJoueur[i][j] = new JButton();
                grilleJoueur[i][j].setBackground(BLEU);
                grilleJoueur[i][j].setPreferredSize(new Dimension(30,30));
                grilleJoueur[i][j].addActionListener(ecouteur);
                panneauGrilleJoueur.add(grilleJoueur[i][j]);
            }
        }
        setLayout(new GridLayout(2, 2));
        add(panneauTexte);
        add(panneauRadio);
        add(panneauGrilleJoueur);
        add(panneauBoutons);
        pack();
    }

    public void initFenetrePartie(boolean joueurCommence) {
           
        getContentPane().removeAll();
       
        panneauGrilleAdversaire = new JPanel();
        panneauGrilleAdversaire.setLayout(new GridLayout(10, 10));
        panneauGrilleAdversaire.setBorder(new TitledBorder("Grille de l'adversaire"));

        for(int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                grilleJoueur[i][j].setEnabled(false);
                grilleJoueur[i][j].removeActionListener(ecouteur);
                grilleAdversaire[i][j] = new JButton();
                grilleAdversaire[i][j].setBackground(ROUGE);
                grilleAdversaire[i][j].setPreferredSize(new Dimension(30,30));
                grilleAdversaire[i][j].addActionListener(ecouteur);
                grilleAdversaire[i][j].setEnabled(joueurCommence);               
                
                panneauGrilleAdversaire.add(grilleAdversaire[i][j]);
            }
        }
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new GridLayout(1, 3));
        
        panneauBoutons.add(boutonSauvegarde);
        panneauBoutons.add(boutonMenu);
        panneauBoutons.add(boutonVisualisation);
        
        boutonVisualisation.setEnabled(false);
    
        panneauEvenement = new JPanel();
        panneauEvenement.setLayout(new GridLayout(1,1));
        panneauEvenement.setBorder(new TitledBorder("Événement"));
        panneauEvenement.add(messageEvenement);

        setLayout(new GridLayout(2,2));
        add(panneauGrilleJoueur);
        add(panneauGrilleAdversaire);
        add(panneauBoutons);
        add(panneauEvenement);
        pack();
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
                grilleJoueur[i][j].setBackground(Color.blue);
            }
        }
        for (Case c : casesOccupees) {
            grilleJoueur[c.get_i()][c.get_j()].setBackground(GRIS);
        }

        boutonJouer.setEnabled(casesOccupees.size() == 17);
    }

    public void miseAJourPartie(Tour tour, boolean auTourDuJoueur) {
        
        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                
                grilleAdversaire[i][j].setEnabled(auTourDuJoueur); 
                
                if (tour.getChampJoueur()[i][j] == 'd') {
                    grilleJoueur[i][j].setBackground(BLEU_FONCE);
                }
                else if (tour.getChampJoueur()[i][j] == 't') {
                    grilleJoueur[i][j].setBackground(GRIS_FONCE);
                }

                if (tour.getChampAdversaire()[i][j] == 'd') {
                    grilleAdversaire[i][j].setBackground(ROUGE_FONCE);
                    grilleAdversaire[i][j].setEnabled(false);
                }
                else if (tour.getChampAdversaire()[i][j] == 't') {
                    grilleAdversaire[i][j].setBackground(GRIS_FONCE);
                    grilleAdversaire[i][j].setEnabled(false);
                }
            }
        }
        if (!auTourDuJoueur) {
            messageEvenement.setText(tour.getEvenement());
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
