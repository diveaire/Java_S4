import javax.swing.*;
import java.awt.*;

public class Rond extends Sommet{
    private int r;
    Rond(String n,int a,int b,int c){
        super(n,a,b);
        this.r=c;
    }
    Rond(String n,int a,int b,int c,Color d){
        super(n,a,b,d);
        this.r=c;
    }
    public void setR(int a){
        this.r=a;
    }
    public int getR(){
        return this.r;
    }
    public int getXCenter(){
        return this.x+(this.r/2);
    }
    public int getYCenter(){
        return this.y+(this.r/2);
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleur());
        g2.fillOval(this.x,this.y,this.r,this.r);
    }
}