package Graphe.Forme;

import java.awt.*;

public class Carre extends Sommet{
    /* Attribut */
    private int l;
    /* Constructeurs */
    public Carre(String n,int a,int b,int c){
        super(n,a,b);
        this.l=c;
    }
    public Carre(String n,int a,int b,int c,Color d){
        super(n,a,b,d);
        this.l=c;
    }
    /* Methodes */
    public void setLenght(int a){
        this.l=a;
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
        /* Dessin d'un carré et ajout des noms au dessus de celui-ci */
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.fill3DRect(this.getX(),this.getY(),this.l,this.l,false);
        g2.setColor(Color.BLACK);
        Font fonte = new Font("TimesRoman ",Font.BOLD,Element.getNomDisplaySize());
        g2.setFont(fonte);
        g2.drawString(this.getNom(),this.getX(),this.getY());
    }
}
