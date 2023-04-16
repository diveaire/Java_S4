import java.awt.*;
import javax.swing.*;

public class Dessin extends JPanel{
    private Graphe g;
    public Dessin(){
        this.g=new Graphe();
        this.g.addSommet(new Rond("A",0,0,20));
        this.g.addSommet(new Carre("B",30,30,20));
        this.g.addSommet(new Triangle("C",50,50,20,Color.RED));
        this.g.addSommet(new Rond("D",430,430,20,Color.RED));
        this.g.addArc(new Rond("A",0,0,20),new Carre("B",30,30,20));
    }
    public void paint(Graphics g){
        this.g.paint(g);
    }
}