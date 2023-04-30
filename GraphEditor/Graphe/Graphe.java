package Graphe;

// PACKAGE JAVA
import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.List;

// PACKAGE LOCAL
import Graphe.Forme.*;

/**
 * Class d'un Objet Graphe permettant de faire la représentation d'un graphe
 */
public class Graphe implements Serializable {
    /**
     * Une liste de Sommets
     */
    private ArrayList<Sommet> listSom;
    /**
     * Une liste d'Arc
     */
    private ArrayList<Arc> listArc;

    /**
     * Création du Graphe.
     */
    public Graphe(){
        this.listSom=new ArrayList<Sommet>();
        this.listArc=new ArrayList<Arc>();
    }

    /**
     * Ajouter sommet.
     *
     * @param s Sommet
     */
    public void addSommet(Sommet s){
        if(!isSommetInList(s)){
            this.listSom.add(s);
        }
    }

    /**
     * Récupérer le Sommet. si le Sommet en paramètre appartient au graphe
     *
     * @param s Sommet
     * @return Sommet
     */
    public Sommet getSommet(Sommet s){
        Sommet x=null;
        for(Sommet a : this.listSom){
            /* Si les coordonnées de s se situent dans la zone représentant le sommet, alors on considère qu'il s'agit du sommet recherché puisqu'il ne peut y avoir deux sommets au même endroit*/
            if((((a.getX()<=s.getX())&&((a.getX()+a.getLenght())>=s.getX()))&&((a.getY()<=s.getY())&&((a.getY()+a.getLenght())>=s.getY())))){
                x=a;
            }
        }
        return x;
    }

    /**
     * Méthode qui renvoie la liste de sommet d'un graphe
     * @return liste de sommet d'un graphe (Liste de Sommets)
     */
    public List<Sommet> getSommetList(){
        return this.listSom;
    }
    /**
     * Méthode qui renvoie la liste d'Arc d'un graphe
     * @return liste de sommet d'un graphe (Liste d'Arc)
     */
    public List<Arc> getArcList(){
        return this.listArc;
    }
    /**
     * Méthode qui renvoie un booléen vrai si le Sommet s en paramètre est dans le Graphe
     *
     * @param s Sommet
     * @return Booléen
     */
    public boolean isSommetInList(Sommet s){
        Sommet x=this.getSommet(s);
        return !(x==null);
    }

    /**
     * Méthode qui vérifie si le nom choisie n'est pas déjà existant
     *
     * @param n String nom
     * @return Booléen vrai si le nom est disponible
     */
    public boolean isAvailableName(String n){
        /* Si l'un des éléments du graphe contient ce nom on renvoie faux */
        boolean x=true;
        for(Sommet s : this.listSom){
            if (n.equals(s.getNom())) {
                x = false;
                break;
            }
        }
        for(Arc a : this.listArc){
            if (n.equals(a.getNom())) {
                x = false;
                break;
            }
        }
        return x;
        
    }

    /**
     * Suppression d'un sommet.
     *
     * @param s Le Sommet
     */
    public void delSommet(Sommet s){
        Sommet x=this.getSommet(s);
        /* On créer une liste de suppression contenant tous les arc à supprimer c'est à dire contenant le sommet s */
        ArrayList<Arc> delListe=new ArrayList<Arc>();
        for(Arc a : this.listArc){
            if(a.contain(x)){
                delListe.add(a);
            }
        }
        /* On supprime ensuite tous les arcs de la liste de suppression */
        for(Arc a : delListe){
            this.listArc.remove(a);
        }
        this.listSom.remove(x);
    }

    /**
     * Ajouter un Arc
     *
     * @param a L'Arc
     */
    public void addArc(Arc a){
        if(!isArcInList(a)&&(this.isSommetInList(a.getS1()))&&(this.isSommetInList(a.getS2()))){
            this.listArc.add(a);
        }
    }

    /**
     * Récupère l'Arc si il appartient au Graphe
     *
     * @param a L'Arc
     * @return  L'Arc
     */
    public Arc getArc(Arc a){
        Arc x=null;
        for(Arc al : this.listArc){
            if(a.equals(al)){
                x=al;
            }
        }
        return x;
    }

