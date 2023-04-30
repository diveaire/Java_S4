package ScreenGUI;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * Une barre d'outil permettant de faire le choix de l'outil à dessiner dans le Dessin
 */
public class BarreOutils extends JToolBar implements ActionListener{
    /**
     * Le Dessin
     */
    protected Dessin d;
    /**
     * Les boutons sélections
     */
    private JRadioButton typeAucun,typeRond,typeCarre,typeTriangle,typeArete,typeOriente;
    /**
     * Bouton Group
     */
    private ButtonGroup typeSommet,typeArc;
    /**
     * Bouton de conversion objet
     */
    private JButton convRond, convCarre, convTriangle,suprArc;
    /**
     * JPanel
     */
    private JPanel selType,selArc,convLab,supLab;

    /**
     * Création de la Barre outils.
     *
     * @param nom  le nom de la barre d'outil
     * @param draw le dessin
     */
    public BarreOutils(String nom,Dessin draw){
        super(nom);
        this.d=draw;

        /* Initialisation des propriétés de la barre d'outils */
        this.setFloatable(true);
		this.setRollover(true);
		this.setBackground(Color.WHITE);
        Border appBorder = BorderFactory.createLineBorder(Color.DARK_GRAY);

        /* Création des boutons de sélection du type de sommet */
        this.selType = new JPanel();
        this.selType.setBorder(appBorder);
        this.selType.setBackground(Color.CYAN);
        this.selType.setLayout(new GridLayout(5,1));
        this.selType.add(new JLabel("TYPE DE SOMMET :"));

        this.typeSommet = new ButtonGroup();

        this.typeAucun = new JRadioButton("Aucun");
        this.typeAucun.addActionListener(this);
        this.typeAucun.setActionCommand("Aucun");

		this.typeRond = new JRadioButton("Rond");
        this.typeRond.addActionListener(this);
        this.typeRond.setActionCommand("Rond");

        this.typeCarre = new JRadioButton("Carré");
        this.typeCarre.addActionListener(this);
        this.typeCarre.setActionCommand("Carre");

        this.typeTriangle = new JRadioButton("Triangle");
        this.typeTriangle.addActionListener(this);
        this.typeTriangle.setActionCommand("Triangle");

        this.typeSommet.add(this.typeAucun);
        this.typeSommet.add(this.typeRond);
		this.typeSommet.add(this.typeCarre);
		this.typeSommet.add(this.typeTriangle);

		this.selType.add(this.typeAucun);
        this.selType.add(this.typeRond);
		this.selType.add(this.typeCarre);
		this.selType.add(this.typeTriangle);

        this.add(this.selType);

        /* Création des boutons de conversion des sommets */
        this.convLab = new JPanel();
        this.convLab.setBorder(appBorder);
        this.convLab.setBackground(Color.CYAN);
        this.convLab.setLayout(new GridLayout(4,1));
        this.convLab.add(new JLabel("  CHANGER LE TYPE DU SOMMET :"));
        this.convRond=new JButton("Convertir en Rond");
        this.convRond.addActionListener(this);
		this.convRond.setActionCommand("convR");
        this.convLab.add(convRond);

        this.convCarre=new JButton("Convertir en Carre");
        this.convCarre.addActionListener(this);
		this.convCarre.setActionCommand("convC");
        this.convLab.add(convCarre);

        this.convTriangle=new JButton("Convertir en Triangle");
        this.convTriangle.addActionListener(this);
		this.convTriangle.setActionCommand("convT");
        this.convLab.add(convTriangle);
        this.add(this.convLab);

        this.addSeparator();


        /* Création des boutons de sélection des arcs */
        this.selArc = new JPanel();
        this.selArc.setBorder(appBorder);
        this.selArc.setBackground(Color.CYAN);
        this.selArc.setLayout(new GridLayout(3,1));
        this.selArc.add(new JLabel("  TYPE D'ARC :"));
        this.typeArc = new ButtonGroup();
        this.typeArete = new JRadioButton("Arete");
		this.typeOriente = new JRadioButton("Arc");
        this.typeArete.addActionListener(this);
		this.typeArete.setActionCommand("Arete");
		this.typeOriente.addActionListener(this);
		this.typeOriente.setActionCommand("Arc");
        this.typeArc.add(this.typeArete);
        this.typeArc.add(this.typeOriente);
        this.selArc.add(this.typeArete);
        this.selArc.add(this.typeOriente);
        this.add(this.selArc);

        /* Création du bouton de suppression d'un arc */
        this.supLab = new JPanel();
        this.supLab.setBorder(appBorder);
        this.suprArc=new JButton("Supprimer Arc");
        this.suprArc.addActionListener(this);
		this.suprArc.setActionCommand("SuprArc");
        this.supLab.add(suprArc);
        this.add(this.supLab);
    }
    /* Méthode */

    /**
     * récupère le dessin.
     *
     * @return dessin
     */
    public Dessin getDessin(){
        return this.d;
    }

    /**
     * définir le dessin.
     *
     * @param draw le dessin
     */
    public void setDessin(Dessin draw){
        this.d=draw;
    }

    /**
     * Fonction permettant de valider l'action de la barre d'outils
     * @param ae ActionEvent ae
     */
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Rond")||action.equals("Carre")||action.equals("Triangle")||action.equals("Aucun")) {
            this.d.setType(action);
        }
        else if (action.equals("Arete")){
            this.d.setOrientedArc(false);
        }
        else if (action.equals("Arc")){
            this.d.setOrientedArc(true);
        }
        else if (action.equals("SuprArc")){
            this.d.delSelArc();
        }
        else if (action.equals("convR")){
            if(this.d.getSelSom()!=null){
                this.d.getGraphe().convertRond(this.d.getSelSom());
                this.d.setSelSom(null);
                this.d.setPrevSelSom(null);
                this.d.repaint();
            }
        }
        else if (action.equals("convC")){
            if(this.d.getSelSom()!=null){
                this.d.getGraphe().convertCarre(this.d.getSelSom());
                this.d.setSelSom(null);
                this.d.setPrevSelSom(null);
                this.d.repaint();
            }
        }
        else if (action.equals("convT")){
            if(this.d.getSelSom()!=null){
                this.d.getGraphe().convertTriangle(this.d.getSelSom());
                this.d.setSelSom(null);
                this.d.setPrevSelSom(null);
                this.d.repaint();
            }
        }
    }
}