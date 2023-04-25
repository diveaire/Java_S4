package ScreenGUI;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Windows extends JFrame implements ActionListener{
	private static Windows instance;
    protected Dessin d;
	protected BarreOutils toolBar;
	protected BarreMenu uperBar;
	protected EditBar editBar;
	Windows(){
		super("Graphe");
		this.setLayout(new BorderLayout());
		/*Initialisation de notre panel*/
		this.d=new Dessin();

		this.editBar = new EditBar(this.d);
		this.add(editBar,BorderLayout.EAST);

		/*Création de la barre de menu */
		this.uperBar=new BarreMenu(this.d,this.editBar);
		this.setJMenuBar(uperBar);

		/*Création de la barre d'outil*/
		this.toolBar=new BarreOutils("Propriétés",this.d);

		/*Placement des composants*/
        this.add(d,BorderLayout.CENTER);
		this.add(toolBar,BorderLayout.WEST);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1980,1080);
		this.setVisible(true);
	}
	//permet de lancer une nouvelle instance de la fenêtre
	public static Windows getInstance() {
		if (instance == null) {
			instance = new Windows();
		}
		return instance;
	}
	/*
	public void showHideEditBar() {
		if (editBar == null){
			//fenetre n'existe pas'
			editBar = new EditBar();
			this.add(editBar, BorderLayout.EAST);
			editBar.setText("Masquer EditBar");
		} else {
			//fenetre presente
			//remove(editBar);
			editBar = null;
			mnuEditBar.setText("Afficher EditBar");
		}
	}*/
	void addDessin(Dessin dessin){
		this.d=dessin;
	}
	@Override
	public void actionPerformed(ActionEvent e) {    }
	public static void main(String[] argv){
		Windows g = new Windows();
	}
}