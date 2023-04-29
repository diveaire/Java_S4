package ScreenGUI;

// PACKAGES JAVA
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// PACKAGES LOCAL
import Graphe.*;
import Graphe.Forme.*;
public class Dessin extends JPanel implements MouseListener,MouseMotionListener {
    private Graphe graphe;
    private int size,movedX,movedY;
    private boolean orientedArc;
    private String type;
    private Sommet selSom,pselSom,movedSom;
    private Arc selArc;
    private Windows fenetre;
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
        if(this.selSom!=null){
            this.selSom.setCouleurAff(this.selSom.getCouleur());
        }
        if(s!=null){
            s.setCouleurAff(s.getCouleurSelect());
        }
        this.selSom=s;
    }
    public Sommet getPrevSelSom(){
        return this.pselSom;
    }
    public void setPrevSelSom(Sommet s){
        if(this.selSom!=null){
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
        this.selArc=this.graphe.getArc(new Arete(this.selSom,this.pselSom));
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
    public String askName(){
        String x= JOptionPane.showInputDialog(this, "Nommez le nouvel élément :");
        if(x==null){
            x="ERROR";
        }
        else{
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
            g.setColor(Color.LIGHT_GRAY);
            Graphics2D g2 = (Graphics2D) g;
            this.movedSom.setX(this.movedX-this.movedSom.getLenght()/2);
            this.movedSom.setY(this.movedY-this.movedSom.getLenght()/2);
            this.movedSom.paint(g);
        }
    }
    public void mouseClicked(MouseEvent e){
        this.movedSom=null;
        if(e.getButton()==MouseEvent.BUTTON1){
            if(this.graphe.isSommetInList(new Rond("",e.getX(),e.getY(),this.size))){
                Sommet pointed=this.graphe.getSommet(new Rond("",e.getX(),e.getY(),this.size));
                if(this.selSom==null){
                    this.selSom=pointed;
                    pointed.setCouleurAff(pointed.getCouleurSelect());
                    this.repaint();
                }
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
                else if(this.pselSom==null){
                    this.pselSom=pointed;
                    if(this.orientedArc){
                        this.graphe.addArc(new AreteOriente(this.selSom,this.pselSom));
                    }
                    else{
                        this.graphe.addArc(new Arete(this.selSom,this.pselSom));
                    }
                    pointed.setCouleurAff(pointed.getCouleurSelect());
                    this.repaint();
                }
                else if(this.pselSom.equals(pointed)){
                    this.pselSom=null;
                    pointed.setCouleurAff(pointed.getCouleur());
                    if (this.selArc!=null){
                        this.selArc.setCouleur(this.selArc.getCouleur());
                        this.selArc=null;
                    }
                    this.repaint();
                }
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
                        this.selArc.setCouleur(this.selArc.getCouleur());
                        this.selArc=null;
                    }
                    this.repaint();
                }
                //arc ou sommet
                if (this.selSom!=null && this.pselSom!=null && this.graphe.isArcInList(new Arete(this.selSom,this.pselSom))){
                    this.setSelArc();
                    this.selArc.setCouleur(this.selArc.getCouleurSelect());
                    this.repaint();
                }
            }
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
                else{
                    if(this.selSom!=null){
                        this.selSom.setCouleurAff(this.selSom.getCouleur());
                        if(this.pselSom!=null){
                            this.pselSom.setCouleurAff(this.selSom.getCouleur());
                            if(this.selArc!=null){
                                this.selArc.setCouleurAff(this.selSom.getCouleur());
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
        else if((e.getButton()==MouseEvent.BUTTON3)&&(graphe.isSommetInList(new Rond("",e.getX(),e.getY(),this.size)))){
            Sommet pointed=this.graphe.getSommet(new Rond("",e.getX(),e.getY(),this.size));
            if(this.selSom!=null && this.selSom.equals(pointed)){
                if(this.pselSom!=null){
                    this.selSom=this.pselSom;
                    this.pselSom=null;
                }
                else{
                    this.selSom=null;
                }
                if (this.selArc!=null){
                    this.selArc.setCouleur(Color.BLACK);
                    this.selArc=null;
                }
            }
            else if(this.pselSom!=null && this.pselSom.equals(pointed)){
                this.pselSom=null;
                if (this.selArc!=null){
                    this.selArc.setCouleur(Color.BLACK);
                    this.selArc=null;
                }
            }
            this.graphe.delSommet(pointed);
            this.repaint();
        }
        fenetre.updateBar();
    }
    public void mousePressed(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
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
