package Graphe.Forme;

import java.awt.*;

/**
 * Objet (hérité de Sommet) correspondant à un sommet d'un graphe représenté par un Triangle
 */
public class Triangle extends Sommet{
    /**
     * taille de l'objet
     */
    private int l;
    /**
     * Coordonnées en x de chaque points du triangle
     */
    private int [] posx=new int[3];
    /**
     * Coordonnées en y de chaque points du triangle
     */
    private int [] posy=new int[3];

    /**
     * Création d'un Triangle.
     *
     * @param n le nom
     * @param a Coordonné X
     * @param b Coordonné Y
     * @param c Taille
     */
    /* Constructeurs */
    public Triangle(String n,int a,int b,int c){
        super(n,a,b);
        this.l=c;
        /* Le sommet du triangle a des coordonnées en X se trouvant à la moitié de la longueur de la base */
        this.posx[0]=(a+(c/2));
        this.posy[0]=b;
        this.posx[1]=a;
        this.posy[1]=(b+c);
        this.posx[2]=(a+c);
        this.posy[2]=(b+c);
    }

    /**
     * Création d'un Triangle.
     *
     * @param n le nom
     * @param a Coordonné X
     * @param b Coordonné Y
     * @param c Taille
     * @param d Couleur
     */
    public Triangle(String n,int a,int b,int c,Color d){
        super(n,a,b,d);
        this.l=c;
        /* Le sommet du triangle a des coordonnées en X se trouvant à la moitié de la longueur de la base */
        this.posx[0]=(a+(c/2));
        this.posy[0]=b;
        this.posx[1]=a;
        this.posy[1]=(b+c);
        this.posx[2]=(a+c);
        this.posy[2]=(b+c);
    }

    /**
     * Création d'un Triangle.
     *
     * @param n le nom
     * @param a Coordonné X
     * @param b Coordonné Y
     * @param c Taille
     * @param d Couleur
     * @param d2 Couleur Sélection
     */
    public Triangle(String n,int a,int b,int c,Color d, Color d2){
        super(n,a,b,d,d2);
        this.l=c;
        this.posx[0]=(a+(c/2));
        this.posy[0]=b;
        this.posx[1]=a;
        this.posy[1]=(b+c);
        this.posx[2]=(a+c);
        this.posy[2]=(b+c);
    }

    /**
     * Définir la taille de l'élément
     * @param a Taille (Entier)
     */
    public void setLenght(int a){
        this.l=a;
        /* Quand on modifie la longueur du triangle, les positions des trois points sont modifiées */
        this.posx[0]=(this.getX()+(this.l/2));
        this.posy[0]=this.getY();
        this.posx[1]=this.getX();
        this.posy[1]=(this.getY()+this.l);
        this.posx[2]=(this.getX()+this.l);
        this.posy[2]=(this.getY()+this.l);
    }

    /**
     * Récupérer la taille de l'élément
     * @return Taille (Entier)
     */
    public int getLenght(){
        return this.l;
    }

    /**
     * Récupérer coordonnée du centre X de l'objet
     * @return Coordonnée X au Centre
     */
    public int getXCenter(){
        return this.getX()+(this.l/2);
    }
    /**
     * Récupérer coordonnée du centre Y de l'objet
     * @return Coordonnée Y au Centre
     */
    public int getYCenter(){
        return this.getY()+(this.l/2);
    }

    /**
     * Dessine l'élément Triangle
     * @param g Graphics
     */
    public void paint(Graphics g){
        /* On dessine un polygone représentant un triangle selon les trois points définis précèdement et on affiche le nom du triangle au dessus */
        this.setLenght(this.l);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.fillPolygon(this.posx,this.posy,3);
        Font fonte = new Font("TimesRoman ",Font.BOLD,Element.getNomDisplaySize());
        g2.setFont(fonte);
        g2.drawString(this.getNom(),this.getX(),this.getY());
    }
}
