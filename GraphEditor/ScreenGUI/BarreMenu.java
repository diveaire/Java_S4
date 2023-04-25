package ScreenGUI;


import javax.swing.*;
import java.awt.event.*;
import Graphe.*;
import ScreenGUI.*;

public class BarreMenu extends JMenuBar implements ActionListener{
    private boolean test=false;
    private JMenu mnuFile,mnuEdit,mnuAff,mnuHelp;
    private JMenuItem mnuNewFile, mnuOpenFile,mnuSaveFile,mnuSaveFileAs,mnuExit,mnuUndo,mnuRedo,mnuCopy,mnuCut,mnuPaste,mnuEditBar;
    Dessin D;
    private EditBar eb;
    public BarreMenu(Dessin D, EditBar eb) {
        if (test){
            return;
        }else{
            CreateBar(D,eb);
            return;
        }
    }
    private void CreateBar(Dessin d,EditBar eb){
        this.D = d;
        this.eb = eb;
        //SECTION MENU FILE
        mnuFile = new JMenu( "File" );
        mnuFile.setMnemonic( 'F' );
        //SOUS SECTION newFile
        mnuNewFile = new JMenuItem( "New File" );
        mnuNewFile.setIcon( new ImageIcon( "Files/Icons/new.png" ) );
        mnuNewFile.setMnemonic( 'N' );
        mnuNewFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK) ); //CTRL + N
        mnuNewFile.addActionListener(this::mnuNewList);
        mnuFile.add(mnuNewFile);

        mnuFile.addSeparator();

        //SOUS SECTION OpenFile
        mnuOpenFile = new JMenuItem( "Open File (CTRL + O)" );
        mnuOpenFile.setIcon( new ImageIcon( "Files/Icons/open.png" ) );
        mnuOpenFile.setMnemonic( 'O' );
        mnuOpenFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK) ); //CTRL + O
        mnuOpenFile.addActionListener(this::mnuOpenList);
        mnuFile.add(mnuOpenFile);

        //SOUS SECTION saveFile
        mnuSaveFile = new JMenuItem( "Save File (CTRL+S)" );
        mnuSaveFile.setIcon( new ImageIcon( "Files/Icons/save.png" ) );
        mnuSaveFile.setMnemonic( 'S' );
        mnuSaveFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK) ); //CTRL + S
        mnuSaveFile.addActionListener(this::mnuSaveList);
        mnuFile.add(mnuSaveFile);

        mnuSaveFileAs = new JMenuItem( "Save File As (CTRL+Shift+S)" );
        mnuSaveFileAs.setIcon( new ImageIcon( "Files/Icons/save_as.png" ) );
        mnuSaveFileAs.addActionListener(this::mnuSaveAsList);
        mnuSaveFileAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.SHIFT_DOWN_MASK | KeyEvent.CTRL_DOWN_MASK) ); //CTRL + shift + S
        mnuFile.add(mnuSaveFileAs);

        mnuFile.addSeparator();

        mnuExit = new JMenuItem( "Exit (ALT+F4)" );
        mnuExit.setIcon( new ImageIcon( "Files/Icons/exit.png" ) );
        mnuExit.setMnemonic( 'x' );
        mnuExit.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK) );
        mnuExit.addActionListener(this::mnuExitList);
        mnuFile.add(mnuExit);

        add(mnuFile);

        // Définition du menu déroulant "Edit" et de son contenu
        mnuEdit = new JMenu( "Edit" );
        mnuEdit.setMnemonic( 'E' );

        mnuUndo = new JMenuItem( "Undo" );
        mnuUndo.setIcon( new ImageIcon( "Files/Icons/undo.png" ) );
        mnuUndo.setMnemonic( 'U' );
        mnuUndo.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK) );
        mnuUndo.addActionListener(this::mnuUndoList);
        mnuEdit.add(mnuUndo);

        mnuRedo = new JMenuItem( "Redo" );
        mnuRedo.setIcon( new ImageIcon( "Files/Icons/redo.png" ) );
        mnuRedo.setMnemonic( 'R' );
        mnuRedo.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK) );
        mnuRedo.addActionListener(this::mnuRedoList);
        mnuEdit.add(mnuRedo);

        mnuEdit.addSeparator();

        mnuCopy = new JMenuItem("Copy");
        mnuCopy.setIcon(new ImageIcon( "Files/Icons/copy.png" ));
        mnuCopy.setMnemonic( 'C' );
        mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        mnuCopy.addActionListener(this::mnuCopyList);
        mnuEdit.add(mnuCopy);

        mnuCut = new JMenuItem("Cut");
        mnuCut.setIcon( new ImageIcon( "Files/Icons/cut.png" ) );
        mnuCut.setMnemonic('t');
        mnuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        mnuCut.addActionListener(this::mnuCutList);
        mnuEdit.add(mnuCut);

        mnuPaste = new JMenuItem("Paste");
        mnuPaste.setIcon( new ImageIcon( "Files/Icons/paste.png" ) );
        mnuPaste.setMnemonic( 'P' );
        mnuPaste.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK) );
        mnuPaste.addActionListener(this::mnuPasteList);
        mnuEdit.add(mnuPaste);

        add(mnuEdit);

        // Définition du menu déroulant "Edit" et de son contenu
        mnuAff = new JMenu("Affichage");
        mnuEditBar = new JMenuItem("Afficher EditBar");
        mnuEditBar.setIcon( new ImageIcon( "..." ) );
        mnuEditBar.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK) );
        mnuEditBar.addActionListener(this::mnuEditBarList);
        mnuAff.add(mnuEditBar);

        add(mnuAff);

        // Définition du menu déroulant "Help" et de son contenu
        mnuHelp = new JMenu( "Help" );

        add( mnuHelp );
    }
    public void mnuNewList( ActionEvent event ) {
        JOptionPane.showMessageDialog(this,"New file clicked !");
        D.setNewGraph();
        //On ajoute le dessin à la fenêtre
        //Windows.getInstance().addDessin(g);
    }
    public void mnuOpenList( ActionEvent event ) {
        JOptionPane.showMessageDialog(this,"Open file clicked !");
    }
    public void mnuSaveList( ActionEvent event ) {
        JOptionPane.showMessageDialog(this,"Save clicked !");
    }
    public void mnuSaveAsList(ActionEvent event) {
        JOptionPane.showMessageDialog(this,"SaveAs clicked !");
    }
    public void mnuExitList(ActionEvent event) {
        JOptionPane.showMessageDialog(this,"Exit clicked !");
    }
    public void mnuUndoList(ActionEvent event) {
        JOptionPane.showMessageDialog(this,"Undo clicked !");
    }
    public void mnuRedoList(ActionEvent event) {
        JOptionPane.showMessageDialog(this,"Redo clicked !");
    }
    public void mnuCopyList(ActionEvent event) {
        JOptionPane.showMessageDialog(this,"Copy clicked !");
    }
    public void mnuCutList(ActionEvent event) {
        JOptionPane.showMessageDialog(this,"Cut clicked !");
    }
    public void mnuPasteList(ActionEvent event) {
        JOptionPane.showMessageDialog(this,"Paste clicked !");
    }
    public void mnuEditBarList(ActionEvent event) {
        eb.showEditBar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {    }
}
