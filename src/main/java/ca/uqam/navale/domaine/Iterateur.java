/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * Iterateur.java - Fichier source de l'interface Iterateur
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.domaine;


public interface Iterateur<E extends Tour> {

    public E precedent();
    public E suivant();
    public E premier();
    public E dernier();
    public E courant();
}
