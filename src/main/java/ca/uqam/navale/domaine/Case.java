/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * Case.java - Fichier source de la classe Case permettant de représenter
 *             le duplet des index de la case d'une grille 2D et offrant
 *             une méthode permettant de comparer une instance de Case 
 *             avec une autre.
 *
 * @Auteurs  Alexis Chrétien  (CHRA25049209)
 *           Bernard Houle    (HOUB12129001)
 *           Tom Berthiaume   (BERT17039105)
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.domaine;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class Case {

    int i;
    int j;

    /*
     * Constructeurs
     */
    public Case() {
        i = 0;
        j = 0;
    }

    public Case(int i, int j) {
        this.i = i;
        this.j = j;
    }
      
    public boolean equals(Case c) {
        return (i == c.i && j == c.j);
    }

    // getter
    public int get_i() {
        return i;
    }
    public int get_j() {
        return j;
    }

    // setters
    @XmlElement
    public void set_i(int i) {
        this.i = i;
    }
    @XmlElement
    public void set_j(int j) {
        this.j = j;
    }
}
