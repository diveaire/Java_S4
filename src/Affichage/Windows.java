import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Windows extends JFrame implements ActionListener{
    protected Dessin d;
	protected JToolBar toolBar;
	Windows(String name){
		super(name);
		this.setLayout(new BorderLayout());
		/*Initialisation de notre panel*/
		this.d=new Dessin();
		/*Création de la barre d'outil*/
		this.toolBar=new JToolBar("Propriétés");
		this.toolBar.setFloatable(true);
		this.toolBar.setRollover(true);
		this.toolBar.setBackground(Color.lightGray);
		/*Création des boutons permettant de sélectioner le type de sommet */
		ButtonGroup typeSommet = new ButtonGroup();
		JRadioButton typeRond = new JRadioButton("Rond");
        JRadioButton typeCarre = new JRadioButton("Carré");
        JRadioButton typeTriangle = new JRadioButton("Triangle");
		typeRond.addActionListener(this);
		typeRond.setActionCommand("Rond");
		typeCarre.addActionListener(this);
		typeCarre.setActionCommand("Carre");
		typeTriangle.addActionListener(this);
		typeTriangle.setActionCommand("Triangle");
        typeSommet.add(typeRond);
		typeSommet.add(typeCarre);
		typeSommet.add(typeTriangle);
		this.toolBar.add(typeRond);
		this.toolBar.add(typeCarre);
		this.toolBar.add(typeTriangle);
		/*Placement des composants*/
        this.add(d,BorderLayout.CENTER);
		this.add(toolBar,BorderLayout.WEST);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1980,1080);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Rond")||action.equals("Carre")||action.equals("Triangle")) {
            this.d.setType(action);
        }
    }
	public static void main(String[] argv){
        Windows g = new Windows("Graphe");
        Sommet a=g.d.getGraphe().getSommet(new Rond("A",10,10,20));
        Sommet b=g.d.getGraphe().getSommet(new Carre("B",30,30,20));
	}
}