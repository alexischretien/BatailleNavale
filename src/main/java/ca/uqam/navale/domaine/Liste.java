/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * Liste.java - Fichier source de l'interface Liste
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.domaine;

import java.util.*;

public interface Liste<E extends Tour> {

    public Iterateur<E> creerIterateur();
    public void ajouter(E element);
    public E getElement(int index);
    public int size();
}
