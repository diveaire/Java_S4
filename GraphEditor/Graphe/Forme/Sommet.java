package Graphe.Forme;

import java.awt.*;

public abstract class Sommet extends Element{
    /* Attribut */
    private int x,y; // Coordonnées en X,Y du sommet, correspond aux positions en haut à gauche de l'élément
    /* Constructeurs */
    public Sommet(String n,int a, int b){
        super(n);
        this.x=a;
        this.y=b;
    }
    public Sommet(String n,int a, int b,Color c){
        super(n,c);
        this.x=a;
        this.y=b;
    }
    public Sommet(String n,int a, int b,Color c,Color c2){
        super(n,c,c2);
        this.x=a;
        this.y=b;
    }
    /* Méthodes */
    public void setX(int a){
        this.x=a;
    }
    public void setY(int a){
        this.y=a;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean equals(Sommet s){
        /* Si les positions en X et Y sont les mêmes, les sommets sont égaux */
        return ((this.x==s.getX())&&(this.y==s.getY()));
    }
    /* Méthode permettant d'obtenir le milieu d'un sommet et sa taille, ces méthodes dépendent de la forme de l'objet donc elles sont abstraites */
    public abstract int getLenght();
    public abstract void setLenght(int a);
    public abstract int getXCenter();
    public abstract int getYCenter();
}