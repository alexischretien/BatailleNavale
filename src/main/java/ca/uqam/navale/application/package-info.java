@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=LocalDateTime.class,
                        value=LocalDateTimeAdapteur.class)
})
package ca.uqam.navale.application;

import ca.uqam.navale.fondation.LocalDateTimeAdapteur;
import org.joda.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.*;

