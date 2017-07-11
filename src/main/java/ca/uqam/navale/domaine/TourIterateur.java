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

        if(0 > index-1){
            return  tours.getElement(0);
        }
        else {
            return tours.getElement(index-1);
        }
    }
    public Tour suivant() {

        if(tours.size()-1 < index+1){
            return  tours.getElement(tours.size()-1);
        }
        else {
            return tours.getElement(index+1);
        }
    }

    public Tour courant() {
          
        return tours.getElement(index);
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
