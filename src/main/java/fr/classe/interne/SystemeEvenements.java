package fr.classe.interne;

import java.util.*;

public class SystemeEvenements {

    // Interface pour écouter les clics (x, y = position du clic)
    @FunctionalInterface
    interface ClickListener {
        void onClick(int x, int y);
    }

    // Interface pour écouter les touches du clavier
    @FunctionalInterface
    interface KeyListener {
        void onKeyPress(char touche);
    }

    // Interface pour écouter le survol (true = entré, false = sorti)
    @FunctionalInterface
    interface HoverListener {
        void onHover(boolean entre);
    }

    // Classe qui représente un bouton avec ses 3 types de listeners
    public static class Bouton {

        private String label; // nom du bouton
        private List<ClickListener> clickListeners = new ArrayList<>();
        private List<KeyListener> keyListeners = new ArrayList<>();
        private List<HoverListener> hoverListeners = new ArrayList<>();

        public Bouton(String label) {
            this.label = label;
        }

        // Ajoute un listener à la liste correspondante
        public void addClickListener(ClickListener l) { clickListeners.add(l); }
        public void addKeyListener(KeyListener l)     { keyListeners.add(l); }
        public void addHoverListener(HoverListener l) { hoverListeners.add(l); }

        // Simule un clic et notifie tous les ClickListeners
        public void simulerClic(int x, int y) {
            System.out.println("[" + label + "] Clic en (" + x + ", " + y + ")");
            for (ClickListener l : clickListeners) l.onClick(x, y);
        }

        // Simule une touche pressée et notifie tous les KeyListeners
        public void simulerTouche(char touche) {
            System.out.println("[" + label + "] Touche : " + touche);
            for (KeyListener l : keyListeners) l.onKeyPress(touche);
        }

        // Simule un survol et notifie tous les HoverListeners
        public void simulerSurvol(boolean entre) {
            System.out.println("[" + label + "] Survol : " + (entre ? "entré" : "sorti"));
            for (HoverListener l : hoverListeners) l.onHover(entre);
        }
    }

    // Version avec des classes anonymes (syntaxe classique)
    public static void mainAnonyme() {
        System.out.println("=== Version Classe Anonyme ===");
        Bouton btn = new Bouton("Valider");

        // Classe anonyme pour le clic
        btn.addClickListener(new ClickListener() {
            public void onClick(int x, int y) {
                System.out.println("  [Anonyme Click] Clic en (" + x + ", " + y + ")");
            }
        });

        // Classe anonyme pour la touche
        btn.addKeyListener(new KeyListener() {
            public void onKeyPress(char touche) {
                System.out.println("  [Anonyme Key] Touche : " + touche);
            }
        });

        // Classe anonyme pour le survol
        btn.addHoverListener(new HoverListener() {
            public void onHover(boolean entre) {
                System.out.println("  [Anonyme Hover] " + (entre ? "Entré" : "Sorti"));
            }
        });

        btn.simulerClic(10, 20);
        btn.simulerTouche('A');
        btn.simulerSurvol(true);
    }

    // Version avec des lambdas (syntaxe courte, même résultat)
    public static void mainLambda() {
        System.out.println("\n=== Version Lambda ===");
        Bouton btn = new Bouton("Valider");

        // Lambda pour le clic
        btn.addClickListener((x, y) ->
                System.out.println("  [Lambda Click] Clic en (" + x + ", " + y + ")")
        );

        // Lambda pour la touche
        btn.addKeyListener(touche ->
                System.out.println("  [Lambda Key] Touche : " + touche)
        );

        // Lambda pour le survol
        btn.addHoverListener(entre ->
                System.out.println("  [Lambda Hover] " + (entre ? "Entré" : "Sorti"))
        );

        btn.simulerClic(10, 20);
        btn.simulerTouche('A');
        btn.simulerSurvol(true);
    }

    // Point d'entrée : lance les deux versions
    public static void main(String[] args) {
        mainAnonyme();
        mainLambda();
    }
}