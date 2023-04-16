import javax.swing.*;
import java.awt.*;

public class Carre extends Sommet{
    private int l;
    Carre(String n,int a,int b,int c){
        super(n,a,b);
        this.l=c;
    }
    Carre(String n,int a,int b,int c,Color d){
        super(n,a,b,d);
        this.l=c;
    }
    public void setCote(int a){
        this.l=a;
    }
    public int getCote(){
        return this.l;
    }
    public int getXCenter(){
        return this.x+(this.l/2);
    }
    public int getYCenter(){
        return this.y+(this.l/2);
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleur());
        g2.fill3DRect(this.x,this.y,this.l,this.l,false);
    }
}