package Graphe.Forme;

import java.io.*;
import javax.swing.*;
import java.awt.*;

/**
 * Objet abstrait permettant  de représenté tout objet dans Graphe
 */
public abstract class Element implements Serializable{
    /**
     * Nom de l'objet
     */
    private String nom;
    /**
     * Taille du nom de l'objet affiché sur un pannel
     */
    private static int nomDisplaySize=15;
    /**
     * Couleurs d'affichage et de selection de l'objet
     */
    private Color couleurAff,couleur,couleurSelect;

    /**
     * Création d'un Element.
     *
     * @param nom the nom
     */
    /* Constructeurs */
    Element(String nom){
        this.nom=nom;
        this.couleur=Color.BLACK;
        this.couleurAff=Color.BLACK;
        this.couleurSelect=Color.GREEN;
    }

    /**
     * Création d'un Element
     *
     * @param nom Le nom
     * @param c   La couleur
     */
    Element(String nom,Color c){
        this.nom=nom;
        this.couleur=c;
        this.couleurAff=c;
        this.couleurSelect=Color.GREEN;
    }

    /**
     * Création d'un Element
     *
     * @param nom le nom
     * @param c   la couleur
     * @param c2  la couleur de selection
     */
    Element(String nom,Color c,Color c2){
        this.nom=nom;
        this.couleur=c;
        this.couleurAff=c;
        this.couleurSelect=c2;
    }

    /**
     * définir le nom de l'objet
     *
     * @param n Le nom à attribué
     */
    /* Méthodes */
    public void setNom(String n){
        this.nom=n;
    }

    /**
     * définir la couleur de l'objet
     * @param a la couleur à attribué à l'objet
     */
    public void setCouleur(Color a){
        this.couleur=a;
    }

    /**
     * récupèrer la couleur de l'objet
     *
     * @return la couleur de l'objet
     */
    public Color getCouleur(){
        return this.couleur;
    }

    /**
     * définir la couleur Affichée à l'écran de l'objet
     *
     * @param a la couleur Affichée à l'écran
     */
    public void setCouleurAff(Color a){
        this.couleurAff=a;
    }

    /**
     * récupèrer la couleur Affichée à l'écran de l'objet
     *
     * @return la couelur Affichée à l'écran'
     */
    public Color getCouleurAff(){
        return this.couleurAff;
    }

    /**
     * définir la couleur de sélection de l'objet
     *
     * @param a la couleur de sélection
     */
    public void setCouleurSelect(Color a){
        this.couleurSelect=a;
    }

    /**
     * Récupère la couleur de sélection de l'objet
     *
     * @return la couleur de sélection
     */
    public Color getCouleurSelect(){
        return this.couleurSelect;
    }

    /**
     * Récupère le nom de l'objet
     *
     * @return la nom de l'objet
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * définir la taille de la police du nom affiché
     *
     * @param n la taille de la police à attribué
     */
    static public void setNomDisplaySize(int n){
        nomDisplaySize=n;
    }

    /**
     * Récupère la taille de la police du nom affiché
     *
     * @return taille de la police du nom affiché
     */
    static public int getNomDisplaySize(){
        return nomDisplaySize;
    }
    @Override
    public String toString(){
        return this.nom;
    }

    /**
     * Méthode abstraite de dessin
     *
     * @param g Objet (Graphics)
     */
    public abstract void paint(Graphics g); // Méthode dépendante de l'objet

}
