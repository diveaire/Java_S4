package Graphe.Forme;

import javax.swing.*;
import java.awt.*;

public abstract class Element{
    private String nom;
    private static int nomDisplaySize=15;
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
    static public void setNomDisplaySize(int n){
        nomDisplaySize=n;
    }
    public void setCouleur(Color a){
        this.couleur=a;
    }
    public String getNom(){
        return this.nom;
    }
    static public int getNomDisplaySize(){
        return nomDisplaySize;
    }
    public Color getCouleur(){
        return this.couleur;
    }
}
