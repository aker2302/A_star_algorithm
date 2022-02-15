import java.util.Arrays;

public class Puissance3 {
    private final int[][] matriceJeu;
    public int WIDTH=5;
    public int HEIGHT=5;

    public Puissance3(){
        matriceJeu = new int[this.HEIGHT][this.WIDTH];
    }

    public int[][] getMatriceJeu(){
        return matriceJeu;
    }

    public boolean jouer(boolean typeJoueur,int colonne,int[][] matrice){
        int joueur = typeJoueur ? 1:2;
        for(int i = matrice.length-1 ; i >= 0 ; i--){
            if(matrice[i][colonne] == 0){
                matrice[i][colonne] = joueur;
                return true;
            }
        }
        return false;
    }

    public boolean estFinJeu(boolean typeJoueur,int[][] matrice){
        Noeud Noeud_matrice = new Noeud(typeJoueur,matrice);
        if (Noeud_matrice.troisPionsAlignesColonne(typeJoueur) == 1000){
            return true;
        }
        else if(Noeud_matrice.troisPionsAlignesLigne(typeJoueur) == 1000){
            return true;
        }else{
            for (int i = 0;  i< matrice.length; i++) {
                for (int k = 0; k < matrice.length; k++) {
                    if (matrice[i][k]==0){
                        return false; // on vérifie si toute les cases ne sont pas remplies
                    }
                }
            }
        }
        return true;
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < matriceJeu.length; i++){
            for(int j = 0; j < matriceJeu[i].length; j++){
                s += " | " + matriceJeu[i][j] + " | ";
            }
            s += "\n";
        }
        return s;
    }

    public void copieMatrice(int[][] mSource,int[][] mDest){
        for(int i = 0; i < mSource.length; i++){
            for(int j = 0; j < mSource[0].length; j++){
                mDest[i][j] = mSource[i][j];
            }
        }
    }

    public Coup alpha_beta(Noeud noeud,int alpha,int beta,int profondeur){
        int best_Colonne=0;

        if (profondeur==1 || estFinJeu(!noeud.isMax(),noeud.getMatrice())) {
            noeud.evaluer();
            return new Coup(noeud.getH(),-1);
        }
        if (noeud.isMax()) {
            //
            for (int k = 0; k < noeud.getMatrice()[0].length; k++) {
                int[][] matrice_copie = new int[WIDTH][HEIGHT];
                copieMatrice(noeud.getMatrice(),matrice_copie);
                jouer(true, k , matrice_copie);
                Noeud noeud_fils = new Noeud(false,matrice_copie);
                Coup coup = alpha_beta(noeud_fils,alpha,beta,profondeur-1);
                noeud_fils.setH(coup.getEval());
                if (coup.getEval()>alpha) {
                    alpha=coup.getEval();
                    best_Colonne = k;
                }
                if (beta<=alpha) {
                    return new Coup(alpha,best_Colonne);
                }

            }
            return new Coup(alpha,best_Colonne);
        }
        else {
            for (int k = 0; k < noeud.getMatrice()[0].length; k++) {
                int[][] matrice_copie = new int[WIDTH][HEIGHT];
                copieMatrice(noeud.getMatrice(),matrice_copie);
                jouer(false, k , matrice_copie);
                Noeud noeud_fils = new Noeud(true,matrice_copie);
                Coup coup = alpha_beta(noeud_fils,alpha,beta,profondeur-1);
                noeud_fils.setH(coup.getEval());
                if (coup.getEval()<beta) {
                    beta=coup.getEval();
                    best_Colonne=k;
                }
                if (beta<=alpha) {
                    return new Coup(beta,best_Colonne);
                }
            }
            return new Coup(beta,best_Colonne);
        }
    }
}
