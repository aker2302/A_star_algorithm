public class Coup {
    private int eval;
    private int colonne;

    public Coup(int val,int c){
        this.colonne = c;
        this.eval = val;
    }

    public int getEval() {
        return eval;
    }

    public int getColonne() {
        return colonne;
    }
}
