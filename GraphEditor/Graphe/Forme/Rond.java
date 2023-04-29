package Graphe.Forme;


import java.awt.*;

public class Rond extends Sommet{
    private int r;
    public Rond(String n, int a, int b, int c){
        super(n,a,b);
        this.r=c;
    }
    public Rond(String n,int a,int b,int c,Color d){
        super(n,a,b,d);
        this.r=c;
    }
    public Rond(String n,int a,int b,int c,Color d, Color d2){
        super(n,a,b,d,d2);
        this.r=c;
    }
    public void setLenght(int a){
        this.r=a;
    }
    public int getLenght(){
        return this.r;
    }
    public int getXCenter(){
        return this.getX()+(this.r/2);
    }
    public int getYCenter(){
        return this.getY()+(this.r/2);
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleurAff());
        g2.fillOval(this.getX(),this.getY(),this.r,this.r);
        Font fonte = new Font("TimesRoman ",Font.BOLD,Element.getNomDisplaySize());
        g2.setFont(fonte);
        g2.drawString(this.getNom(),this.getX(),this.getY());
    }
}
