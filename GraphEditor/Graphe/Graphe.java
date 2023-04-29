package Graphe;

// PACKAGE JAVA
import java.io.*;
import java.util.*;
import java.awt.*;

// PACKAGE LOCAL
import Graphe.Forme.*;

public class Graphe implements Serializable {
    /* Attributs */
    private ArrayList<Sommet> listSom; // La liste des sommets constituant le graphe
    public ArrayList<Arc> listArc; // La liste des arcs constituant le graphe
    /* Constructeurs */
    public Graphe(){
        this.listSom=new ArrayList<Sommet>();
        this.listArc=new ArrayList<Arc>();
    }
    /* Méthodes */
    public void addSommet(Sommet s){
        if(!isSommetInList(s)){
            this.listSom.add(s);
        }
    }
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
    public boolean isSommetInList(Sommet s){
        Sommet x=this.getSommet(s);
        return !(x==null);
    }
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
    public void addArc(Arc a){
        if(!isArcInList(a)&&(this.isSommetInList(a.getS1()))&&(this.isSommetInList(a.getS2()))){
            this.listArc.add(a);
        }
    }
    public Arc getArc(Arc a){
        Arc x=null;
        for(Arc al : this.listArc){
            if(a.equals(al)){
                x=al;
            }
        }
        return x;
    }
    public boolean isArcInList(Arc a){
        Arc x=this.getArc(a);
        return !(x==null);
    }
    public void delArc(Arc a){
        Arc x=this.getArc(a);
        this.listArc.remove(x);
    }
    /* Méthode de conversion d'un sommet en un autre */
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
            for(Arc a1 : linked){
                this.listArc.add(a1);
            }
        }
    }
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
            for(Arc a1 : linked){
                this.listArc.add(a1);
            }
        }
    }
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
            for(Arc a1 : linked){
                this.listArc.add(a1);
            }
        }
    }
    public void paint(Graphics g){
        for(Sommet s : this.listSom){
            s.paint(g);
        }
        for(Arc a : this.listArc){
            a.paint(g);
        }
    }
    /* Méthodes de sérialization et de désérialization d'un graphe */
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

