package ScreenGUI;


import Graphe.Forme.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Barre de Menu Pour intéragir avec la fenêtre Window
 */
public class BarreMenu extends JMenuBar implements ActionListener{
    /**
     * Booléen permettant de definir si la fenêtre est affichée ou non
     */
    private boolean test=false;
    /**
     * Différentes sections JMenu
     */
    private JMenu mnuFile,mnuEdit,mnuAff,mnuHelp;
    /**
     * JMenuItem
     */
    private JMenuItem mnuNewFile, mnuOpenFile,mnuSaveFile,mnuSaveFileAs,mnuExit;
    /**
     * JMenuItem section Edit (copier/couper/coller)
     */
    private JMenuItem mnuCopy,mnuCut,mnuPaste;
    /**
     * JMenuItem section Affichage (EditBar)
     */
    private JMenuItem mnuEditBar;
    /**
     * JMenuItem section Aide (readme/javadoc)
     */
    private JMenuItem mnuHelpBar,mnuHelp2Bar;
    /**
     * Le dessin
     */
    Dessin D;
    /**
     * La fenêtre
     */
    private Windows screen;
    /**
     * Map pour les raccourcis
     */
    private Map<String, Object> clipboard = new HashMap<>();

    /**
     * Création de la Barre menu.
     *
     * @param D      le dessin
     * @param screen la Windows
     */
    public BarreMenu(Dessin D, Windows screen) {
        if (!test) {
            test=true;
            CreateBar(D, screen);
        }
        return;
    }

