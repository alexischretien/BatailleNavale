package ca.uqam.navale.application;

import ca.uqam.navale.domaine.*;
import ca.uqam.navale.fondation.*;

import java.util.*;
import java.lang.String;

public interface PartieControleur {

    public void init();
    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId);
    public Tour initFlottes(List<List<Case>> flotte);
    public Tour attaquerAdversaire(Case c); 
    public Tour getAttaqueAdversaire();
    public Tour getTourPrecedent();
    public Tour getTourSuivant();
    public void miseAJourRecords(String nom, int temps);
}
