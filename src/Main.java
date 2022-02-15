import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Puissance3 partie = new Puissance3();
        int j = 0;
        System.out.println("TP3 Puissance 3 KERBAL Abdellah");
        System.out.println(partie);
        while(true) {
            do{
                System.out.println("Tapez le numero de la colonne : ");
                try {
                    j = scanner.nextInt();
                }catch (Exception e){
                }
            }while((j>4) || (j<0));
            partie.jouer(false, j , partie.getMatriceJeu());
            System.out.println("Votre tour :");
            System.out.println(partie);

            if(partie.estFinJeu(false,partie.getMatriceJeu())){
                System.out.println("------------- Vouz avez gagné -------------");
                break;
            }

            Noeud noeud = new Noeud(true,partie.getMatriceJeu());
            Coup coup = partie.alpha_beta(noeud, Integer.MIN_VALUE, Integer.MAX_VALUE, 4);
            noeud.setH(coup.getEval());

            partie.jouer(true, coup.getColonne() , partie.getMatriceJeu());
            System.out.println("Tour de L'IA");
            System.out.println(partie);


            if (partie.estFinJeu(true, partie.getMatriceJeu() )) {
                System.out.println(partie);
                System.out.println("------------- L'IA a gagné -------------");
                break;
            }
        }
    }
}
