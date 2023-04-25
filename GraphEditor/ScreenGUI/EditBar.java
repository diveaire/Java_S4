package ScreenGUI;

import javax.swing.*;
import java.awt.*;
import Graphe.*;
public class EditBar extends JPanel{
    boolean aff;
    Dessin D;
    //information qui représente un graphe
    public EditBar(Dessin D){
        //elements de la fenêtre edit_bar
        this.setSize(700,1080);
        this.setVisible(false);
        aff=false;
        this.setBackground(Color.RED);
        this.D=D;
        this.add(new JLabel(""));
        this.add(new JLabel("AZEAZEAZEAZEAZEAZEAZE"));
    }

    public void showEditBar(){
        if (!this.aff){
            this.setVisible(true);
            this.aff=true;
        }else{
            this.setVisible(false);
            this.aff=false;
        }
    }

}
