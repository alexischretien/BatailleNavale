package ca.uqam.navale.domaine;

import java.util.*;

public interface Liste<E extends Tour> {

    public Iterateur<E> creerIterateur();
    public void ajouter(E element);
    public E getElement(int index);
}
