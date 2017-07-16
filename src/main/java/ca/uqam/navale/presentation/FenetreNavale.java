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

    private static final Color COULEUR_CASE_INVALIDE = new Color(0, 0, 0);
    private static final Color BLEU        = new Color(0, 0, 230);
    private static final Color BLEU_FONCE  = new Color(0, 0, 102);
    private static final Color ROUGE       = new Color(230, 0, 0);
    private static final Color ROUGE_FONCE = new Color(102, 0, 0);
    private static final Color GRIS        = new Color(220, 220, 220);
    private static final Color GRIS_FONCE  = new Color(128, 128, 128);
   
    private EcouteurFenetreNavale ecouteur;

    private JPanel panneauBoutons;
    private JPanel panneauGrilleJoueur;
    private JPanel panneauGrilleAdversaire;
    private JPanel panneauEvenement;
    private JRadioButton boutonRadioDebutant;
    private JRadioButton boutonRadioAvance;
    private JRadioButton boutonRadioEnLigne;
    private JButton boutonDemarrer;
    private JButton boutonRecharger;
    private JButton boutonRecords;
    private JButton boutonJouer;
    private JButton boutonTourPrecedent;
    private JButton boutonTourSuivant;
    private JButton boutonMenu;
    private JButton boutonFermer;
    private JButton boutonSauvegarde;
    private JButton boutonVisualisation;
    private JRadioButton boutonHor;
    private JRadioButton boutonVer;
    private JRadioButton boutonPorteAvion;
    private JRadioButton boutonCroiseur;
    private JRadioButton boutonContreTorpilleurs;
    private JRadioButton boutonSousMarin;
    private JRadioButton boutonTorpilleur;
    private JButton grilleJoueur[][];
    private JButton grilleAdversaire[][];
    private JTextArea messageEvenement;

    public FenetreNavale() {

        ecouteur = new EcouteurFenetreNavale(this);

        boutonRadioDebutant = new JRadioButton("Débutant");
        boutonRadioAvance   = new JRadioButton("Avancé");
        boutonRadioEnLigne  = new JRadioButton("En ligne");

        boutonDemarrer  = new JButton("Nouvelle partie");
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

        boutonDemarrer.addActionListener(ecouteur);
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

    public void initFenetreMenu() {

        getContentPane().removeAll();
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new GridLayout(1, 5));

        JPanel panneauBoutonsRadio = new JPanel();
        panneauBoutonsRadio.setLayout(new GridLayout(3, 1));

        ButtonGroup boutonsRadio = new ButtonGroup();

        boutonRadioDebutant.setSelected(true);

        boutonsRadio.add(boutonRadioDebutant);
        boutonsRadio.add(boutonRadioAvance);
        boutonsRadio.add(boutonRadioEnLigne);

        panneauBoutonsRadio.add(boutonRadioDebutant);
        panneauBoutonsRadio.add(boutonRadioAvance);
        panneauBoutonsRadio.add(boutonRadioEnLigne);

        panneauBoutons.add(boutonDemarrer);
        panneauBoutons.add(panneauBoutonsRadio);
        panneauBoutons.add(boutonRecharger);
        panneauBoutons.add(boutonRecords);
        panneauBoutons.add(boutonFermer);

        add(panneauBoutons);
        pack();
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

    public void initFenetrePartie() {
           
        getContentPane().removeAll();
        
        panneauGrilleJoueur = new JPanel();
        panneauGrilleJoueur.setLayout(new GridLayout(10, 10));
        panneauGrilleJoueur.setBorder(new TitledBorder("Votre grille"));
       
        panneauGrilleAdversaire = new JPanel();
        panneauGrilleAdversaire.setLayout(new GridLayout(10, 10));
        panneauGrilleAdversaire.setBorder(new TitledBorder("Grille de l'adversaire"));

        for(int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                grilleJoueur[i][j] = new JButton();
                grilleJoueur[i][j].setEnabled(false);
                grilleJoueur[i][j].setBackground(BLEU);
                grilleJoueur[i][j].setPreferredSize(new Dimension(30, 30));
                grilleJoueur[i][j].removeActionListener(ecouteur);
                grilleAdversaire[i][j] = new JButton();
                grilleAdversaire[i][j].setBackground(ROUGE);
                grilleAdversaire[i][j].setPreferredSize(new Dimension(30,30));
                grilleAdversaire[i][j].addActionListener(ecouteur);               
               
                panneauGrilleJoueur.add(grilleJoueur[i][j]); 
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
        messageEvenement.setEditable(false);

        setLayout(new GridLayout(2,2));
        add(panneauGrilleJoueur);
        add(panneauGrilleAdversaire);
        add(panneauBoutons);
        add(panneauEvenement);
        pack();
    }


    public void initFenetreRecords(Records records) {

    getContentPane().removeAll();

    JPanel panneauRecords = new JPanel();
    panneauRecords.setBorder(new TitledBorder("Meilleurs temps")); 
    JTextArea messageRecords = new JTextArea();
    messageRecords.setText("Partie en ligne:\n" +
                           "     Joueur: " + records.getNomRecordJoueur()    +  "\n" +
                           "     Temps:  " + records.getTempsRecordJoueur() + "s\n" +
                           "Partie niveau débutant:\n" +
                           "     Joueur: " + records.getNomRecordDebutant()   +  "\n" +
                           "     Temps:  " + records.getTempsRecordDebutant() + "s\n" +
                           "Partie niveau avancé:\n" +
                           "     Joueur: " + records.getNomRecordAvance()   + "\n" +
                           "     Temps:  " + records.getTempsRecordAvance() + "s");
    messageRecords.setEditable(false); 
    panneauRecords.add(messageRecords);

    setLayout(new GridLayout(2, 1));
    add(panneauRecords);
    add(boutonMenu);
    pack();
    }

    public void initFenetreVisualisation() {

        getContentPane().removeAll();

        JPanel panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new GridLayout(1, 3));

        panneauBoutons.add(boutonTourPrecedent);
        panneauBoutons.add(boutonTourSuivant);
        panneauBoutons.add(boutonMenu);
        boutonTourPrecedent.setEnabled(false);

        JPanel panneauGrilleJoueur = new JPanel();
        panneauGrilleJoueur.setLayout(new GridLayout(10, 10));
        panneauGrilleJoueur.setBorder(new TitledBorder("Votre grille"));

        JPanel panneauGrilleAdversaire = new JPanel();
        panneauGrilleAdversaire.setLayout(new GridLayout(10, 10));
        panneauGrilleAdversaire.setBorder(new TitledBorder("Grille de l'adversaire"));

        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                panneauGrilleJoueur.add(grilleJoueur[i][j]);
                panneauGrilleAdversaire.add(grilleAdversaire[i][j]);
                grilleJoueur[i][j].setEnabled(false);
                grilleAdversaire[i][j].setEnabled(false);
            }
        }
        panneauEvenement = new JPanel();
        panneauEvenement.setLayout(new GridLayout(1,1));
        panneauEvenement.setBorder(new TitledBorder("Événement"));
        panneauEvenement.add(messageEvenement);
        messageEvenement.setEditable(false);

        setLayout(new GridLayout(2,2));
        add(panneauGrilleJoueur);
        add(panneauGrilleAdversaire);
        add(panneauBoutons);
        add(panneauEvenement);
        pack();
    }
    
    public void miseAJourPlacementsNavires(List<Case> casesOccupees) {

        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                grilleJoueur[i][j].setBackground(BLEU);
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
                
                Color couleurCaseJoueur     = getCouleurCase(tour.getChampJoueur(i, j), true);
                Color couleurCaseAdversaire = getCouleurCase(tour.getChampAdversaire(i, j), false);

                grilleAdversaire[i][j].setEnabled(auTourDuJoueur);            
                grilleJoueur[i][j].setBackground(couleurCaseJoueur);

                if (couleurCaseAdversaire == ROUGE_FONCE ||
                        couleurCaseAdversaire == GRIS_FONCE) {
                    grilleAdversaire[i][j].setBackground(couleurCaseAdversaire);
                    grilleAdversaire[i][j].setEnabled(false);
                }
            }
        }
        if (tour.estDernierTour()) {
            messageEvenement.setText(tour.getEvenement());
            boutonSauvegarde.setEnabled(false);
            boutonVisualisation.setEnabled(true);
        }                                  
        else {
            boutonSauvegarde.setEnabled(true);
        
            if (!auTourDuJoueur) {
                messageEvenement.setText(tour.getEvenement());
            }
        }
    }

    public void miseAJourVisualisation(Tour tour) {

        for (int i = 0 ; i < 10 ; ++i) {
            for (int j = 0 ; j < 10 ; ++j) {
                grilleJoueur[i][j].setBackground(getCouleurCase(tour.getChampJoueur(i, j), true));
                grilleAdversaire[i][j].setBackground(getCouleurCase(tour.getChampAdversaire(i, j), false));            
            }
        }
        messageEvenement.setText(tour.getEvenement());
       
        boutonTourPrecedent.setEnabled(!tour.estPremierTour());
        boutonTourSuivant.setEnabled(!tour.estDernierTour());
    }
    
    private Color getCouleurCase(char contenu, boolean caseJoueur) {

        if (contenu == 'n') return GRIS;
        if (contenu == 't') return GRIS_FONCE;
        if (caseJoueur == true) {
            if (contenu == 'e') return BLEU;
            if (contenu == 'd') return BLEU_FONCE;
        }
        else {
            if (contenu == 'e') return ROUGE;
            if (contenu == 'd') return ROUGE_FONCE;
        }
        return COULEUR_CASE_INVALIDE;
    }

    // getters
    public JButton getBoutonDemarrer() {
        return boutonDemarrer;
    }
    public JButton getBoutonRecharger() {
        return boutonRecharger;
    }
    public JButton getBoutonRecords() {
        return boutonRecords;
    }
    public JButton getBoutonJouer() {
        return boutonJouer;
    }
    public JButton getBoutonTourPrecedent() {
        return boutonTourPrecedent;
    }
    public JButton getBoutonTourSuivant() {
        return boutonTourSuivant;
    }
    public JButton getBoutonMenu() {
        return boutonMenu;
    }
    public JButton getBoutonFermer() {
        return boutonFermer;
    }
    public JButton getBoutonSauvegarde() {
        return boutonSauvegarde;
    }
    public JButton getBoutonVisualisation() {
        return boutonVisualisation;
    }
    public JButton getGrilleJoueur(int i, int j) {
        return grilleJoueur[i][j];
    }
    public JButton getGrilleAdversaire(int i, int j) {
        return grilleAdversaire[i][j];
    }
    public JRadioButton getBoutonRadioDebutant() {
        return boutonRadioDebutant;
    }
    public JRadioButton getBoutonRadioAvance() {
        return boutonRadioAvance;
    }
    public JRadioButton getBoutonRadioEnLigne() {
        return boutonRadioEnLigne;
    }
    public JRadioButton getBoutonHor() {
        return boutonHor;
    }
    public JRadioButton getBoutonVer() {
        return boutonVer;
    }
    public JRadioButton getBoutonPorteAvion() {
        return boutonPorteAvion;
    }
    public JRadioButton getBoutonCroiseur() {
        return boutonCroiseur;
    }
    public JRadioButton getBoutonContreTorpilleurs() {
        return boutonContreTorpilleurs;
    }
    public JRadioButton getBoutonSousMarin() {
        return boutonSousMarin;
    }
    public JRadioButton getBoutonTorpilleur() {
        return boutonTorpilleur;
    }
}
