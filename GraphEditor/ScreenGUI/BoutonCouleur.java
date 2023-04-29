package ScreenGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonCouleur extends JButton {
    private Color c;
    public BoutonCouleur(String nom, Color c){
        this.setText(nom);
        this.c=c;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Afficher le JColorChooser
                Color color = JColorChooser.showDialog(null, "Choisissez une couleur", Color.BLACK);
                // Utiliser la couleur sélectionnée
                if (color != null) {
                    setColor(color);
                }
            }
        });
    }

    public Color getColor(){
        return c;
    }
    public void setColor(Color c){
        this.c=c;
    }
    public void Activate(){
        this.doClick();
    }
    public void setBackgroundColor(Color color){
        this.setBackground(color);
        this.setForeground(color);
    }
}
