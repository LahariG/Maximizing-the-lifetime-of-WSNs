
import java.awt.*;
import java.awt.event.*;

public class AwtImage extends Frame{
	
  Image img;
  public static void main(String[] args){
  AwtImage ai = new AwtImage();
  }
  
  public AwtImage(){
	  super("Image Frame");
	  
	  MediaTracker mt = new MediaTracker(this);
	  img = Toolkit.getDefaultToolkit().getImage("E:\\eclips_pjt\\workdir\\MxLifeTimeSenNodes\\bin\\architec.gif");
	  mt.addImage(img,0);
	  setSize(400,400);
	  setVisible(true);
	  
	  addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent we){
			  dispose();
		  }
	  });
  }
  public void update(Graphics g){
	  paint(g);
  }
  
  public void paint(Graphics g){
	  if(img != null)
		  g.drawImage(img, 100, 100, this);
	  else
	  {
		  g.clearRect(0, 0, getSize().width, getSize().height);
		  System.out.println("img not found:");
		  
	  }
  }
}
