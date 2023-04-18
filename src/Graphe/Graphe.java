import java.util.*;
import java.awt.*;
import java.io.Serializable;
import javax.swing.*;

public class Graphe implements Serializable {
    private ArrayList<Sommet> listSom;
    private ArrayList<Arc> listArc;
    public Graphe(){
        this.listSom=new ArrayList<Sommet>();
        this.listArc=new ArrayList<Arc>();
    }
    public void addSommet(Sommet s){
        this.listSom.add(s);
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
    public void delSommet(Sommet s){
        Sommet x=this.getSommet(s);
        ArrayList<Arc> delListe=new ArrayList<Arc>();
        for(Arc a : this.listArc){
            if(a.contain(x)){
                delListe.add(a);
            }
        }
        for(Arc a : delListe){
            this.listArc.remove(a);
        }
        this.listSom.remove(x);
    }
    public void addArc(Sommet a,Sommet b){
        if((this.getSommet(a)!=null)&&(this.getSommet(b)!=null)){
            this.listArc.add(new Arc(a,b));
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
        this.listArc.remove(a);
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