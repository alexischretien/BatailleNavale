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
        
        return new TourIterateur(this);
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
                                                                           
