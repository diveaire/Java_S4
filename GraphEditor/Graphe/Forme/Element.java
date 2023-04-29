package Graphe.Forme;

import java.io.*;
import javax.swing.*;
import java.awt.*;

public abstract class Element implements Serializable{
    /* Attribut */
    private String nom; // Nom de l'objet
    private static int nomDisplaySize=15; // Taille du nom de l'objet affiché sur un pannel
    private Color couleurAff,couleur,couleurSelect; // Couleurs d'affichage et de selection de l'objet
    /* Constructeurs */
    Element(String nom){
        this.nom=nom;
        this.couleur=Color.BLACK;
        this.couleurAff=Color.BLACK;
        this.couleurSelect=Color.GREEN;
    }
    Element(String nom,Color c){
        this.nom=nom;
        this.couleur=c;
        this.couleurAff=c;
        this.couleurSelect=Color.GREEN;
    }
    Element(String nom,Color c,Color c2){
        this.nom=nom;
        this.couleur=c;
        this.couleurAff=c;
        this.couleurSelect=c2;
    }
    /* Méthodes */
    public void setNom(String n){
        this.nom=n;
    }
    public void setCouleur(Color a){
        this.couleur=a;
    }
    public Color getCouleur(){
        return this.couleur;
    }
    public void setCouleurAff(Color a){
        this.couleurAff=a;
    }
    public Color getCouleurAff(){
        return this.couleurAff;
    }
    public void setCouleurSelect(Color a){
        this.couleurSelect=a;
    }
    public Color getCouleurSelect(){
        return this.couleurSelect;
    }
    public String getNom(){
        return this.nom;
    }
    static public void setNomDisplaySize(int n){
        nomDisplaySize=n;
    }
    static public int getNomDisplaySize(){
        return nomDisplaySize;
    }
    public abstract void paint(Graphics g); // Méthode dépendante de l'objet

}
