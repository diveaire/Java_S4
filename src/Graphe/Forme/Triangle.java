import javax.swing.*;
import java.awt.*;

public class Triangle extends Sommet{
    private int l;
    private int [] posx=new int[3];
    private int [] posy=new int[3];
    Triangle(String n,int a,int b,int c){
        super(n,a,b);
        this.l=c;
        this.posx[0]=(a+(c/2));
        this.posy[0]=b;
        this.posx[1]=a;
        this.posy[1]=(b+c);
        this.posx[2]=(a+c);
        this.posy[2]=(b+c);
    }
    Triangle(String n,int a,int b,int c,Color d){
        super(n,a,b,d);
        this.l=c;
    }
    public void setCote(int a){
        this.l=a;
        this.posx[0]=(this.x+(this.l/2));
        this.posy[0]=this.y;
        this.posx[1]=this.x;
        this.posy[1]=(this.y+this.l);
        this.posx[2]=(this.x+this.l);
        this.posy[2]=(this.y+this.l);
    }
    public int getCote(){
        return this.l;
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleur());
        g2.fillPolygon(this.posx,this.posy,3);
    }
}