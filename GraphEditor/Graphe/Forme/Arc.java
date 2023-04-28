package Graphe.Forme;

import java.awt.*;
import javax.swing.*;

public abstract class Arc extends Element {
    private Sommet s1,s2;
    private float thickness;
    private boolean oriente;
    public Arc(Sommet a, Sommet b){
        super("",Color.BLACK);
        this.s1=a;
        this.s2=b;
        this.thickness=1;
    }
    public Arc(Sommet a,Sommet b,String n,Color c){
        super(n,c);
        this.s1=a;
        this.s2=b;
        this.thickness=1;
    }
    public void setS1(Sommet a){
        this.s1=a;
    }
    public void setS2(Sommet a){
        this.s2=a;
    }
    public void setThickness(float a){
        this.thickness=a;
    }
    public Sommet getS1(){
        return this.s1;
    }
    public Sommet getS2(){
        return this.s2;
    }
    public float getThickness(){
        return this.thickness;
    }
    public boolean contain(Sommet a){
        return (a.equals(this.s1))||(a.equals(this.s2));
    }
    public boolean equals(Arc a){
        return (this.s1.equals(a.getS1())&&(this.s2.equals(a.getS2())))||(this.s1.equals(a.getS2())&&(this.s2.equals(a.getS1())));
    }
}