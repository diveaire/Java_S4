import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
public class Dessin extends JPanel implements MouseListener{
    private Graphe graphe;
    private int size;
    private String type;
    private Sommet selSom,pselSom,movedSom;
    private Arc selArc;
    public Dessin(){
        this.graphe=new Graphe();
        this.size=20;
        this.type="Aucun";
        this.selSom=null;
        this.pselSom=null;
        this.movedSom=null;
        this.selArc=null;
        addMouseListener(this);
    }
    public Graphe getGraphe(){
        return this.graphe;
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
    public void setSelSom(Sommet s){
        this.selSom=s;
    }
    public Sommet getPrevSelSom(){
        return this.pselSom;
    }
    public void setPrevSelSom(Sommet s){
        this.pselSom=s;
    }
    public Sommet getMovedSom(){
        return this.movedSom;
    }
    public Arc getSelArc(){
        return this.selArc;
    }
    public void setSelArc(){
        this.selArc=this.graphe.getArc(new Arc(this.selSom,this.pselSom));
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
    }
    public void mouseClicked(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            if(this.graphe.isSommetInList(new Rond("",e.getX(),e.getY(),this.size))){
                Sommet pointed=this.graphe.getSommet(new Rond("",e.getX(),e.getY(),this.size));
                if(this.selSom==null){
                    this.selSom=pointed;
                    pointed.setCouleur(Color.GREEN);
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
                    pointed.setCouleur(Color.BLACK);
                    if (this.selArc!=null){
                        this.selArc.setCouleur(Color.BLACK);
                        this.selArc=null;
                    }
                    this.movedSom=null;
                    this.repaint();
                }
                else if(this.pselSom==null){
                    this.pselSom=pointed;
                    this.graphe.addArc(this.selSom,this.pselSom);
                    pointed.setCouleur(Color.GREEN);
                    this.repaint();
                }
                else if(this.pselSom.equals(pointed)){
                    this.pselSom=null;
                    pointed.setCouleur(Color.BLACK);
                    if (this.selArc!=null){
                        this.selArc.setCouleur(Color.BLACK);
                        this.selArc=null;
                    }
                    this.movedSom=null;
                    this.repaint();
                }
                else{
                    this.selSom.setCouleur(Color.BLACK);
                    this.selSom=this.pselSom;
                    this.pselSom=pointed;
                    this.graphe.addArc(this.selSom,this.pselSom);
                    pointed.setCouleur(Color.GREEN);
                    if (this.selArc!=null){
                        this.selArc.setCouleur(Color.BLACK);
                        this.selArc=null;
                    }
                    this.repaint();
                }
                if (this.selSom!=null && this.pselSom!=null && this.graphe.isArcInList(new Arc(this.selSom,this.pselSom))){
                    this.setSelArc();
                    this.selArc.setCouleur(Color.GREEN);
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
                        this.selSom.setCouleur(Color.BLACK);
                        if(this.pselSom!=null){
                            this.pselSom.setCouleur(Color.BLACK);
                            if(this.selArc!=null){
                                this.selArc.setCouleur(Color.BLACK);
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
    }
    public void mousePressed(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            if(this.graphe.isSommetInList(new Rond("",e.getX(),e.getY(),this.size))){
                if(this.movedSom==null && this.pselSom==null){
                    this.movedSom=this.graphe.getSommet(new Rond("",e.getX(),e.getY(),this.size));
                }
            }
        }
    }  
    public void mouseReleased(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            if(!this.graphe.isSommetInList(new Rond("",e.getX(),e.getY(),this.size))){
                if(this.movedSom!=null && this.pselSom==null){
                    this.movedSom.setX(e.getX()-this.movedSom.getLenght()/2);
                    this.movedSom.setY(e.getY()-this.movedSom.getLenght()/2);
                    this.movedSom=null;
                    this.repaint();
                }
            }
        }
    }  
    public void mouseEntered(MouseEvent e) {}  
    public void mouseExited(MouseEvent e) {}  
}