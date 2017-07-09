package ca.uqam.navale.domaine;


public class TourIterateur implements Iterateur {

    int index;
    TourListe tours;

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
          
        return tours.getElement(tours.size()-1);
    }
}
