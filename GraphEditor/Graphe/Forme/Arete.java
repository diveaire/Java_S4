package Graphe.Forme;

import java.awt.*;
import javax.swing.*;

/**
 * Objet (hérité d'Arc) permettant de représenter une arrête dans le Graphe
 */
public class Arete extends Arc {
    /**
     * Création d'une Arete.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     */
    /* Constructeurs */
    public Arete(Sommet a, Sommet b){
        super(a,b);
    }

    /**
     * Création d'une Arete.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     * @param n Nom de l'arc
     */
    /* Constructeurs */
    public Arete(Sommet a, Sommet b, String n){
        super(a,b,n);
    }

    /**
     * Création d'une Arete.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     * @param n Nom de l'arc
     * @param c Couleur de l'Arc
     */
    public Arete(Sommet a,Sommet b,String n,Color c){
        super(a,b,n,c);
    }

    /**
     * Création d'une Arete.
     *
     * @param a  Sommet 1
     * @param b  Sommet 2
     * @param n  Nom de l'arc
     * @param c  Couleur de l'Arc
     * @param c2 Couleur de sélection de l'Arc
     */
    public Arete(Sommet a,Sommet b,String n,Color c,Color c2){
        super(a,b,n,c,c2);
    }
    /* Methode paint */

    /**
     * Méthode pour dessiner l'Arrete
     * @param g Objet (Graphics)
     */
    public void paint(Graphics g){
        /* Trace une simple ligne entre le milieux de chaque sommet */
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.setStroke(new BasicStroke(this.getThickness()));
        g2.drawLine(this.getS1().getXCenter(),this.getS1().getYCenter(),this.getS2().getXCenter(),this.getS2().getYCenter());
    }
}