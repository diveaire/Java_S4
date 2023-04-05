package Affichage;

import java.awt.*;
import javax.swing.*;

public class Windows extends JFrame{
	public Windows(String name){
		super(name);
		
		//toolBox
		JToolBar tb = new JToolBar("ToolBox", 1);
		tb.setFloatable(true);
		tb.setRollover(true);

        ButtonGroup btnGp = new ButtonGroup();
        JRadioButton rBtn1 = new JRadioButton("Rond");
        JRadioButton rBtn2 = new JRadioButton("Carré");
        JRadioButton rBtn3 = new JRadioButton("Triangle");
        btnGp.add( rBtn1 );
        btnGp.add( rBtn2 );
        btnGp.add( rBtn3 );
        tb.add((Action) btnGp);
        JButton btnNew = new JButton( new ImageIcon( "icons/new.png") );
        btnNew.setToolTipText( "New File (CTRL+N)" );
        tb.add( btnNew );

        JButton btnSave = new JButton( new ImageIcon( "icons/save.png" ) );
        btnSave.setToolTipText( "Save (CTRL+S)" );
        tb.add( btnSave );

        JButton btnSaveAs = new JButton( new ImageIcon( "icons/save_as.png" ) );
        btnSaveAs.setToolTipText( "Save As..." );
        tb.add( btnSaveAs );

        tb.addSeparator();

        JButton btnCopy = new JButton( new ImageIcon( "icons/copy.png") );
        btnCopy.setToolTipText( "Copy (CTRL+C)" );
        tb.add( btnCopy );

        JButton btnCut = new JButton( new ImageIcon( "icons/cut.png") );
        btnCut.setToolTipText( "Cut (CTRL+X)" );
        tb.add( btnCut );

        JButton btnPaste = new JButton( new ImageIcon( "icons/paste.png") );
        btnPaste.setToolTipText( "Paste (CTRL+V)" );
        tb.add( btnPaste );

        tb.addSeparator();

        JButton btnExit = new JButton( new ImageIcon( "icons/exit.png") );
        btnExit.setToolTipText( "Exit (ALT+F4)" );
        tb.add( btnExit );

        //tb.addSeparator();
        // Autres types de composants graphiques
        //tb.add( new JCheckBox( "Check me" ) );
        //tb.add( new JTextField( "Edit me" ) );
		
		//panel
		JScrollPane aff = new JScrollPane();
		
		//Propriétés
		JLabel prop = new JLabel("Panel PROP");
		
		
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,aff,prop);
		panel.setDividerSize(20);
		panel.setDividerLocation(1200);
		
		this.add(panel);
		this.getContentPane().add(tb, BorderLayout.WEST);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1980,1080);
		this.setVisible(true);
	}

	public static void main(String[] argv){
        Windows G = new Windows("Graphe");
	}
	
	
	
}
