import javax.swing.*;
import java.awt.*;

public abstract class Sommet{
    protected int x,y;
    private Color couleur;
    Sommet(int a, int b){
        this.x=a;
        this.y=b;
        this.couleur=Color.BLACK;
    }
    Sommet(int a, int b,Color c){
        this.x=a;
        this.y=b;
        this.couleur=c;
    }
    public void setX(int a){
        this.x=a;
    }
    public void setY(int a){
        this.y=a;
    }
    public void setCouleur(Color a){
        this.couleur=a;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public Color getCouleur(){
        return this.couleur;
    }
    public abstract void paint(Graphics g);
}