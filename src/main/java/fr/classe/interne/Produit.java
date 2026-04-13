package fr.classe.interne;
//7 Produit

public class Produit {

    private String nom;
    private String categorie;
    private double prix;
    private int quantite;

    public Produit(String nom, String categorie, double prix, int quantite) {
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
    }

    public String getNom()          { return nom; }
    public String getCategorie()    { return categorie; }
    public double getPrix()         { return prix; }
    public int getQuantite()        { return quantite; }

    @Override
    public String toString() {
        return nom + " | " + categorie + " | " + prix + "€ | qté: " + quantite;
    }
}