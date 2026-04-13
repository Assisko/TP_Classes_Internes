package fr.classe.interne;
//4 Ordinateur
public final class Ordinateur {

    // champs obligatoires
    private final String marque;
    private final String processeur;
    private final int ramGo;

    // champs optionnels
    private final int stockageGo;
    private final boolean ssd;
    private final String carteGraphique;
    private final double prixEuros;

    private Ordinateur(Builder builder) {
        this.marque = builder.marque;
        this.processeur = builder.processeur;
        this.ramGo = builder.ramGo;
        this.stockageGo = builder.stockageGo;
        this.ssd = builder.ssd;
        this.carteGraphique = builder.carteGraphique;
        this.prixEuros = builder.prixEuros;
    }

    @Override
    public String toString() {
        return marque + " | " + processeur + " | RAM: " + ramGo + "Go | Stockage: "
                + stockageGo + "Go | SSD: " + ssd + " | GPU: "
                + carteGraphique + " | Prix: " + prixEuros + "€";
    }





    //5 Builder


    public static class Builder {

        // champs obligatoires
        private final String marque;
        private final String processeur;
        private final int ramGo;

        // champs optionnels avec valeurs par défaut
        private int stockageGo = 256;
        private boolean ssd = true;
        private String carteGraphique = "Intégrée";
        private double prixEuros = 0.0;

        public Builder(String marque, String processeur, int ram) {
            this.marque = marque;
            this.processeur = processeur;
            this.ramGo = ram;
        }

        public Builder stockage(int stockageGo) {
            this.stockageGo = stockageGo;
            return this;
        }

        public Builder ssd(boolean ssd) {
            this.ssd = ssd;
            return this;
        }

        public Builder carteGraphique(String carteGraphique) {
            this.carteGraphique = carteGraphique;
            return this;
        }

        public Builder prix(double prixEuros) {
            this.prixEuros = prixEuros;
            return this;
        }

        public Ordinateur build() {
            if (ramGo < 4) {
                throw new IllegalArgumentException("La RAM doit être minimum 4 Go !");
            }
            return new Ordinateur(this);
        }
    }




    //6 Main

    public static void main(String[] args) {

        // Ordinateur 1 - configuration minimale
        Ordinateur pc1 = new Builder("Dell", "Intel i5", 8)
                .prix(599.99)
                .build();

        // Ordinateur 2 - configuration complète
        Ordinateur pc2 = new Builder("Apple", "M3 Pro", 16)
                .stockage(512)
                .ssd(true)
                .carteGraphique("Apple GPU 18 cœurs")
                .prix(1999.99)
                .build();

        // Ordinateur 3 - gaming
        Ordinateur pc3 = new Builder("Asus", "Intel i9", 32)
                .stockage(2000)
                .ssd(true)
                .carteGraphique("RTX 4080")
                .prix(2499.99)
                .build();

        System.out.println("=== MES ORDINATEURS ===");
        System.out.println(pc1);
        System.out.println(pc2);
        System.out.println(pc3);

        // Test de validation - RAM trop faible
        System.out.println("\n=== TEST ERREUR RAM ===");
        try {
            Ordinateur pcInvalide = new Builder("HP", "Intel i3", 2).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


}