/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * EntreeSortieFichier.java - Fichier source de la classe EntreeSortieFichier,
 *                            offrant les méthodes pour lire et écrire les 
 *                            fichiers du système.
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.fondation;

import ca.uqam.navale.domaine.Records;
import ca.uqam.navale.application.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class EntreeSortieFichier {

    private static final String FICHIER_RECORDS = "records.json";
    private static final String FICHIER_SAUVEGARDE = "sauvegarde.xml";

    /* Permet d’ecraser le fichier des records en le remplaçant par un fichier contenant les records en argument.
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
            FileWriter ficher = new FileWriter(FICHIER_RECORDS);
            ficher.write(obj.toString(),0,obj.toString().length());
            ficher.flush();
            ficher.close();
        } catch (IOException ex) {
            Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /* Permet de creer et de retourner l’instance de Records contenu dans le fichier des records.
     *  @return Records dans le fichier des records
     */
    public static Records recupererRecords() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(FICHIER_RECORDS));
        JSONObject jsonObject = (JSONObject) obj;
        String nomRecordJoueur   = (String) jsonObject.get("nomRecordJoueur");
        String nomRecordDebutant = (String) jsonObject.get("nomRecordDebutant");
        String nomRecordAvance   = (String) jsonObject.get("nomRecordAvance");
        Long   tempsRecordJoueur   = (Long) jsonObject.get("tempsRecordJoueur");
        Long   tempsRecordDebutant = (Long) jsonObject.get("tempsRecordDebutant");
        Long   tempsRecordAvance   = (Long) jsonObject.get("tempsRecordAvance"); 

        return new Records(nomRecordJoueur, nomRecordDebutant, nomRecordAvance, 
                           tempsRecordJoueur.intValue(), tempsRecordDebutant.intValue(),
                           tempsRecordAvance.intValue());

    }

   /* Permet d’ecraser le fichier de sauvegarde en le remplacant par un fichier 
    * contenant les donnees de l’instance de PartieControleur
    * @param la instance du PartieControleur
    */
    public static void ecrireSauvegarde(PartieControleur partie) throws IOException, JAXBException {
   
        File fichierSauvegarde = new File(FICHIER_SAUVEGARDE);
        fichierSauvegarde.delete();
        fichierSauvegarde = new File(FICHIER_SAUVEGARDE);
        FileWriter f = new FileWriter(fichierSauvegarde, false);
     
        Marshaller marshaller = JAXBContext.newInstance(partie.getClass()).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter resultat = new StringWriter(); 
        marshaller.marshal(partie, resultat);
        f.write(resultat.toString());
        f.close();
    }

   /* Permet de retourner l’instance de PartieControleur sauvegardee dans le fichier de sauvegarde
    * @return l'instance de PartieControleur
    */
    public static PartieControleur chargerSauvegarde()  {
   
        PartieControleur partie = null;      
        
        try {
            File fichierSauvegarde = new File(FICHIER_SAUVEGARDE);

            if (fichierSauvegarde.exists()) {
                Unmarshaller unmarshaller = JAXBContext.newInstance(PartieDebutantControleur.class, 
                                                            PartieAvanceControleur.class).createUnmarshaller();
                partie = (PartieControleur) unmarshaller.unmarshal(fichierSauvegarde);
            }
        }
        catch (UnmarshalException e) {
            Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (JAXBException e) {
            Logger.getLogger(EntreeSortieFichier.class.getName()).log(Level.SEVERE, null, e);
        }
        return partie;
    } 
}

