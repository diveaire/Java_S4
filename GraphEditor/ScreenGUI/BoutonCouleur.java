package ScreenGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonCouleur extends JButton {
    private Color c;
    public BoutonCouleur(String nom){
        this.setText(nom);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Afficher le JColorChooser
                Color color = JColorChooser.showDialog(null, "Choisissez une couleur", Color.WHITE);
                // Utiliser la couleur sélectionnée
                if (color != null) {
                    c=color;
                }
            }
        });
    }

    public Color getColor(){
        return c;
    }
    public void setColor(){
        this.doClick();
    }
    public void setBackgroundColor(Color color){
        this.setBackground(color);
        this.setForeground(color);
    }
}
