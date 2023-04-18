import javax.swing.*;
import java.awt.*;

public class Arc extends Element{
    private Sommet s1,s2;
    Arc(Sommet a,Sommet b){
        super("",Color.BLACK);
        this.s1=a;
        this.s2=b;
    }
    Arc(Sommet a,Sommet b,String n,Color c){
        super(n,c);
        this.s1=a;
        this.s2=b;
    }
    public void setS1(Sommet a){
        this.s1=a;
    }
    public void setS2(Sommet a){
        this.s2=a;
    }
    public Sommet getS1(){
        return this.s1;
    }
    public Sommet getS2(){
        return this.s2;
    }
    public boolean contain(Sommet a){
        return (a.equals(this.s1))||(a.equals(this.s2));
    }
    public boolean equals(Arc a){
        return (this.s1.equals(a.getS1())&&(this.s2.equals(a.getS2())))||(this.s1.equals(a.getS2())&&(this.s2.equals(a.getS1())));
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.getCouleur());
        g2.drawLine(this.s1.getXCenter(),this.s1.getYCenter(),this.s2.getXCenter(),this.s2.getYCenter());
    }
}