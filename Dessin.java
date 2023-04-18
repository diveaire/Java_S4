import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
public class Dessin extends JPanel implements MouseListener{
    private Graphe g;
    private int size;
    private String type;
    public Dessin(){
        this.g=new Graphe();
        this.size=20;
        this.type="Rond";
        addMouseListener(this);
        this.g.addSommet(new Rond("A",10,10,20));
        this.g.addSommet(new Carre("B",30,30,20));
        this.g.addSommet(new Triangle("C",50,50,20,Color.RED));
        this.g.addSommet(new Rond("D",430,430,20,Color.RED));
        this.g.addArc(new Rond("A",10,10,20),new Carre("B",30,30,20));
        this.g.addArc(new Carre("B",30,30,20),new Triangle("C",50,50,20,Color.RED));
        this.g.addArc(new Triangle("C",50,50,20,Color.RED),new Rond("D",430,430,20,Color.RED));
    }
    public Graphe getGraphe(){
        return this.g;
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
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        this.g.paint(g);
    }
    public void mouseClicked(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            if(g.isSommetInList(new Rond("",e.getX(),e.getY(),20))){
                Sommet x=this.g.getSommet(new Rond("",e.getX(),e.getY(),20));
                x.setCouleur(Color.GREEN);
                this.repaint();
            }
            else{
                if(this.type=="Rond"){
                    this.g.addSommet(new Rond("A",e.getX()-10,e.getY()-10,20));
                    this.repaint();
                }
                else if(this.type=="Carre"){
                    this.g.addSommet(new Carre("A",e.getX()-10,e.getY()-10,20));
                    this.repaint();
                }
                else if(this.type=="Triangle"){
                    this.g.addSommet(new Triangle("A",e.getX()-10,e.getY()-10,20));
                    this.repaint();
                }     
            }
        }
        else if((e.getButton()==MouseEvent.BUTTON3)&&(g.isSommetInList(new Rond("",e.getX(),e.getY(),20)))){
            this.g.delSommet(new Rond("A",e.getX(),e.getY(),20));
            this.repaint();
        }
    }
    public void mouseEntered(MouseEvent e) {}  
    public void mouseExited(MouseEvent e) {}  
    public void mousePressed(MouseEvent e) {}  
    public void mouseReleased(MouseEvent e) {}  
}