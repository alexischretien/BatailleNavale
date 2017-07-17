/* UQAM - été 2017 - INF5153 - Groupe 20 - TP3
 *
 * LocalDateTimeAdapteur.java - Fichier source de la classe LocalDateTimeAdapteur,
 *                              permettant de procéder à la sérialisation et à la
 *                              désérialisation d'instances d'objets de classe
 *                              "LocalDateTime" dans ou via un fichier xml. 
 *
 * @Auteur   implémentation tirée de 
 *           blog.bdoughan.com/2011/05/jaxb-and-joda-time-dates-and-times.html
 * @Version  17 juillet 2017
 */

package ca.uqam.navale.fondation;

import javax.xml.bind.annotation.adapters.*;
import org.joda.time.*;

public class LocalDateTimeAdapteur extends XmlAdapter<String, LocalDateTime> {

    /*
     * Methode de deserialisation d'une instance de LocalDateTime a partir de sa
     * representation toString()
     *
     * @param   date  la representation toString() d'un LocalDateTime
     * @return        l'instance de LocalDateTime associee
     */
    public LocalDateTime unmarshal(String date) throws Exception {
        return new LocalDateTime(date);
    }

    /*
     * Methode de serialisation d'une instance de LocalDateTime en utilisant sa
     * methode toString()
     *
     * @param  date  le LocalDateTime a serialiser
     * @return       la representation toString() du LocalDateTime
     */
    public String marshal(LocalDateTime date) throws Exception {
        return date.toString();
    }
}
