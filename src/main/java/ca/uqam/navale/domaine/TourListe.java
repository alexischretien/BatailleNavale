package ca.uqam.navale.domaine;

import java.util.*;

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
}
                                                                           
