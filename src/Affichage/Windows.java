import java.awt.*;
import javax.swing.*;

public class Windows extends JFrame{
    protected Dessin d;
	Windows(String name){
		super(name);
		this.d=new Dessin();
        this.add(d);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1980,1080);
		this.setVisible(true);
	}
	public static void main(String[] argv){
        Windows g = new Windows("Graphe");
        Sommet a=g.d.getGraphe().getSommet(new Rond("A",10,10,20));
        Sommet b=g.d.getGraphe().getSommet(new Carre("B",30,30,20));
	}
}