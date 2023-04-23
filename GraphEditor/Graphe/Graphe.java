package Graphe;

// PACKAGE JAVA
import java.util.*;
import java.awt.*;
import java.io.Serializable;
// PACKAGE LOCAL
import Graphe.Forme.*;

public class Graphe implements Serializable {
    private ArrayList<Sommet> listSom;
    public ArrayList<Arc> listArc;
    public Graphe(){
        this.listSom=new ArrayList<Sommet>();
        this.listArc=new ArrayList<Arc>();
    }
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
        boolean x=true;
        for(Sommet s : this.listSom){
            if(n.equals(s.getNom())){
                x=false;
            }
        }
        for(Arc a : this.listArc){
            if(n.equals(a.getNom())){
                x=false;
            }
        }
        return x;
        
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
        if(!isArcInList(new Arc(a,b))&&(this.isSommetInList(a))&&(this.isSommetInList(b))){
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
        Arc x=this.getArc(a);
        this.listArc.remove(x);
    }
        public void convertRond(Sommet s){
        if(!(s instanceof Rond)){
            Rond r=new Rond(s.getNom(),s.getX(),s.getY(),s.getLenght(),s.getCouleur());
            ArrayList<Arc> linked=new ArrayList<Arc>();
            for(Arc a : this.listArc){
                if(a.contain(s)){
                    linked.add(a);
                    this.delArc(a);
                }
            }
            this.delSommet(s);
            this.addSommet(r);
            for(Arc a1 : linked){
                this.listArc.add(a1);
            }
        }
    }
        public void convertCarre(Sommet s){
        if(!(s instanceof Carre)){
            Carre c=new Carre(s.getNom(),s.getX(),s.getY(),s.getLenght(),s.getCouleur());
            ArrayList<Arc> linked=new ArrayList<Arc>();
            for(Arc a : this.listArc){
                if(a.contain(s)){
                    linked.add(a);
                    this.delArc(a);
                }
            }
            this.delSommet(s);
            this.addSommet(c);
            for(Arc a1 : linked){
                this.listArc.add(a1);
            }
        }
    }
        public void convertTriangle(Sommet s){
        if(!(s instanceof Triangle)){
            Triangle t=new Triangle(s.getNom(),s.getX(),s.getY(),s.getLenght(),s.getCouleur());
            ArrayList<Arc> linked=new ArrayList<Arc>();
            for(Arc a : this.listArc){
                if(a.contain(s)){
                    linked.add(a);
                    this.delArc(a);
                }
            }
            this.delSommet(s);
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
}
