import java.util.*;
import java.awt.*;
import java.io.Serializable;
import javax.swing.*;

class Graphe implements Serializable {
    private ArrayList<Sommet> listSom;
    private ArrayList<Arc> listArc;
    public Graphe(){
        this.listSom=new ArrayList<Sommet>();
        this.listArc=new ArrayList<Arc>();
    }
    public void addSommet(Sommet s){
        this.listSom.add(s);
    }
    public void addArc(Sommet a,Sommet b){
        this.listArc.add(new Arc(a,b));
    }
    public void paint(Graphics g){
        for(Sommet s : this.listSom){
            s.paint(g);
        }
        for(Arc a : this.listArc){
            a.paint(g);
        }
    }
}