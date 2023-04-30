package ScreenGUI;

// PACKAGES JAVA
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// PACKAGES LOCAL
import Graphe.*;
import Graphe.Forme.*;

public class Dessin extends JPanel implements MouseListener,MouseMotionListener {
    /* Attributs */
    private Graphe graphe;
    private int size,movedX,movedY; // Taille des objets créés, dernière position en X et Y de la souris
    private boolean orientedArc; // Booleen indiquant la création d'un arc ou d'une arête
    private String type; // Chaine indiquant la création d'un Rond, Carre ou Triangle
    private Sommet selSom,pselSom,movedSom; // Sommet selectionné, précédent sommet selectionné, sommet à bouger
    private Arc selArc; // Arc selectionné
    private Windows fenetre; // Fenetre à laquelle le dessin est lié
    /* Constructeurs */
    public Dessin(Windows W){
        this.graphe=new Graphe();
        this.size=20;
        this.type="Aucun";
        this.selSom=null;
        this.pselSom=null;
        this.movedSom=null;
        this.selArc=null;
        this.orientedArc=false;
        this.fenetre=W;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    /* Méthodes */
    public void setWindow(Windows win) {
        this.fenetre=win;
    }
    public Windows getWindow() {
        return this.fenetre ;
    }
    public Graphe getGraphe(){
        return this.graphe;
    }
    public void setGraphe(Graphe g){
        this.graphe=g;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String newType){
        this.type=newType;
    }
    public int getSizeLenght(){
        return this.size;
    }
    public void setSizeLenght(int x){
        this.size=x;
    }
    public Sommet getSelSom(){
        return this.selSom;
    }
    public Sommet getPSelSom(){
        return this.pselSom;
    }
    public void setSelSom(Sommet s){
        /* Si le sommet selectionné est changé il faut modifier les couleurs de selection */
        if(this.selSom!=null){
            this.selSom.setCouleurAff(this.selSom.getCouleur());
        }
        if(s!=null){
            s.setCouleurAff(s.getCouleurSelect());
            this.setPrevSelSom(this.selSom);
        }
        this.selSom=s;
    }
    public Sommet getPrevSelSom(){
        return this.pselSom;
    }
    public void setPrevSelSom(Sommet s){
        /* Si le sommet selectionné n'a pas de valeur alors on n'affecte rien à la valeur de "sélection précédente" */
        if(this.selSom!=null){
            /* Si le sommet selectionné est changé il faut modifier les couleurs de selection */
            if(this.pselSom!=null){
                this.pselSom.setCouleurAff(this.pselSom.getCouleur());
            }
            if(s!=null){
                s.setCouleurAff(s.getCouleurSelect());
            }
            this.pselSom=s;
        }
        else{
            this.setSelSom(s);
        }
    }
    public Sommet getMovedSom(){
        return this.movedSom;
    }
    public Arc getSelArc(){
        return this.selArc;
    }
    public void setSelArc(){
        if(this.selSom!=null && this.pselSom!=null){
            this.selArc=this.graphe.getArc(new Arete(this.selSom,this.pselSom));
        }
    }
    public void delSelArc(){
        if(this.selArc!=null){
            if(this.graphe.isArcInList(this.selArc)){
                this.graphe.delArc(this.selArc);
                this.repaint();
                this.selArc=null;
            }
        }
    }
    public boolean getOrientedArc(){
        return this.orientedArc;
    }
    public void setOrientedArc(boolean r){
        this.orientedArc=r;
    }
    /* Méthode permettant de récupérer un nom valable */
    public String askName(){
        String x= JOptionPane.showInputDialog(this, "Nommez le nouvel élément :");
        if(x==null){
            /* Si aucune chaine n'a été sélectionnée, on affecte le nom ERROR qui indique la non création d'un objet */
            x="ERROR";
        }
        else{
            /* Le seul nom pouvant être affecté plusieurs fois est un sommet sans nom */
            while(!x.equals("") && !this.graphe.isAvailableName(x)){
                x=JOptionPane.showInputDialog(this, "Nom déjà attribué ! Nommez le nouvel élément :");
                if(x==null){
                    x="";
                }
            }
        }
        return x;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        this.graphe.paint(g);
        if(this.movedSom!=null){
            /* Si on déplace un sommet, il faut mettre à jour l'affichage en fonction de movedX et movedY */
            g.setColor(Color.LIGHT_GRAY);
            Graphics2D g2 = (Graphics2D) g;
            this.movedSom.setX(this.movedX-this.movedSom.getLenght()/2);
            this.movedSom.setY(this.movedY-this.movedSom.getLenght()/2);
            this.movedSom.paint(g);
        }
    }
    public void mouseClicked(MouseEvent e){
        this.movedSom=null;
        /* Si on effectue un clique droit */
        if(e.getButton()==MouseEvent.BUTTON1){
            /* Si on a cliqué sur un sommet*/
            if(this.graphe.isSommetInList(new Rond("",e.getX(),e.getY(),this.size))){
                Sommet pointed=this.graphe.getSommet(new Rond("",e.getX(),e.getY(),this.size));
                /* Si aucun sommet n'est sélectionné */
                if(this.selSom==null){
                    this.selSom=pointed;
                    pointed.setCouleurAff(pointed.getCouleurSelect());
                    this.repaint();
                }
                /* Si on clique sur un sommet déjà sélectionné */
                else if(this.selSom.equals(pointed)){
                    if(this.pselSom!=null){
                        this.selSom=this.pselSom;
                        this.pselSom=null;
                    }
                    else{
                        this.selSom=null;
                    }
                    pointed.setCouleurAff(pointed.getCouleur());
                    if (this.selArc!=null){
                        this.selArc.setCouleurAff(this.selArc.getCouleur());
                        this.selArc=null;
                    }
                    this.repaint();
                }
                /* Si on clique sur un autre sommet et que l'on a déjà sélectionné un sommet */
                else if(this.pselSom==null){
                    this.pselSom=pointed;
                    if(this.orientedArc){
                        this.graphe.addArc(new AreteOriente(this.selSom,this.pselSom));
                    }
                    else{
                        this.graphe.addArc(new Arete(this.selSom,this.pselSom));
                    }
                    pointed.setCouleurAff(pointed.getCouleurSelect());
                    this.setSelArc();
                    this.selArc.setCouleurAff(this.selArc.getCouleurSelect());
                    this.repaint();
                }
                /* Si on clique sur le deuxième sommet déjà sélectionné */
                else if(this.pselSom.equals(pointed)){
                    this.pselSom=null;
                    pointed.setCouleurAff(pointed.getCouleur());
                    if (this.selArc!=null){
                        this.selArc.setCouleurAff(this.selArc.getCouleur());
                        this.selArc=null;
                    }
                    this.repaint();
                }
                /* Sinon on clique sur un autre sommet alors que deux sommets sont déjà sélectionné, on interverti les différents sommets */
                else{
                    this.selSom.setCouleurAff(pointed.getCouleur());
                    this.selSom=this.pselSom;
                    this.pselSom=pointed;
                    if(this.orientedArc){
                        this.graphe.addArc(new AreteOriente(this.selSom,this.pselSom));
                    }
                    else{
                        this.graphe.addArc(new Arete(this.selSom,this.pselSom));
                    }
                    pointed.setCouleurAff(pointed.getCouleurSelect());

                    if (this.selArc!=null){
                        this.selArc.setCouleurAff(this.selArc.getCouleur());
                        this.selArc=null;
                    }
                    this.repaint();
                }
                /* On vérifie si deux sommets on été sélectionné ce qui indique que s'il existe un arc entre les deux, on le sélectionne */
                if (this.selSom!=null && this.pselSom!=null && this.graphe.isArcInList(new Arete(this.selSom,this.pselSom))){
                    this.setSelArc();
                    this.selArc.setCouleurAff(this.selArc.getCouleurSelect());
                    this.repaint();
                }
            }
            /* Si on clique sur un espace non occupé */
            else{
                if(this.type!="Aucun"){
                    String n=this.askName();
                    if(n!="ERROR"){
                        if(this.type=="Rond"){
                            this.graphe.addSommet(new Rond(n,e.getX()-this.size/2,e.getY()-this.size/2,this.size));
                            this.repaint();
                        }
                        else if(this.type=="Carre"){
                            this.graphe.addSommet(new Carre(n,e.getX()-this.size/2,e.getY()-this.size/2,this.size));
                            this.repaint();
                        }
                        else if(this.type=="Triangle"){
                            this.graphe.addSommet(new Triangle(n,e.getX()-this.size/2,e.getY()-this.size/2,this.size));
                            this.repaint();
                        }     
                    }
                    
                }
                /* Chacun des éléments est déselectionné si aucun type n'est sélectionné */
                else{
                    if(this.selSom!=null){
                        this.selSom.setCouleurAff(this.selSom.getCouleur());
                        if(this.pselSom!=null){
                            this.pselSom.setCouleurAff(this.pselSom.getCouleur());
                            if(this.selArc!=null){
                                this.selArc.setCouleurAff(this.selArc.getCouleur());
                            }
                        }
                        this.repaint();
                        this.selSom=null;
                        this.pselSom=null;
                        this.selArc=null;
                    }
                }
            }
        }
        /* Si on effectue un clique gauche sur un sommet, on supprime le sommet */
        else if((e.getButton()==MouseEvent.BUTTON3)&&(graphe.isSommetInList(new Rond("",e.getX(),e.getY(),this.size)))){
            Sommet pointed=this.graphe.getSommet(new Rond("",e.getX(),e.getY(),this.size));
            if(this.selSom!=null && this.selSom.equals(pointed)){
                if(this.pselSom!=null){//désélection du 2ème sommet
                    this.selSom=this.pselSom;
                    this.pselSom=null;
                }
                else{ //désélection d'un sommet
                    this.selSom=null;
                }
                if (this.selArc!=null){
                    this.selArc.setCouleurAff(this.selArc.getCouleur());
                    this.selArc=null;
                }
            }
            else if(this.pselSom!=null && this.pselSom.equals(pointed)){
                this.pselSom=null;
                if (this.selArc!=null){
                    this.selArc.setCouleurAff(this.selArc.getCouleur());
                    this.selArc=null;
                }
            }// supprimer un sommet du graphe
            this.graphe.delSommet(pointed);
            this.repaint();
        }
        fenetre.updateBar();
    }
    public void mousePressed(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            /* Si on clique sur un sommet en maintenant le bouton enfoncé, on le déplace */
            if(this.graphe.isSommetInList(new Rond("",e.getX(),e.getY(),this.size))){
                if(this.movedSom==null){
                    this.movedSom=this.graphe.getSommet(new Rond("",e.getX(),e.getY(),this.size));
                }
                else if(this.movedSom!=null){
                    this.movedSom=null;
                }
            }
        }
    }  
    public void mouseReleased(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            if(this.movedSom!=null){
                this.movedSom=null;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        if(this.movedSom!=null){
            this.movedX= e.getX();
            this.movedY= e.getY();
            this.repaint();
        }
    }

    public void setNewGraph(){
        this.graphe=new Graphe();
        System.gc();
        this.repaint();
    }
    public void mouseEntered(MouseEvent e) {}  
    public void mouseExited(MouseEvent e) {}  
}
