package Graphe.Forme;


import java.awt.*;

public class Triangle extends Sommet{
    private int l;
    private int [] posx=new int[3];
    private int [] posy=new int[3];
    public Triangle(String n,int a,int b,int c){
        super(n,a,b);
        this.l=c;
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
        this.posx[0]=(a+(c/2));
        this.posy[0]=b;
        this.posx[1]=a;
        this.posy[1]=(b+c);
        this.posx[2]=(a+c);
        this.posy[2]=(b+c);
    }
    public void setLenght(int a){
        this.l=a;
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
        this.setLenght(this.l);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleur());
        g2.fillPolygon(this.posx,this.posy,3);
    }
}