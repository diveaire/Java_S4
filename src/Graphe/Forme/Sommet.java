import javax.swing.*;
import java.awt.*;

public abstract class Sommet extends Element{
    private int x,y;
    Sommet(String n,int a, int b){
        super(n,Color.BLACK);
        this.x=a;
        this.y=b;
    }
    Sommet(String n,int a, int b,Color c){
        super(n,c);
        this.x=a;
        this.y=b;
    }
    public void setX(int a){
        this.x=a;
    }
    public void setY(int a){
        this.y=a;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean equals(Sommet s){
        return ((this.x==s.getX())&&(this.y==s.getY()));
    }
    public abstract int getLenght();
    public abstract void setLenght(int a);
    public abstract int getXCenter();
    public abstract int getYCenter();
    public abstract void paint(Graphics g);
}