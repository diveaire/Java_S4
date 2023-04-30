package ScreenGUI;

import Graphe.Forme.Arc;
import Graphe.Forme.Element;
import Graphe.Forme.Sommet;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * InfosBar est un panel permettant l'affichage et la modifications des éléments Graphe du Dessin
 */
public class InfosBar extends JPanel{
    /**
     * Booléen permettant de savoir si la fenêtre doit être affichée ou masquée.
     */
    boolean aff;
    /**
     * Le Dessin des éléments à affichés dans l'info bar
     */
    Dessin D;
    /**
     * Les deux sections de l'infosBar : haut=modification éléments bas=liste éléments
     */
    private JPanel haut, bas;
    /**
     * Champs de texte pour la BarreInfos
     */
    private JTextField nomField,lenField,thickField,nomArcField;
    /**
     * Bouton de validation des modifications
     */
    private JButton appliquerButton;
    /**
     * Les boutons pour choisir une couleur
     */
    private BoutonCouleur colorButton,colorButtonSelect;
    /**
     * Model pour la liste
     */
    private DefaultListModel<Element> model;
    /**
     * Liste des éléments du dessin
     */
    private JList<Element> list;
    /**
     * Box pour l'affichage
     */
    private Box b1;
    /**
     * Création de l'InfosBar
     *
     * @param aff Paramètre Booléen qui définis si la fenêtre est affichée ou non par default
     * @param d   Dessin référent de l'InfosBar
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
        this.b1=new Box(BoxLayout.Y_AXIS);

        /* initialisation des options */
        nomField = new JTextField();
        lenField = new JTextField();
        thickField = new JTextField();
        nomArcField = new JTextField();

        colorButton = new BoutonCouleur("Sélectionner couleur de l'élément",Color.BLACK);
        colorButtonSelect = new BoutonCouleur("Sélectionner couleur sélection de l'élément",Color.GREEN);

        appliquerButton = new JButton("Appliquer");
        appliquerButton.addActionListener(this::appliqueModif);

        // Créer un modèle pour la liste
        model = new DefaultListModel<>();
        // Créer la JList à partir du modèle
        list = new JList<>(model);
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        });

        /* reglage de l'affichage */
        this.update();
    }
    /**
     * Met à jour la barre info en fonction du mouse event
     */
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



        this.b1.removeAll();
        this.model.removeAllElements();

        this.b1.add(new JLabel("Liste des composants"));
        for (Sommet s : this.D.getGraphe().getSommetList()){
            model.addElement(s);
        }
        for(Arc a : this.D.getGraphe().getArcList()){
            model.addElement(a);
        }
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedObject = list.getSelectedValue();
                if (selectedObject instanceof Sommet) {
                    this.D.resetSelSom();
                    this.D.setSelSom((Sommet) selectedObject);
                    this.D.getSelSom().setCouleurAff(this.D.getSelSom().getCouleurSelect());
                    this.D.getWindow().updateBar();
                    this.D.revalidate();
                    this.D.repaint();
                } else if (selectedObject instanceof Arc) {
                    this.D.resetSelSom();
                    this.D.setSelSom(((Arc)selectedObject).getS1());
                    this.D.setSelSom(((Arc) selectedObject).getS2());
                    this.D.setSelArc();

                    this.D.getSelSom().setCouleurAff(this.D.getSelSom().getCouleurSelect());
                    this.D.getPSelSom().setCouleurAff(this.D.getPSelSom().getCouleurSelect());
                    this.D.getSelArc().setCouleurAff(this.D.getSelArc().getCouleurSelect());

                    this.D.getWindow().updateBar();
                    this.D.revalidate();
                    this.D.repaint();
                }

            }
        });
        JScrollPane scrollPane = new JScrollPane(list);
        b1.add(scrollPane);
        this.bas.add(b1);
        this.add(bas);
    }

    /**
     * Met à jour les modifications
     */
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
     * Affiche ou masque la Barre d'infos
     */
    public void showEditBar(){
        this.aff= !this.aff;
        this.setVisible(this.aff);
    }
}
