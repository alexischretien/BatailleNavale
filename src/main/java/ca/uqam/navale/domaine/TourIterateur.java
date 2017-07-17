/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * TourIterateur.java - Fichier source de la classe TourIterateur implémentant
 *                      l'interface Iterateur et permettant d'itérer sur les tours
 *                      stockés dans un objet de classe TourListe
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.domaine;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class TourIterateur implements Iterateur {

    int index;
    TourListe tours;

    public TourIterateur() {
        index = 0;
        tours = new TourListe();
    }

    public TourIterateur(TourListe tours) {
        this.index = 0;
        this.tours = tours;
    }

    public Tour precedent() {
    
        Tour t = null;

        if (tours != null && tours.size() > 0) {
            if(0 > index-1){
                index = 0;
            }
            else {
                --index; 
            }
            t = tours.getElement(index);
        }
        return t;
    }

    public Tour suivant() {
        
        Tour t = null;

        if (tours != null && tours.size() > 0) {
            if(tours.size()-1 < index+1){
                index = tours.size() - 1;
            }
            else {
                ++index;
            }
            t = tours.getElement(index);
        }
        return t;
    }
    
    public Tour premier() {

        Tour t = null;

        if (tours != null && tours.size() > 0) {
            index = 0;
            t = tours.getElement(index);
        }
        return t;
    }    

    public Tour dernier() {
    
        Tour t = null;

        if (tours != null && tours.size() > 0) {
            index = tours.size() -1;
            t = tours.getElement(index);
        }
        return t;
    }

    public Tour courant() {
       
        Tour t = null;

        if (tours != null && tours.size() > index) {  
            t = tours.getElement(index);
        }
        return t;
    }

    // getters
    public int getIndex() {
        return index;
    }
    public TourListe getTours() {
        return tours;
    }

    // setters
    @XmlElement
    public void setIndex(int index) {
        this.index = index;
    }
    @XmlElement
    public void setTours(TourListe tours) {
        this.tours = tours;
    }
}
