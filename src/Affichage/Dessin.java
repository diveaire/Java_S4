import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
public class Dessin extends JPanel implements MouseListener{
    private Graphe g;
    private int size;
    private String type;
    private Sommet selSom,pselSom;
    private Arc selArc;
    public Dessin(){
        this.g=new Graphe();
        this.size=20;
        this.type="Rond";
        this.selSom=null;
        this.pselSom=null;
        this.selArc=null;
        addMouseListener(this);
        this.g.addSommet(new Rond("A",10,10,20));
        this.g.addSommet(new Carre("B",30,30,20));
        this.g.addSommet(new Triangle("C",50,50,20,Color.BLUE));
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
    public Sommet getSelSom(){
        return this.selSom;
    }
    public Sommet getPrevSelSom(){
        return this.pselSom;
    }
    public Arc getSelArc(){
        return this.selArc;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        this.g.paint(g);
    }
    public void mouseClicked(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            if(g.isSommetInList(new Rond("",e.getX(),e.getY(),this.size))){
                Sommet pointed=this.g.getSommet(new Rond("",e.getX(),e.getY(),this.size));
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
                    this.repaint();
                }
                else if(this.pselSom==null){
                    this.pselSom=pointed;
                    g.addArc(this.selSom,this.pselSom);
                    pointed.setCouleur(Color.GREEN);
                    this.repaint();
                }
                else if(this.pselSom.equals(pointed)){
                    this.pselSom=null;
                    pointed.setCouleur(Color.BLACK);
                    this.repaint();
                }
                else{
                    this.selSom.setCouleur(Color.BLACK);
                    this.selSom=this.pselSom;
                    this.pselSom=pointed;
                    g.addArc(this.selSom,this.pselSom);
                    pointed.setCouleur(Color.GREEN);
                    this.repaint();
                }
            }
            else{
                if(this.type=="Rond"){
                    this.g.addSommet(new Rond("",e.getX()-this.size/2,e.getY()-this.size/2,this.size));
                    this.repaint();
                }
                else if(this.type=="Carre"){
                    this.g.addSommet(new Carre("",e.getX()-this.size/2,e.getY()-this.size/2,this.size));
                    this.repaint();
                }
                else if(this.type=="Triangle"){
                    this.g.addSommet(new Triangle("",e.getX()-this.size/2,e.getY()-this.size/2,this.size));
                    this.repaint();
                }     
            }
        }
        else if((e.getButton()==MouseEvent.BUTTON3)&&(g.isSommetInList(new Rond("",e.getX(),e.getY(),this.size)))){
            this.g.delSommet(new Rond("",e.getX(),e.getY(),this.size));
            this.repaint();
        }
    }
    public void mouseEntered(MouseEvent e) {}  
    public void mouseExited(MouseEvent e) {}  
    public void mousePressed(MouseEvent e) {}  
    public void mouseReleased(MouseEvent e) {}  
}
