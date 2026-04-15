package fr.classe.interne;

import java.util.*;

public class GestionnaireStock {
    // Liste qui contient tous les produits du stock
    private List<Produit> stock = new ArrayList<>();

    public void ajouterProduit(Produit p) {
        stock.add(p);
    }




    //8 Méthode de filtrage + tri


    public List<Produit> filtrerEtTrier(String categorie, double prixMax, int quantiteMin) 
    {

        // Interface locale (visible uniquement dans cette méthode)
        interface Filtre {
            boolean accepter(Produit p);
        }

        // Classe locale qui implémente Filtre
        class FiltreCompose implements Filtre {
            // Méthode qui vérifie si un produit respecte les conditions
            public boolean accepter(Produit p) {
                return p.getCategorie().equals(categorie)
                        && p.getPrix() <= prixMax
                        && p.getQuantite() >= quantiteMin;
            }
        }
        // Creation du filtre 
        Filtre filtre = new FiltreCompose();
        // liste des produits filtrés
        List<Produit> resultat = new ArrayList<>();

        // Parcours du stock
        for (Produit p : stock) {
            if (filtre.accepter(p)) {
                resultat.add(p);
            }
        }

        // Tri par prix croissant
        resultat.sort(new Comparator<Produit>() 
        {
            public int compare(Produit a, Produit b)
            {
                return Double.compare(a.getPrix(), b.getPrix());
            }
        });

        return resultat;
    }




    // 9 Main

    public static void main(String[] args) {

        GestionnaireStock stock = new GestionnaireStock();

        // Ajout de 8 produits
        stock.ajouterProduit(new Produit("Laptop", "Informatique", 899.99, 5));
        stock.ajouterProduit(new Produit("Souris", "Informatique", 29.99, 15));
        stock.ajouterProduit(new Produit("Clavier", "Informatique", 49.99, 10));
        stock.ajouterProduit(new Produit("Écran", "Informatique", 299.99, 3));
        stock.ajouterProduit(new Produit("T-shirt", "Vêtements", 19.99, 50));
        stock.ajouterProduit(new Produit("Jean", "Vêtements", 59.99, 20));
        stock.ajouterProduit(new Produit("Veste", "Vêtements", 89.99, 8));
        stock.ajouterProduit(new Produit("Casque", "Informatique", 79.99, 12));

        // Filtre 1 : Informatique, max 100€, min 10 en stock
        System.out.println("=== Informatique <= 100€, stock >= 10 ===");
        for (Produit p : stock.filtrerEtTrier("Informatique", 100.0, 10)) {
            System.out.println(p);
        }

        // Filtre 2 : Vêtements, max 70€, min 15 en stock
        System.out.println("\n=== Vêtements <= 70€, stock >= 15 ===");
        for (Produit p : stock.filtrerEtTrier("Vêtements", 70.0, 15)) {
            System.out.println(p);
        }
    }
}
