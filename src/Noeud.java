public class Noeud {

    private int[][] matrice;
    private boolean max;
    private int h;

    public Noeud(boolean max,int[][] matrice){
        this.matrice = matrice;
        this.max = max;
    }
     public int getH(){
        return h;
     }

     public void setH(int h){
        this.h = h;
     }

     public int[][] getMatrice(){
         return matrice;
     }

     public boolean isMax(){
        return this.max;
     }

     public String toString(){
        String s = "";
        for(int i = 0; i < matrice.length; i++){
            for(int k = 0; k < matrice[i].length; k++){
                s += matrice[i][k] + " ";
            }
            s += "\n";
        }
        return s;
     }

     public int troisPionsAlignesLigne(boolean typeJoueur){
        int joueur = typeJoueur ? 1:2;
        int nombrePions;
        for(int i = 0; i < matrice.length; i++) {
            nombrePions = 0;
            for (int k = 0; k < matrice[i].length; k++) {
                if (matrice[i][k] == joueur){
                    nombrePions++;
                }else{
                    nombrePions = 0;
                }
                if(nombrePions == 3){
                    return 1000;
                }
            }
        }
        return 0;
     }

    public int troisPionsAlignesColonne(boolean typeJoueur){
        int joueur = typeJoueur ? 1:2;
        int nombrePions;
        for(int i = 0; i < matrice.length; i++) {
            nombrePions = 0;
            for (int k = 0; k < matrice[i].length; k++) {
                if (matrice[k][i] == joueur){
                    nombrePions++;
                }else{
                    nombrePions = 0;
                }
                if(nombrePions == 3){
                    return 1000;
                }
            }
        }
        return 0;
    }

    public int troisPionsPossiblesLigne(boolean typeJoueur){
        int joueur = typeJoueur ? 1:2;
        int resultat = 0;
        for(int i = 0; i < this.matrice.length; i++){
            for(int k = 0; k < this.matrice[i].length; k++){
                if(this.matrice[i][k] == 0){
                    if (k-2 >= 0 && this.matrice[i][k-2]== joueur && this.matrice[i][k-1]==joueur) resultat+=200;
                    if (k-1 >= 0 && this.matrice[i][k-1]== joueur) resultat+=30;
                    if (k+1 <= this.matrice[i].length-1 && this.matrice[i][k+1]== joueur) resultat+=30;
                    if (k+2 <= this.matrice[i].length-1 && this.matrice[i][k+2]== joueur && this.matrice[i][k+1]==joueur) resultat+=200;

                }
            }
        }
        return resultat;
    }

    public int troisPionsPossiblesColonne(boolean typeJoueur){
        int resultat = 0;
        int joueur = typeJoueur ? 1 : 2;
        for(int i = 0; i < this.matrice.length; i++){
            for(int k = 0; k < this.matrice[i].length; k++){
                if(matrice[k][i] == 0){
                    if (i-2 >= 0 && this.matrice[i-2][k]== joueur && this.matrice[i-1][k]==joueur) resultat+=200;
                    if (i-1 >= 0 && this.matrice[i-1][k]== joueur) resultat+=30;
                    if (i+1 <= this.matrice.length-1 && this.matrice[i+1][k]== joueur) resultat+=30;
                    if (i+2 <= this.matrice.length-1 && this.matrice[i+2][k]== joueur && this.matrice[i+1][k]==joueur) resultat+=200;

                }
            }
        }
        return resultat;
    }

    public void evaluer(){
        this.h = (-2*troisPionsAlignesLigne(false)
                + troisPionsAlignesLigne(true)
                -2*troisPionsAlignesColonne(false)
                + troisPionsAlignesColonne(true)
                -2*troisPionsPossiblesLigne(false)
                + troisPionsPossiblesLigne(true)
                -2*troisPionsPossiblesColonne(false)
                + troisPionsPossiblesColonne(true));
    }
}
