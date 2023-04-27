package ScreenGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfosBar extends JPanel{
    boolean aff;
    Dessin D;
    private JPanel haut;
    private JTextField nomField,lenField,thickField,nomArcField;
    private JButton appliquerButton;
    private BoutonCouleur colorButton,colorButtonSelect,arcC;
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

        /* initialisation des options */
        nomField = new JTextField();
        lenField = new JTextField();
        thickField = new JTextField();
        nomArcField = new JTextField();
        arcC = new BoutonCouleur("Sélectionner couleur sélection de l'arc");
        colorButton = new BoutonCouleur("Sélectionner couleur du sommet");
        colorButtonSelect = new BoutonCouleur("Sélectionner couleur sélection du sommet");
        appliquerButton = new JButton("Appliquer");
        /* reglage de l'affichage */
        this.update();
    }

    //met à jour la barre info en fonction du mouse event
    public void update(){
        //on enleve tous les éléments de haut
        this.haut.removeAll();
        //on enleve le haut
        this.remove(haut);

        if (D.getSelSom() != null && D.getPSelSom() != null ){//changement arrête
            arcC.setBackgroundColor(this.D.getSelArc().getCouleur());
            this.haut.setLayout(new GridLayout(10,2));
            this.haut.add(new JLabel("epaisseur : "+D.getSelArc().getThickness()));

            this.haut.add(thickField);
            this.haut.add(arcC);
            this.haut.add(appliquerButton);
        }else if (D.getSelSom() != null) {//changement sommet
            colorButton.setBackgroundColor(this.D.getSelSom().getCouleur());
            colorButtonSelect.setBackgroundColor(this.D.getSelSom().getCouleurSelect());
            this.haut.setLayout(new GridLayout(10,2));
            this.haut.add(new JLabel("Nom : "+ D.getSelSom().getNom()));
            this.haut.add(nomField);
            this.haut.add(new JLabel("Length : "+ String.valueOf(D.getSelSom().getLenght())));
            this.haut.add(lenField);

            this.haut.add(colorButton);
            this.haut.add(colorButtonSelect);

            appliquerButton = new JButton("Appliquer");
            appliquerButton.addActionListener(this::appliqueModif);
            this.haut.add(appliquerButton);
        }else{//rien de selectionné
            this.haut.setLayout(new GridLayout(1,1));
            this.haut.add(new JTextField("aucun sommets/arrêtes sélectionnées "));
        }
        this.add(haut);
    }

    //met à jour les modifications
    private void appliqueModif(ActionEvent event) {
        //Si le champ thickField n'est pas vide
        if (!this.thickField.getText().isEmpty()) {
            this.D.getSelArc().setThickness(Integer.parseInt(this.thickField.getText()));
            this.thickField.setText("");
        }
        //Si le champ nomField n'est pas vide
        if (!this.nomField.getText().isEmpty()) {
            this.D.getSelSom().setNom(this.nomField.getText());
            this.nomField.setText("");
        }//Si le champ lenField n'est pas vide
        if(!this.lenField.getText().isEmpty()) {
            this.D.getSelSom().setLenght(Integer.parseInt(this.lenField.getText()));
            this.lenField.setText("");
        }//Si la couleur element a été choisie
        if (colorButton.getColor() != null) {
            D.getSelSom().setCouleur(colorButton.getColor());
            D.getSelSom().setCouleurAff(colorButton.getColor());
        }//Si la couleur selection a été choisie
        if (colorButtonSelect.getColor() != null) {
            D.getSelSom().setCouleurSelect(colorButtonSelect.getColor());
            D.getSelSom().setCouleurAff(colorButtonSelect.getColor());
        }
        if (arcC.getColor() != null) {
            D.getSelArc().setCouleur(arcC.getColor());
            D.getSelArc().setCouleurAff(arcC.getColor());
        }
        //on met a jour les parametres de l'objet
        this.D.getWindow().updateBar();
        this.D.repaint();
    }

    //affiche ou masque la barre info
    public void showEditBar(){
        this.aff= !this.aff;
        this.setVisible(this.aff);
    }
}
