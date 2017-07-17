/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * PartieControleur.java - Fichier source de l'interface PartieControleur
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.application;

import ca.uqam.navale.domaine.*;

import java.io.*;
import java.util.*;
import org.json.simple.parser.ParseException;

public interface PartieControleur {

    public boolean init();
    public List<Case> positionnerNavire(int i, int j, boolean horizontal, int navireId);
    public Tour attaquerAdversaire(int i, int j); 
    public Tour getAttaqueAdversaire();
    public Tour getTourPrecedent();
    public Tour getTourSuivant();
    public Tour getPremierTour();
    public Tour getDernierTour();
    public void miseAJourRecords(String nom) throws IOException, ParseException;
}
