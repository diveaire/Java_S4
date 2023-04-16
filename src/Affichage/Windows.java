import java.awt.*;
import javax.swing.*;

public class Windows extends JFrame{
    private Dessin d;
	Windows(String name){
		super(name);
		this.d=new Dessin();
        this.add(d);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1980,1080);
		this.setVisible(true);
	}

	public static void main(String[] argv){
        Windows G = new Windows("Graphe");
	}
}