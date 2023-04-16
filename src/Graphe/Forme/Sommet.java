import javax.swing.*;
import java.awt.*;

public abstract class Sommet{
    protected int x,y;
    private String nom;
    private Color couleur;
    Sommet(String n,int a, int b){
        this.x=a;
        this.y=b;
        this.nom=n;
        this.couleur=Color.BLACK;
    }
    Sommet(String n,int a, int b,Color c){
        this.x=a;
        this.y=b;
        this.nom=n;
        this.couleur=c;
    }
    public void setX(int a){
        this.x=a;
    }
    public void setY(int a){
        this.y=a;
    }
    public void setNom(String n){
        this.nom=n;
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
    public String getNom(){
        return this.nom;
    }
    public Color getCouleur(){
        return this.couleur;
    }
    public abstract void paint(Graphics g);
}