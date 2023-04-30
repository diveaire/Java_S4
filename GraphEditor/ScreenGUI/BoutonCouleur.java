package ScreenGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Objet permettant de créer un bouton qui va permettre de choisir une couleur
 */
public class BoutonCouleur extends JButton {
    /**
     * Couleur du bouton
     */
    private Color c;

    /**
     * Création du Bouton couleur.
     *
     * @param nom le nom
     * @param c   la couleur par défaut
     */
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

    /**
     * Récupère la couleur choisie
     *
     * @return Color
     */
    public Color getColor(){
        return c;
    }

    /**
     * Définir la couleur choisie par une méthode
     *
     * @param c Color
     */
    public void setColor(Color c){
        this.c=c;
    }

    /**
     * Activation du bouton par une méthode
     */
    public void Activate(){
        this.doClick();
    }

    /**
     * Méthode pour changer les couleurs des boutons
     *
     * @param color La couleur
     */
    public void setBackgroundColor(Color color){
        this.setBackground(color);
        this.setForeground(color);
    }
}
