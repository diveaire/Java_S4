package ScreenGUI;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Infos bar.
 */
public class InfosBar extends JPanel{
    /**
     * The Aff.
     */
    boolean aff;
    /**
     * The D.
     */
    Dessin D;
    private JPanel haut, bas;
    private JTextField nomField,lenField,thickField,nomArcField;
    private JButton appliquerButton;
    private BoutonCouleur colorButton,colorButtonSelect;

    /**
     * Instantiates a new Infos bar.
     *
     * @param aff the aff
     * @param d   the d
     */
    public InfosBar(boolean aff, Dessin d){
        //parametre de la fenêtre
        this.setSize(150,1080);
        this.setVisible(aff);
        this.aff=aff;
        this.setBackground(Color.LIGHT_GRAY);
        this.D=d;
        this.setLayout(new GridLayout(2,1));

        this.haut = new JPanel();
        this.haut.setBackground(Color.GRAY);

        this.bas = new JPanel();
        this.bas.setBackground(Color.GRAY);


        /* initialisation des options */
        nomField = new JTextField();
        lenField = new JTextField();
        thickField = new JTextField();
        nomArcField = new JTextField();

        colorButton = new BoutonCouleur("Sélectionner couleur de l'élément",Color.BLACK);
        colorButtonSelect = new BoutonCouleur("Sélectionner couleur sélection de l'élément",Color.GREEN);

        appliquerButton = new JButton("Appliquer");
        appliquerButton.addActionListener(this::appliqueModif);
        /* reglage de l'affichage */
        this.update();
    }

    /**
     * Update.
     */
//met à jour la barre info en fonction du mouse event
    public void update(){
        //on enleve tous les éléments de haut
        this.haut.removeAll();
        //on enleve le haut
        this.remove(haut);
        this.colorButtonSelect.setBackgroundColor(this.colorButtonSelect.getColor());
        if (D.getSelSom() != null && D.getPSelSom() != null ){//changement arrête
            this.haut.setLayout(new GridLayout(10,2));
            this.haut.add(new JLabel("Arrête" ));
            this.haut.add(new JLabel("nom : " + D.getSelArc().getNom()));
            this.haut.add(nomArcField);

            this.haut.add(new JLabel("epaisseur : "+D.getSelArc().getThickness()));
            this.haut.add(thickField);

            this.colorButton.setBackgroundColor(this.D.getSelArc().getCouleur());
            this.haut.add(colorButton);
            this.colorButtonSelect.setBackgroundColor(this.D.getSelArc().getCouleurSelect());
            this.haut.add(colorButtonSelect);

            this.haut.add(appliquerButton);

        }else if (D.getSelSom() != null) {//changement sommet
            //colorButton.setBackgroundColor(this.D.getSelSom().getCouleur());
            //colorButtonSelect.setBackgroundColor(this.D.getSelSom().getCouleurSelect());
            this.haut.add(new JLabel("Sommet" ));
            this.haut.setLayout(new GridLayout(10,2));

            this.haut.add(new JLabel("Nom : "+ D.getSelSom().getNom()));
            this.haut.add(nomField);

            this.haut.add(new JLabel("Length : "+ String.valueOf(D.getSelSom().getLenght())));
            this.haut.add(lenField);

            this.colorButton.setBackgroundColor(this.D.getSelSom().getCouleur());
            this.haut.add(colorButton);
            this.colorButtonSelect.setBackgroundColor(this.D.getSelSom().getCouleurSelect());
            this.haut.add(colorButtonSelect);

            this.haut.add(appliquerButton);
        }else{//rien de selectionné
            this.haut.setLayout(new GridLayout(1,1));
            this.haut.add(new JTextField("aucun sommets/arrêtes sélectionnées "));
        }
        this.add(haut);
    }

    //met à jour les modifications
    private void appliqueModif(ActionEvent event) {

        /* CHANGEMENTS SUR ARC */
        if (D.getSelSom() != null && D.getPSelSom() != null ) {
            //Si le champ nomArcField n'est pas vide
            if (!this.nomArcField.getText().isEmpty()) {
                if (this.D.getGraphe().isAvailableName(this.nomArcField.getText())){
                    this.D.getSelArc().setNom(this.nomArcField.getText());
                }
                this.nomArcField.setText("");
            }
            //Si le champ thickField n'est pas vide
            if (!this.thickField.getText().isEmpty()) {
                if (Integer.parseInt(this.thickField.getText()) <= 10){
                    this.D.getSelArc().setThickness(Integer.parseInt(this.thickField.getText()));
                }
                this.thickField.setText("");
            }
            //Si la couleur element a été choisie
            if (colorButton.getColor() != null && colorButton.getColor() != Color.WHITE) {
                D.getSelArc().setCouleur(colorButton.getColor());
            }
            if (colorButtonSelect.getColor() != null && colorButton.getColor() != Color.WHITE) {
                D.getSelArc().setCouleurSelect(colorButtonSelect.getColor());
                D.getSelArc().setCouleurAff(colorButtonSelect.getColor());
            }


        }else {

            /* CHANGEMENT SUR SOMMET */

            //Si le champ nomField n'est pas vide
            if (!this.nomField.getText().isEmpty()) {
                if (this.D.getGraphe().isAvailableName(this.nomField.getText())) {
                    this.D.getSelSom().setNom(this.nomField.getText());
                }
                this.nomField.setText("");
            }//Si le champ lenField n'est pas vide
            if (!this.lenField.getText().isEmpty()) {
                if (Integer.parseInt(this.lenField.getText()) <= 50) {
                    this.D.getSelSom().setLenght(Integer.parseInt(this.lenField.getText()));
                }
                this.lenField.setText("");
            }//Si la couleur element a été choisie
            if (colorButton.getColor() != null && colorButton.getColor() != Color.WHITE) {
                D.getSelSom().setCouleur(colorButton.getColor());
            }//Si la couleur selection a été choisie
            if (colorButtonSelect.getColor() != null && colorButton.getColor() != Color.WHITE) {
                D.getSelSom().setCouleurSelect(colorButtonSelect.getColor());
                D.getSelSom().setCouleurAff(colorButtonSelect.getColor());
            }
        }
        //on met a jour les parametres de l'objet
        this.D.getWindow().updateBar();
        this.D.revalidate();
        this.D.repaint();
    }

    /**
     * Show edit bar.
     */
//affiche ou masque la barre info
    public void showEditBar(){
        this.aff= !this.aff;
        this.setVisible(this.aff);
    }
}
