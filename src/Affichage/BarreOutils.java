import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarreOutils extends JToolBar implements ActionListener{
    protected Dessin d;
    public BarreOutils(String nom,Dessin draw){
        super(nom);
        this.d=draw;
        ButtonGroup typeSommet = new ButtonGroup();
		JRadioButton typeRond = new JRadioButton("Rond");
        JRadioButton typeCarre = new JRadioButton("Carré");
        JRadioButton typeTriangle = new JRadioButton("Triangle");
        this.setFloatable(true);
		this.setRollover(true);
		this.setBackground(Color.lightGray);
		typeRond.addActionListener(this);
		typeRond.setActionCommand("Rond");
		typeCarre.addActionListener(this);
		typeCarre.setActionCommand("Carre");
		typeTriangle.addActionListener(this);
		typeTriangle.setActionCommand("Triangle");
        typeSommet.add(typeRond);
		typeSommet.add(typeCarre);
		typeSommet.add(typeTriangle);
		this.add(typeRond);
		this.add(typeCarre);
		this.add(typeTriangle);
        JButton suprArc=new JButton("Supprimer Arc");
        suprArc.addActionListener(this);
		suprArc.setActionCommand("SuprArc");
        this.add(suprArc);
    }
    public Dessin getDessin(){
        return this.d;
    }
    public void setDessin(Dessin draw){
        this.d=draw;
    }
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Rond")||action.equals("Carre")||action.equals("Triangle")) {
            this.d.setType(action);
        }
        else if (action.equals("SuprArc")){
            this.d.delSelArc();
        }
    }
}