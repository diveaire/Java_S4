package ScreenGUI;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Fenêtre d'application pour le graph-Editor.
 */
public class Windows extends JFrame {
	/**
	 * Le dessin affiché sur la fenêtre d'application
	 */
	protected Dessin d;
	/**
	 * La barre d'outil permettant de choisir l'outil d'application du graphe
	 */
	protected BarreOutils toolBar;
	/**
	 * La barre de menu de l'application pour intégrer les fonctionnalités
	 */
	protected BarreMenu uperBar;
	/**
	 * La barre d'infos/modifications des composants
	 */
	protected InfosBar infosBar;

	/**
	 * Instantiates a new Windows.
	 */
	Windows(){
		super("Graphe");

		/* Parametres de la fenetre */
		this.setLayout(new BorderLayout());
		this.setSize(1980,1080);

		/*Initialisation de notre panel de dessin*/
		this.d=new Dessin(this);


		this.infosBar = new InfosBar(false,this.d);

		/* Création de la barre de menu */
		this.uperBar=new BarreMenu(this.d,this);
		this.setJMenuBar(uperBar);


		/* Création de la barre d'outil */
		this.toolBar=new BarreOutils("Propriétés",this.d);

		/* Placement des composants */
		this.add(infosBar,BorderLayout.EAST);
        this.add(d,BorderLayout.CENTER);
		this.add(toolBar,BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * mise à jour de l'infos barre
	 */
	void updateBar(){
		//On update l'info barre dans la windows
		this.infosBar.update();
		//on valide les changements
		this.infosBar.revalidate();
		this.infosBar.repaint();
	}

	/**
	 * Main.
	 *
	 * @param argv the argv
	 */
	public static void main(String[] argv){
		Windows g = new Windows();
	}
}