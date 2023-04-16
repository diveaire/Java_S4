import java.util.*;
import java.awt.*;
import java.io.Serializable;
import javax.swing.*;

class Graphe implements Serializable {
    private ArrayList<Sommet> listSom;
    public Graphe(){
        this.listSom=new ArrayList<Sommet>();
    }
    public void addSommet(Sommet s){
        this.listSom.add(s);
    }
    public void paint(Graphics g){
        for(Sommet s : this.listSom){
            s.paint(g);
        }
    }
}