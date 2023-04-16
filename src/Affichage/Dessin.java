import java.awt.*;
import javax.swing.*;

public class Dessin extends JPanel{
    private Graphe g;
    public Dessin(){
        this.g=new Graphe();
        this.g.addSommet(new Rond(30,300,20));
        this.g.addSommet(new Rond(20,20,20));
        this.g.addSommet(new Rond(450,30,20));
        this.g.addSommet(new Rond(600,95,20));
    }
    public void paint(Graphics g){
        this.g.paint(g);
    }
}