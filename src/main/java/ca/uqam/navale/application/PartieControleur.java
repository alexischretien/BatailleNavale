package ca.uqam.navale.application;

import ca.uqam.navale.domaine.*;
import ca.uqam.navale.fondation.*;

import java.io.*;
import java.util.*;
import org.json.simple.parser.ParseException;

public interface PartieControleur {

    public boolean init();
    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId);
    public Tour attaquerAdversaire(Case c); 
    public Tour getAttaqueAdversaire();
    public Tour getTourPrecedent();
    public Tour getTourSuivant();
    public void miseAJourRecords(String nom, int temps) throws IOException, ParseException;
}
