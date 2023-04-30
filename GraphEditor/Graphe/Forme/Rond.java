package Graphe.Forme;

import java.awt.*;

/**
 * Objet (hérité de Sommet) permettant de representé un Sommet sous la forme d'un Rond
 */
public class Rond extends Sommet{
    /**
     * taille du Rond
     */
    private int r;

    /**
     * Création d'un Rond.
     *
     * @param n Le nom du sommet
     * @param a Coordonnée X
     * @param b Coordonnée Y
     * @param c Taille du sommet
     */
    public Rond(String n, int a, int b, int c){
        super(n,a,b);
        this.r=c;
    }

    /**
     * Création d'un Rond.
     *
     * @param n Le nom du sommet
     * @param a Coordonnée X
     * @param b Coordonnée Y
     * @param c Taille du sommet
     * @param d Couleur du sommet
     */
    public Rond(String n,int a,int b,int c,Color d){
        super(n,a,b,d);
        this.r=c;
    }

    /**
     * Création d'un Rond.
     *
     * @param n Le nom du sommet
     * @param a Coordonnée X
     * @param b Coordonnée Y
     * @param c Taille du sommet
     * @param d Couleur du sommet
     * @param d2 Couleur de sélection du sommet
     */
    public Rond(String n,int a,int b,int c,Color d, Color d2){
        super(n,a,b,d,d2);
        this.r=c;
    }
    /* Méthodes */

    /**
     * Attribution de la taille du Rond
     * @param a Rayon à attribué
     */
    public void setLenght(int a){
        this.r=a;
    }

    /**
     * Récupèration de la taille du Rond
     * @return Rayon du Rond
     */
    public int getLenght(){
        return this.r;
    }
    /**
     * Récupèration de la coordonnée X du centre du Rond
     * @return coordonnée X du centre du Rond
     */
    public int getXCenter(){
        return this.getX()+(this.r/2);
    }

    /**
     * Récupèration de la coordonnée Y du centre du Rond
     * @return coordonnée Y du centre du Rond
     */
    public int getYCenter(){
        return this.getY()+(this.r/2);
    }

    /**
     * Permet de réaliser le dessin d'un Rond
     * @param g Objet (Graphics)
     */
    public void paint(Graphics g){
        /* Dessine un rond et affiche le nom de l'objet au dessus de celui-ci */
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.fillOval(this.getX(),this.getY(),this.r,this.r);
        Font fonte = new Font("TimesRoman ",Font.BOLD,Element.getNomDisplaySize());
        g2.setFont(fonte);
        g2.drawString(this.getNom(),this.getX(),this.getY());
    }
}
