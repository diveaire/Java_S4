import javax.swing.*;
import java.awt.*;

public abstract class Element{
    private String nom;
    private Color couleur;
    Element(String nom){
        this.nom=nom;
    }
    Element(String nom,Color c){
        this.nom=nom;
        this.couleur=c;
    }
    public void setNom(String n){
        this.nom=n;
    }
    public void setCouleur(Color a){
        this.couleur=a;
    }
    public String getNom(){
        return this.nom;
    }
    public Color getCouleur(){
        return this.couleur;
    }
}