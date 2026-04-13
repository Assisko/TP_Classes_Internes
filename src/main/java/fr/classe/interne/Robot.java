package fr.classe.interne;

//1 Le robot
public class Robot {

    private String nom;
    private boolean allume = false;
    private int energie;

    public Robot(String nom, int energie) {
        this.nom = nom;
        this.energie = energie;
    }

    public void allumer() {
        allume = true;
        System.out.println(nom + " est allumé.");
    }

    public void eteindre() {
        allume = false;
        System.out.println(nom + " est éteint.");
    }




    //2 BRAS

    public Bras getBras() {
        return new Bras();
    }

    class Bras {

        public void saisir(String objet) {
            if (allume && energie > 20) {
                energie -= 10;
                System.out.println(nom + " saisit : " + objet + " (énergie restante : " + energie + ")");
            } else {
                System.out.println("Impossible de saisir : robot éteint ou énergie insuffisante.");
            }
        }

        public void deposer(String objet) {
            if (allume) {
                energie -= 5;
                System.out.println(nom + " dépose : " + objet + " (énergie restante : " + energie + ")");
            } else {
                System.out.println("Impossible de déposer : robot éteint.");
            }
        }

        public void afficherEtat() {
            System.out.println("Robot : " + nom + " | Allumé : " + allume + " | Énergie : " + energie);
        }
    }



    //3 Main


    public static void main(String[] args) {

        Robot r = new Robot("R2D2", 80);
        Bras bras = r.getBras();

        // Test avant allumage
        System.out.println("=== AVANT ALLUMAGE ===");
        bras.saisir("boulon");

        // Allumage
        System.out.println("\n=== ALLUMAGE ===");
        r.allumer();
        bras.afficherEtat();

        // Opérations
        System.out.println("\n=== OPERATIONS ===");
        bras.saisir("boulon");
        bras.saisir("plaque");
        bras.deposer("boulon");
        bras.afficherEtat();

        // Extinction
        System.out.println("\n=== EXTINCTION ===");
        r.eteindre();
        bras.deposer("plaque");
    }

}