    /**
     * Création de la barre menu
     * @param d le dessins
     * @param screen le panel Windows
     */
    private void CreateBar(Dessin d, Windows screen){
        this.D = d;
        this.screen = screen;
        //SECTION MENU FILE
        mnuFile = new JMenu( "Fichier" );
        mnuFile.setMnemonic( 'F' );
        //SOUS SECTION newFile
        mnuNewFile = new JMenuItem( "Nouveau Fichier" );
        mnuNewFile.setIcon( new ImageIcon( "Files/Icons/new.png" ) );
        mnuNewFile.setMnemonic( 'N' );
        mnuNewFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK) ); //CTRL + N
        mnuNewFile.addActionListener(this::mnuNewList);
        mnuFile.add(mnuNewFile);

        mnuFile.addSeparator();

        //SOUS SECTION OpenFile
        mnuOpenFile = new JMenuItem( "Ouvrir" );
        mnuOpenFile.setIcon( new ImageIcon( "Files/Icons/open.png" ) );
        mnuOpenFile.setMnemonic( 'O' );
        mnuOpenFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK) ); //CTRL + O
        mnuOpenFile.addActionListener(this::mnuOpenList);
        mnuFile.add(mnuOpenFile);

        //SOUS SECTION saveFile
        mnuSaveFile = new JMenuItem( "Enregistrer" );
        mnuSaveFile.setIcon( new ImageIcon( "Files/Icons/save.png" ) );
        mnuSaveFile.setMnemonic( 'S' );
        mnuSaveFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK) ); //CTRL + S
        mnuSaveFile.addActionListener(this::mnuSaveList);
        mnuFile.add(mnuSaveFile);

        mnuSaveFileAs = new JMenuItem( "Enregistrer-sous" );
        mnuSaveFileAs.setIcon( new ImageIcon( "Files/Icons/save_as.png" ) );
        mnuSaveFileAs.addActionListener(this::mnuSaveAsList);
        mnuSaveFileAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.SHIFT_DOWN_MASK | KeyEvent.CTRL_DOWN_MASK) ); //CTRL + shift + S
        mnuFile.add(mnuSaveFileAs);

        mnuFile.addSeparator();

        mnuExit = new JMenuItem( "Quitter" );
        mnuExit.setIcon( new ImageIcon( "Files/Icons/exit.png" ) );
        mnuExit.setMnemonic( 'x' );
        mnuExit.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK) );
        mnuExit.addActionListener(this::mnuExitList);
        mnuFile.add(mnuExit);

        add(mnuFile);

        // Définition du menu déroulant "Edit" et de son contenu
        mnuEdit = new JMenu( "Editer" );
        mnuEdit.setMnemonic( 'E' );

        mnuCopy = new JMenuItem("Copier");
        mnuCopy.setIcon(new ImageIcon( "Files/Icons/copy.png" ));
        mnuCopy.setMnemonic( 'C' );
        mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        mnuCopy.addActionListener(this::mnuCopyList);
        mnuEdit.add(mnuCopy);

        mnuCut = new JMenuItem("Couper");
        mnuCut.setIcon( new ImageIcon( "Files/Icons/cut.png" ) );
        mnuCut.setMnemonic('t');
        mnuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        mnuCut.addActionListener(this::mnuCutList);
        mnuEdit.add(mnuCut);

        mnuPaste = new JMenuItem("Coller");
        mnuPaste.setIcon( new ImageIcon( "Files/Icons/paste.png" ) );
        mnuPaste.setMnemonic( 'P' );
        mnuPaste.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK) );
        mnuPaste.addActionListener(this::mnuPasteList);
        mnuEdit.add(mnuPaste);

        add(mnuEdit);

        // Définition du menu déroulant "Edit" et de son contenu
        mnuAff = new JMenu("Affichage");
        mnuEditBar = new JMenuItem("EditBar");
        mnuEditBar.setIcon( new ImageIcon( "..." ) );
        mnuEditBar.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK) );
        mnuEditBar.addActionListener(this::mnuEditBarList);
        mnuAff.add(mnuEditBar);

        add(mnuAff);

        // Définition du menu déroulant "Help" et de son contenu
        mnuHelp = new JMenu( "Aide" );
        mnuHelpBar = new JMenuItem("Read-Me");
        mnuHelpBar.addActionListener(this::mnuHelpList);
        mnuHelpBar.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK) );
        mnuHelp2Bar = new JMenuItem("Javadoc");
        mnuHelp2Bar.addActionListener(this::mnuHelp2List);
        mnuHelp2Bar.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK) );
        mnuHelp.add(mnuHelpBar);
        mnuHelp.add(mnuHelp2Bar);
        add( mnuHelp );
    }

    /**
     * Ecouteur Nouveau fichier
     *
     * @param event the event
     */
    public void mnuNewList( ActionEvent event ) {
        D.setNewGraph();
        //On ajoute le dessin à la fenêtre
        //Windows.getInstance().addDessin(g);
    }

    /**
     * Ecouteur ouvrir fichier.
     *
     * @param event the event
     */
    public void mnuOpenList( ActionEvent event ) {
        JFileChooser chooser = new JFileChooser();
        int resultat = chooser.showOpenDialog(screen);
        if(resultat == JFileChooser.APPROVE_OPTION) {
            // Ouverture du fichier sélectionné avec Desktop
            File fichier = chooser.getSelectedFile();
            if(fichier.exists()) {
                try {
                    screen.d.setGraphe(screen.d.getGraphe().deserialize(fichier.getAbsolutePath()));
                    screen.d.repaint();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Ecouteur enregistrer
     *
     * @param event the event
     */
    public void mnuSaveList( ActionEvent event ) {
        // Appel de la méthode serialize avec le nom du fichier par défaut
        String filename = "save.ser";
        screen.d.getGraphe().serialize(screen.d.getGraphe(), filename);
    }

    /**
     * Ecouteur sauvegarder
     *
     * @param event the event
     */
    public void mnuSaveAsList(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(screen) == JFileChooser.APPROVE_OPTION) {
            // Appel de la méthode serialize avec le nom de fichier choisi
            String filename = fileChooser.getSelectedFile().getAbsolutePath();
            screen.d.getGraphe().serialize(screen.d.getGraphe(), filename);
        }
    }

    /**
     * Ecouteur quitter l'application
     *
     * @param event the event
     */
    public void mnuExitList(ActionEvent event) {
        System.exit(0);
    }
    /**
     * Ecouteur copie list.
     * @param event event
     */
    public void mnuCopyList(ActionEvent event) {
        // Copie l'élément sélectionné dans le presse-papiers
        Sommet element = this.D.getSelSom();
        if (element != null){
            clipboard.clear();
            clipboard.put("type",element.getClass().getName());
            clipboard.put("x", element.getX());
            clipboard.put("y", element.getY());
            clipboard.put("l", element.getLenght());
            clipboard.put("color", element.getCouleur());
            clipboard.put("colorSelect", element.getCouleurSelect());
        }
        JOptionPane.showMessageDialog(this,"Copie effectuer !");
    }

    /**
     * Ecouteur couper l'élément.
     *
     * @param event the event
     */
    public void mnuCutList(ActionEvent event) {
        mnuCopyList(event);
        this.D.getGraphe().delSommet(this.D.getSelSom());
        this.D.repaint();
    }

    /**
     * Ecouteur coller l'element.
     *
     * @param event the event
     */
    public void mnuPasteList(ActionEvent event) {
        // Colle l'élément du presse-papiers en demandant un nom
        String name = D.askName();
        //System.out.println(clipboard.get("type"));
        if (clipboard.get("type").equals("Graphe.Forme.Rond")){
            this.D.getGraphe().addSommet(new Rond(name,(int) clipboard.get("x")+50,(int)clipboard.get("y")+50,(int)clipboard.get("l"),(Color) clipboard.get("color"),(Color) clipboard.get("colorSelect")));
        } else if (clipboard.get("type").equals("Graphe.Forme.Carre")) {
            this.D.getGraphe().addSommet(new Carre(name,(int) clipboard.get("x")+50,(int)clipboard.get("y")+50,(int)clipboard.get("l"),(Color) clipboard.get("color"),(Color) clipboard.get("colorSelect")));
        }else if (clipboard.get("type").equals("Graphe.Forme.Triangle")) {
            this.D.getGraphe().addSommet(new Triangle(name,(int) clipboard.get("x")+50,(int)clipboard.get("y")+50,(int)clipboard.get("l"),(Color) clipboard.get("color"),(Color) clipboard.get("colorSelect")));
        }
        this.D.repaint();
    }

    /**
     * Ecouteur Afficher editBar.
     *
     * @param event the event
     */
    public void mnuEditBarList(ActionEvent event) {
        screen.infosBar.showEditBar();
    }

    /**
     * Ecouteur help pour ouvrir fichier read-me.
     *
     * @param event the event
     */
    public void mnuHelpList(ActionEvent event) {
        File file = new File("readme.txt");

        //Vérifier si le système prend en charge la classe Desktop ou non
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop n'est pas prise en charge");
            return;
        }

        Desktop d = Desktop.getDesktop();
        if(file.exists()) {
            try {
                d.open(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Ouverture du javadoc sur le navigateur
     * @param event ActionEvent
     */
    public void mnuHelp2List(ActionEvent event) {
        File file = new File("Files/javadoc/index.html");
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(file.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {    }
}

