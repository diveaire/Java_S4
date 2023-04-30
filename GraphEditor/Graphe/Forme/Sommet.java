package Graphe.Forme;

import java.awt.*;

/**
 * Classe abstraite (hérité d'Element ) permettant la représentation d'un Sommet dans le Graphe
 */
public abstract class Sommet extends Element{
    /**
     * Coordonnées en X,Y du sommet, correspond aux positions en haut à gauche de l'élément
     */
    private int x,y;

    /**
     * Création d'un Sommet.
     *
     * @param n le nom (String)
     * @param a position X (Entier)
     * @param b position Y (Entier)
     */
    public Sommet(String n,int a, int b){
        super(n);
        this.x=a;
        this.y=b;
    }

    /**
     * Création d'un Sommet.
     *
     * @param n le nom (String)
     * @param a position X (Entier)
     * @param b position Y (Entier)
     * @param c Couleur (Color)
     */
    public Sommet(String n,int a, int b,Color c){
        super(n,c);
        this.x=a;
        this.y=b;
    }

    /**
     * Création d'un Sommet.
     *
     * @param n le nom (String)
     * @param a position X (Entier)
     * @param b position Y (Entier)
     * @param c Couleur (Color)
     * @param c2 Couleur de sélection (Color)
     */
    public Sommet(String n,int a, int b,Color c,Color c2){
        super(n,c,c2);
        this.x=a;
        this.y=b;
    }

    /**
     * Définir position X
     *
     * @param a position X (Entier)
     */
    /* Méthodes */
    public void setX(int a){
        this.x=a;
    }

    /**
     * Définir position Y
     *
     * @param a position Y (Entier)
     */
    public void setY(int a){
        this.y=a;
    }

    /**
     * Récupérer position X
     *
     * @return position X (Entier)
     */
    public int getX(){
        return this.x;
    }

    /**
     * Récupérer position Y
     *
     * @return position Y (Entier)
     */
    public int getY(){
        return this.y;
    }

    /**
     * Renvoie vrai si les positions des sommets (this et s) sont les mêmes
     *
     * @param s Sommets s
     * @return booléen vrai si les positions des sommets (this et s) sont les mêmes
     */
    public boolean equals(Sommet s){
        /* Si les positions en X et Y sont les mêmes, les sommets sont égaux */
        return ((this.x==s.getX())&&(this.y==s.getY()));
    }

    /**
     * Récupère,la taille de l'objet la méthode dépend de la forme de l'objet donc elle est abstraites
     *
     * @return la taille de l'objet
     */
    public abstract int getLenght();

    /**
     * Récupère,la taille de l'objet la méthode dépend de la forme de l'objet donc elle est abstraites
     *
     * @param a la taille à attribué
     */
    public abstract void setLenght(int a);

    /**
     * Récupère,le centre X de l'objet
     *
     * @return centre X de l'objet
     */
    public abstract int getXCenter();

    /**
     * Récupère,le centre Y de l'objet
     *
     * @return centre Y de l'objet
     */
    public abstract int getYCenter();
}