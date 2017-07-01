package ca.uqam.navale.domaine;


public interface Iterateur<E extends Tour> {

    public E precedent();
    public E suivant();
}
