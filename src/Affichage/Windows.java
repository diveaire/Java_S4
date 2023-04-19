import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Windows extends JFrame implements ActionListener{
    protected Dessin d;
	protected JToolBar toolBar;
	protected JPanel uperBar;
	Windows(String name){
		super(name);
		this.setLayout(new BorderLayout());

		/*Initialisation de notre panel*/
		this.d=new Dessin();

		/*Création de la barre de menu */
		this.uperBar=new JPanel();
		this.uperBar.setBackground(Color.CYAN);
		this.uperBar.setForeground(Color.BLACK);
		Box uperBarBox=new Box(BoxLayout.X_AXIS);
		JButton btnNew = new JButton( new ImageIcon( "icons/new.png") );
        btnNew.setToolTipText( "New File (CTRL+N)" );
        uperBarBox.add( btnNew );

        JButton btnSave = new JButton( new ImageIcon( "icons/save.png" ) );
        btnSave.setToolTipText( "Save (CTRL+S)" );
        uperBarBox.add( btnSave );

        JButton btnSaveAs = new JButton( new ImageIcon( "icons/save_as.png" ) );
        btnSaveAs.setToolTipText( "Save As..." );
        uperBarBox.add( btnSaveAs );

        JButton btnCopy = new JButton( new ImageIcon( "icons/copy.png") );
        btnCopy.setToolTipText( "Copy (CTRL+C)" );
        uperBarBox.add( btnCopy );

        JButton btnCut = new JButton( new ImageIcon( "icons/cut.png") );
        btnCut.setToolTipText( "Cut (CTRL+X)" );
        uperBarBox.add( btnCut );

        JButton btnPaste = new JButton( new ImageIcon( "icons/paste.png") );
        btnPaste.setToolTipText( "Paste (CTRL+V)" );

        JButton btnExit = new JButton( new ImageIcon( "icons/exit.png") );
        btnExit.setToolTipText( "Exit (ALT+F4)" );
        uperBarBox.add( btnExit );
		this.uperBar.add(uperBarBox);

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
		this.add(uperBar,BorderLayout.NORTH);
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
	}
}