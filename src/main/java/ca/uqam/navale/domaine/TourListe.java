/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * TourListe.java - Fichier source de la classe TourListe implémentant 
 *                  l'interface Liste qui permet de stocker des instances de
 *                  classe Tour et qui offre une méthode pour générer un
 8                  iterateur TourIterateur lui étant associé.
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.domaine;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
public class TourListe implements Liste {

    ArrayList<Tour> elements;

    // Constructeur
    public TourListe() {
        elements = new ArrayList<Tour>();
    }

    /*
     * Cree un iterateur de tour sur ses elements
     *
     * @return  le TourIterateur
     */
    public TourIterateur creerIterateur() {
        
        return new TourIterateur(this);
    }

    /*
     * Ajoute un tour en fin de de liste "elements"
     */                         
    public void ajouter(Tour element) {
        elements.add(element);
    }    
    
    /*
     * Retourne le tour en position "index"
     * 
     * @param  index   la position de l'element Tour a retourner
     * @return         Le tour en position "index"
     */
    public Tour getElement(int index) {

        Tour t = null;

        if (elements != null && index < elements.size()) {
            t = elements.get(index);
        }
        return t;
    }
    
    /*
     * Retourne le nombre de tours contenus dans elements
     *
     * @return le nombre de tours contenus dans elements
     */                                                       
    public int size() {
        return (elements != null ? elements.size() : 0);
    }   

    // getter
    public ArrayList<Tour> getElements() {
        return elements;
    }

    // setter
    @XmlElement
    public void setElements(ArrayList<Tour> elements) {
        this.elements = elements;
    }
}
                                                                           