    /**
     * Méthode qui vérifie si le nom choisie n'est pas déjà existant
     * @param a L'Arc
     * @return booléen vrai si l'arc est dans le Graphe
     */
    public boolean isArcInList(Arc a){
        Arc x=this.getArc(a);
        return !(x==null);
    }

    /**
     * Suppression d'un Arc
     *
     * @param a l'Arc
     */
    public void delArc(Arc a){
        Arc x=this.getArc(a);
        this.listArc.remove(x);
    }

    /**
     * Convertir un Sommet différent de Rond en Rond
     *
     * @param s Le Sommet
     */
    public void convertRond(Sommet s){
        if(this.isSommetInList(s) && !(s instanceof Rond)){
            Rond r=new Rond(s.getNom(),s.getX(),s.getY(),s.getLenght(),s.getCouleur());
            /* Création d'une liste de tous les arcs liés au sommet modifiables que l'on va associé au nouveau sommet */
            ArrayList<Arc> linked=new ArrayList<Arc>();
            for(Arc a : this.listArc){
                if(a.contain(s)){
                    if(a.getS1().equals(s)){
                        a.setS1(r);
                    }
                    else{
                        a.setS2(r);
                    }
                    linked.add(a);
                }
            }
            this.delSommet(s);
            /* Ajout du nouveau sommet et de chaque arc lié à celui-ci */
            this.addSommet(r);
            this.listArc.addAll(linked);
        }
    }

    /**
     * Convertir un Sommet différent de Carre en Carre
     *
     * @param s Le Sommet
     */
    public void convertCarre(Sommet s){
        if(this.isSommetInList(s) && !(s instanceof Carre)){
            Carre c=new Carre(s.getNom(),s.getX(),s.getY(),s.getLenght(),s.getCouleur());
            /* Création d'une liste de tous les arcs liés au sommet modifiables que l'on va associé au nouveau sommet */
            ArrayList<Arc> linked=new ArrayList<Arc>();
            for(Arc a : this.listArc){
                if(a.contain(s)){
                    if(a.getS1().equals(s)){
                        a.setS1(c);
                    }
                    else{
                        a.setS2(c);
                    }
                    linked.add(a);
                }
            }
            this.delSommet(s);
            /* Ajout du nouveau sommet et de chaque arc lié à celui-ci */
            this.addSommet(c);
            this.listArc.addAll(linked);
        }
    }

    /**
     * Convertir un Sommet différent de Triangle en Triangle
     *
     * @param s Le Sommet
     */
    public void convertTriangle(Sommet s){
        if(this.isSommetInList(s) && !(s instanceof Triangle)){
            Triangle t=new Triangle(s.getNom(),s.getX(),s.getY(),s.getLenght(),s.getCouleur());
            /* Création d'une liste de tous les arcs liés au sommet modifiables que l'on va associé au nouveau sommet */
            ArrayList<Arc> linked=new ArrayList<Arc>();
            for(Arc a : this.listArc){
                if(a.contain(s)){
                    if(a.getS1().equals(s)){
                        a.setS1(t);
                    }
                    else{
                        a.setS2(t);
                    }
                    linked.add(a);
                }
            }
            this.delSommet(s);
            /* Ajout du nouveau sommet et de chaque arc lié à celui-ci */
            this.addSommet(t);
            this.listArc.addAll(linked);
        }
    }

    /**
     * Dessine les éléments du Graphe
     *
     * @param g Objet (Graphics)
     */
    public void paint(Graphics g){
        for(Sommet s : this.listSom){
            s.paint(g);
        }
        for(Arc a : this.listArc){
            a.paint(g);
        }
    }
    /**
     * Méthode permettant de faire la sérialisation d'un Graphe pour l'enregistrement de celui-ci
     *
     * @param g Le Graphe
     * @param filename Le nom du fichier (String)
     */
    public void serialize(Graphe g,String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(g);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filename);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Méthode permettant de faire la désérialisation d'un Graphe pour l'ouverture de celui-ci

     *
     * @param filename the filename
     * @return Le Graphe
     */
    public Graphe deserialize(String filename) {
        Graphe g = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            g = (Graphe) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Graphe class not found");
            c.printStackTrace();
        }
        return g;
    }

}

