package Graphe.Forme;


import java.awt.*;

/**
 * Objet (hérité de Sommet) correspondant à un sommet d'un graphe représenté par un Carre
 */
public class Carre extends Sommet{
    /**
     * Taille de l'objet
     */
    private int l;

    /**
     * Création d'un Carre.
     *
     * @param n Le nom du sommet
     * @param a Coordonnée X
     * @param b Coordonnée Y
     * @param c Taille du sommet
     */
    public Carre(String n,int a,int b,int c){
        super(n,a,b);
        this.l=c;
    }

    /**
     * Création d'un Carre.
     *
     * @param n Le nom du sommet
     * @param a Coordonnée X
     * @param b Coordonnée Y
     * @param c Taille du sommet
     * @param d Couleur du sommet
     */
    public Carre(String n,int a,int b,int c,Color d){
        super(n,a,b,d);
        this.l=c;
    }

    /**
     * Création d'un Carre.
     *
     * @param n Le nom du sommet
     * @param a Coordonnée X
     * @param b Coordonnée Y
     * @param c Taille du sommet
     * @param d Couleur du sommet
     * @param d2 Couleur de sélection du sommet
     */
    public Carre(String n,int a,int b,int c,Color d,Color d2){
        super(n,a,b,d,d2);
        this.l=c;
    }
    /* Methodes */

    /**
     * Attribution de la taille du Carre
     * @param a Rayon à attribué
     */
    public void setLenght(int a){
        this.l=a;
    }
    /**
     * Récupèration de la taille du Carre
     * @return Rayon du Carre
     */
    public int getLenght(){
        return this.l;
    }
    /**
     * Récupèration de la coordonnée X du centre du Carre
     * @return coordonnée X du centre du Carre
     */
    public int getXCenter(){
        return this.getX()+(this.l/2);
    }
    /**
     * Récupèration de la coordonnée Y du centre du Carre
     * @return coordonnée Y du centre du Carre
     */
    public int getYCenter(){
        return this.getY()+(this.l/2);
    }
    /**
     * Permet de réaliser le dessin d'un Carre
     * @param g Objet (Graphics)
     */
    public void paint(Graphics g){
        /* Dessin d'un carré et ajout des noms au dessus de celui-ci */
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.fill3DRect(this.getX(),this.getY(),this.l,this.l,false);
        Font fonte = new Font("TimesRoman ",Font.BOLD,Element.getNomDisplaySize());
        g2.setFont(fonte);
        g2.drawString(this.getNom(),this.getX(),this.getY());
    }
}
