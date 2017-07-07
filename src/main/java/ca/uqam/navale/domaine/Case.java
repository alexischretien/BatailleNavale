package ca.uqam.navale.domaine;

public class Case {

    int i;
    int j;

    public Case(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int get_i() {
        return i;
    }
    public int get_j() {
        return j;
    }

    public void set_i(int i) {
        this.i = i;
    }
    public void set_j(int j) {
        this.j = j;
    }
}
