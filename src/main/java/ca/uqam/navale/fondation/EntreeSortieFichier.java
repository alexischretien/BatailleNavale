package ca.uqam.navale.fondation;

import ca.uqam.navale.domaine.Records;
import ca.uqam.navale.application.PartieControleur;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class EntreeSortieFichier {

    /* Permet d’écraser le fichier des records en le remplaçant par un fichier contenant les records en argument.
     * @param la records adverse cible
     */
    public static void ecrireRecords(Records records){
        JSONObject obj = new JSONObject();
        obj.put("nomRecordJoueur",     records.getNomRecordJoueur());
        obj.put("tempsRecordJoueur",   records.getTempsRecordJoueur());
        obj.put("nomRecordDebutant",   records.getNomRecordDebutant());
        obj.put("tempsRecordDebutant", records.getTempsRecordDebutant());
        obj.put("nomRecordAvance",     records.getNomRecordAvance());
        obj.put("tempsRecordAvance",   records.getTempsRecordAvance());
        try {
            FileWriter ficher = new FileWriter("records.json");
            ficher.write(obj.toString(),0,obj.toString().length());
            ficher.flush();
            ficher.close();
        } catch (IOException ex) {
            Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /* Permet de créer et de retourner l’instance de Records contenu dans le fichier des records.
     *  @return Records dans le fichier des records
     */
    public static Records recupererRecords() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("records.json"));
        JSONObject jsonObject = (JSONObject) obj;
        String  nomRecordJoueur = (String) jsonObject.get("nomRecordJoueur");
        String  nomRecordDebutant = (String) jsonObject.get("nomRecordDebutant");
        String  nomRecordAvance = (String) jsonObject.get("nomRecordAvance");
        Integer tempsRecordJoueur = (Integer) jsonObject.get("tempsRecordJoueur");
        Integer tempsRecordDebutant =  (Integer) jsonObject.get("tempsRecordDebutant");
        Integer tempsRecordAvance = (Integer) jsonObject.get("tempsRecordAvance"); 

        return new Records(nomRecordJoueur, nomRecordDebutant, nomRecordAvance, 
                           tempsRecordJoueur.intValue(), tempsRecordDebutant.intValue(),
                           tempsRecordAvance.intValue());

    }
    public static void ecrireSauvegarde(PartieControleur partie) {
       // à compléter
    }
    public static PartieControleur chargerSauvegarde() {
        // à compléter
        return null;
    } 
}

