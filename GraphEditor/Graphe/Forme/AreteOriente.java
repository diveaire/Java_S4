package Graphe.Forme;

import java.awt.*;
import javax.swing.*;

public class AreteOriente extends Arc {
    private int longueur;
    public AreteOriente(Sommet a, Sommet b){
        super(a,b);
        this.longueur=20;
    }
    public AreteOriente(Sommet a,Sommet b,String n,Color c){
        super(a,b,n,c);
        this.longueur=20;
    }
    public AreteOriente(Sommet a,Sommet b,String n,Color c,int l){
        super(a,b,n,c);
        this.longueur=l;
    }
    public int getlongueur(){
        return this.longueur;
    }
    public void setlongueur(int x){
        this.longueur=x;
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.setStroke(new BasicStroke(this.getThickness()));
        int destX,destY;
        if(this.getS1().getX()>this.getS2().getX()){
            destX=this.getS2().getX()+this.getS2().getLenght();
        }
        else{
            destX=this.getS2().getX();
        }
        if(this.getS1().getY()>this.getS2().getY()){
            destY=this.getS2().getY()+this.getS2().getLenght();
        }
        else{
            destY=this.getS2().getYCenter();
        }
        g2.drawLine(this.getS1().getXCenter(),this.getS1().getYCenter(),destX,destY);
        double deg =Math.atan2(destY-this.getS1().getYCenter(),this.getS2().getXCenter()-this.getS1().getXCenter());
        int ax = (int)(destX-this.longueur*Math.cos(deg-Math.PI/6));
        int ay = (int)(destY-this.longueur*Math.sin(deg-Math.PI/6));
        int bx = (int)(destX-this.longueur*Math.cos(deg+Math.PI/6));
        int by = (int)(destY-this.longueur*Math.sin(deg+Math.PI/6));
        g2.drawLine(destX, destY, ax, ay);
        g2.drawLine(destX, destY, bx, by);
    }
}