package ScreenGUI;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarreOutils extends JToolBar implements ActionListener{
    protected Dessin d;
    public BarreOutils(String nom,Dessin draw){
        super(nom);
        this.d=draw;

        this.setFloatable(true);
        this.setRollover(true);
        this.setBackground(Color.lightGray);

        ButtonGroup typeSommet = new ButtonGroup();

        JRadioButton typeAucun = new JRadioButton("Aucun");
        typeAucun.addActionListener(this);
        typeAucun.setActionCommand("Aucun");

		JRadioButton typeRond = new JRadioButton("Rond");
        typeRond.addActionListener(this);
        typeRond.setActionCommand("Rond");

        JRadioButton typeCarre = new JRadioButton("Carré");
        typeCarre.addActionListener(this);
        typeCarre.setActionCommand("Carre");

        JRadioButton typeTriangle = new JRadioButton("Triangle");
        typeTriangle.addActionListener(this);
        typeTriangle.setActionCommand("Triangle");

        typeSommet.add(typeAucun);
        typeSommet.add(typeRond);
		typeSommet.add(typeCarre);
		typeSommet.add(typeTriangle);

		this.add(typeAucun);
        this.add(typeRond);
		this.add(typeCarre);
		this.add(typeTriangle);


        this.addSeparator();


        ButtonGroup typeArc = new ButtonGroup();
        JRadioButton typeArete = new JRadioButton("Arete");
		JRadioButton typeOriente = new JRadioButton("Arc");
        typeArete.addActionListener(this);
		typeArete.setActionCommand("Arete");
		typeOriente.addActionListener(this);
		typeOriente.setActionCommand("Arc");
        typeArc.add(typeArete);
        typeArc.add(typeOriente);
        this.add(typeArete);
        this.add(typeOriente);

        this.addSeparator();

        JButton suprArc=new JButton("Supprimer Arc");
        suprArc.addActionListener(this);
        suprArc.setActionCommand("SuprArc");
        this.add(suprArc);

        this.addSeparator();

        JButton convertR = new JButton("Convert to Rond");
        convertR.addActionListener(e -> {
            d.getGraphe().convertRond(d.getSelSom());
            d.repaint();
        });
        this.add(convertR);
        JButton convertC = new JButton("Convert to Carre");
        convertC.addActionListener(e -> {
            d.getGraphe().convertCarre(d.getSelSom());
            d.repaint();
        });
        this.add(convertC);
        JButton convertT = new JButton("Convert to Triangle");
        convertT.addActionListener(e -> {
            if (d.getSelSom()!=null && d.getPSelSom()==null && d.getSelArc()==null){
                d.getGraphe().convertTriangle(d.getSelSom());
                d.setSelSom(null);
                d.repaint();
            }
        });
        this.add(convertT);
    }
    public Dessin getDessin(){
        return this.d;
    }
    public void setDessin(Dessin draw){
        this.d=draw;
    }
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
    }
}