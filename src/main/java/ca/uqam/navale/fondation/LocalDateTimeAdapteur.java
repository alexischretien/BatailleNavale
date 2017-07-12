/*
 * Inspiré de blog.bdoughan.com/2011/05/jaxb-and-joda-time-dates-and-times.html
 *
 */

package ca.uqam.navale.fondation;

import javax.xml.bind.annotation.adapters.*;
import org.joda.time.*;

public class LocalDateTimeAdapteur extends XmlAdapter<String, LocalDateTime> {

    public LocalDateTime unmarshal(String date) throws Exception {
        return new LocalDateTime(date);
    }
    public String marshal(LocalDateTime date) throws Exception {
        return date.toString();
    }
}
