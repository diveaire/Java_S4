package ScreenGUI;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Windows extends JFrame implements ActionListener{
    protected Dessin d;
	protected BarreOutils toolBar;
	protected BarreMenu uperBar;
	Windows(String name){
		super(name);
		this.setLayout(new BorderLayout());
		/*Initialisation de notre panel*/
		this.d=new Dessin();

		/*Création de la barre de menu */
		this.uperBar=new BarreMenu();

		/*Création de la barre d'outil*/
		this.toolBar=new BarreOutils("Propriétés",this.d);

		/*Placement des composants*/
        this.add(d,BorderLayout.CENTER);
		this.add(toolBar,BorderLayout.WEST);
		this.add(uperBar,BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1980,1080);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
        
	}
	public static void main(String[] argv){
		Windows g = new Windows("Graphe");
	}
}