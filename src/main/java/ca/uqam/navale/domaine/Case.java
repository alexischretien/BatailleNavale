package ca.uqam.navale.domaine;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class Case {

    int i;
    int j;

    public Case() {
        i = 0;
        j = 0;
    }

    public Case(int i, int j) {
        this.i = i;
        this.j = j;
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
    
    public boolean equals(Case c) {
        if (i == c.i && j == c.j) {
            return true;
        }
        return false;
    }
}
