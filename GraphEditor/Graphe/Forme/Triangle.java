package Graphe.Forme;

import java.awt.*;

public class Triangle extends Sommet{
    /* Attributs */
    private int l;
    private int [] posx=new int[3]; //Coordonnées en x de chaque points du triangle
    private int [] posy=new int[3]; //Coordonnées en y de chaque points du triangle
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
    public int getLenght(){
        return this.l;
    }
    public int getXCenter(){
        return this.getX()+(this.l/2);
    }
    public int getYCenter(){
        return this.getY()+(this.l/2);
    }
    public void paint(Graphics g){
        /* On dessine un polygone représentant un triangle selon les trois points définis précèdement et on affiche le nom du triangle au dessus */
        this.setLenght(this.l);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.fillPolygon(this.posx,this.posy,3);
        g2.setColor(Color.BLACK);
        Font fonte = new Font("TimesRoman ",Font.BOLD,Element.getNomDisplaySize());
        g2.setFont(fonte);
        g2.drawString(this.getNom(),this.getX(),this.getY());
    }
}
