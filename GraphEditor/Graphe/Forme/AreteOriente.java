package Graphe.Forme;

import java.awt.*;
import javax.swing.*;

/**
 * Objet (hérité d'Arc) permettant de représenter une arrete orienté dans le graphe
 */
public class AreteOriente extends Arc {
    /**
     * Taille de la fleche
     */
    private int longueur;

    /**
     * Création d'une Arete.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     */
    /* Constructeurs */
    public AreteOriente(Sommet a, Sommet b){
        super(a,b);
        this.longueur=10;
    }

    /**
     * Création d'une Arete.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     * @param n Nom de l'ArreteOriente
     * @param c Couleur de l'ArreteOriente
     */
    public AreteOriente(Sommet a,Sommet b,String n,Color c){
        super(a,b,n,c);
        this.longueur=10;
    }

    /**
     * Création d'une Arete.
     *
     * @param a Sommet 1
     * @param b Sommet 2
     * @param n Nom de l'ArreteOriente
     * @param c Couleur de l'ArreteOriente
     * @param l longeur de l'ArreteOriente
     */
    public AreteOriente(Sommet a,Sommet b,String n,Color c,int l){
        super(a,b,n,c);
        this.longueur=l;
    }

    /**
     * Récupère la longueur de l'objet
     *
     * @return Longueur de l'objet
     */
    /* Méthode */
    public int getlongueur(){
        return this.longueur;
    }

    /**
     * définir la longueur de l'objet
     *
     * @param x entier pour définir la longueur de l'objet
     */
    public void setlongueur(int x){
        this.longueur=x;
    }

    /**
     * Méthode pour dessiner une Arrête Orientée
     * @param g Objet (Graphics)
     */
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.setStroke(new BasicStroke(this.getThickness()));
        /* Initialisation de la destination de la flèche */
        int destX,destY;
        if(this.getS1().getX()>this.getS2().getX()){
            /* Si les coordonnées du sommet de départ sont à droite du sommet d'arrivé alors le points de destination sera à droite du sommet d'arrivé */
            destX=this.getS2().getX()+this.getS2().getLenght();
        }
        else{
            /* Sinon on garde l'arrivé à gauche du sommet d'arrivé */
            destX=this.getS2().getX();
        }
        if(this.getS1().getY()>this.getS2().getY()){
            /* Si les coordonnées du sommet de départ sont en bas du sommet d'arrivé alors le points de destination sera en bas du sommet d'arrivé */
            destY=this.getS2().getY()+this.getS2().getLenght();
        }
        else{
            /* Sinon on garde l'arrivé en haut du sommet d'arrivé */
            destY=this.getS2().getYCenter();
        }
        /* Dessin d'une ligne */
        g2.drawLine(this.getS1().getXCenter(),this.getS1().getYCenter(),destX,destY);
        /* Initialisation de l'angle */
        double deg =Math.atan2(destY-this.getS1().getYCenter(),this.getS2().getXCenter()-this.getS1().getXCenter());
        /* Initialisation des coordonnées du triangle */
        int ax = (int)(destX-this.longueur*Math.cos(deg-Math.PI/6));
        int ay = (int)(destY-this.longueur*Math.sin(deg-Math.PI/6));
        int bx = (int)(destX-this.longueur*Math.cos(deg+Math.PI/6));
        int by = (int)(destY-this.longueur*Math.sin(deg+Math.PI/6));

        int [] posx=new int[3];
        int [] posy=new int[3];
        posx[0]=destX;
        posy[0]=destY;
        posx[1]=ax;
        posy[1]=ay;
        posx[2]=bx;
        posy[2]=by;

        /* Dessin de la flèche */
        g2.fillPolygon(posx,posy,3);
       
    }
}