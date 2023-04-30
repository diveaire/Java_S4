package Graphe.Forme;

import java.awt.*;
import javax.swing.*;

/**
 * Objet Abstrait (hérité d'Element) permettant de représenter un Arc dans un Graphe ( lien entre 2 Sommets)
 */
public abstract class Arc extends Element {
    /**
     * Les deux sommets de l'arc définissant sa position
     */
    private Sommet s1,s2;
    /**
     * L'épaisseur de l'arc
     */
    private float thickness;

    /**
     * Création d'un Arc.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     */
    /* Constructeurs */
    public Arc(Sommet a, Sommet b){
        super(a.getNom()+"-"+b.getNom(),Color.BLACK,Color.GREEN);
        this.s1=a;
        this.s2=b;
        this.thickness=1;
    }

    /**
     * Création d'un Arc.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     * @param n Nom de l'Arc
     */
    public Arc(Sommet a,Sommet b,String n){
        super(n);
        this.s1=a;
        this.s2=b;
        this.thickness=1;
    }
    /**
     * Creation d'un Arc.
     *
     * @param a the a
     * @param b the b
     * @param n the n
     * @param c the c
     */
    public Arc(Sommet a,Sommet b,String n,Color c){
        super(n,c,Color.GREEN);
        this.s1=a;
        this.s2=b;
        this.thickness=1;
    }

    /**
     * Création d'un Arc.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     * @param n Nom de l'Arc
     * @param c Couleur de l'objet
     * @param c2 Couleur de sélection de l'objet
     */
    public Arc(Sommet a,Sommet b,String n,Color c, Color c2){
        super(n,c,c2);
        this.s1=a;
        this.s2=b;
        this.thickness=1;
    }

    /**
     * Définir Sommet 1
     *
     * @param a Le Sommet
     */
    /* Méthodes */
    public void setS1(Sommet a){
        this.s1=a;
    }

    /**
     * Définir Sommet 2
     *
     * @param a Le Sommet
     */
    public void setS2(Sommet a){
        this.s2=a;
    }

    /**
     * Définir la largeur du trait
     *
     * @param a La largeur du trait
     */
    public void setThickness(float a){
        this.thickness=a;
    }

    /**
     * récupérer le Sommet 1
     *
     * @return le sommet
     */
    public Sommet getS1(){
        return this.s1;
    }

    /**
     * récupérer le Sommet 2
     *
     * @return le sommet
     */
    public Sommet getS2(){
        return this.s2;
    }

    /**
     * retourne la largeur du trait
     *
     * @return La largeur du trait (float)
     */
    public float getThickness(){
        return this.thickness;
    }

    /**
     * méthode permettant de savoir si le sommet en paramètre fait partie de l'arc
     *
     * @param a Le Sommet
     * @return Vrai si le Sommet a est dans l'Arc
     */
    public boolean contain(Sommet a){
        return (a.equals(this.s1))||(a.equals(this.s2));
    }

    /**
     * méthode permettant de savoir si les sommets de l'Arc en paramètre sont les mêmes que celui de cet Arc
     *
     * @param a L'Arc
     * @return Vrai si les Sommets de l'Arc a sont les mêmes que ceux de cet Arc
     */
    public boolean equals(Arc a){
        /* Si les deux sommets d'un arc sont un à un égaux alors il s'agit du même arc */
        return (this.s1.equals(a.getS1())&&(this.s2.equals(a.getS2())))||(this.s1.equals(a.getS2())&&(this.s2.equals(a.getS1())));
    }
}