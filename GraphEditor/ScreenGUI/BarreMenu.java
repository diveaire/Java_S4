package ScreenGUI;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarreMenu extends JPanel implements ActionListener{
    public BarreMenu(){
        this.setBackground(Color.CYAN);
		this.setForeground(Color.BLACK);
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
		this.add(uperBarBox);
    }
    public void actionPerformed(ActionEvent ae){

    }
}