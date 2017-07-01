package ca.uqam.navale.domaine;

public class Case {

    int i;
    char j;

    public Case(int i, char j) {
        this.i = i;
        this.j = j;
    }

    public int get_i() {
        return i;
    }
    public char get_j() {
        return j;
    }

    public void set_i(int i) {
        this.i = i;
    }
    public void set_j(char j) {
        this.j = j;
    }
}
