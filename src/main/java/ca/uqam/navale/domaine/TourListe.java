package ca.uqam.navale.domaine;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
public class TourListe {

    ArrayList<Tour> elements;

    public TourListe() {
        elements = new ArrayList<Tour>();
    }

    public TourIterateur creerIterateur() {
        // à compléter
        return null;
    }
                         
    public void ajouter(Tour element) {
        elements.add(element);
    }    
        
    public Tour getElement(int index) {
        return elements.get(index);
    }
                                                             
    public int size() {
        return elements.size();
    }   

    public Tour getDernier() {

        if (elements.size() > 0) {
            return elements.get(elements.size() - 1);
        }
        else {
            return null;
        }
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
                                                                           
