package dynamic_beat_1;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame
{
	//For double buffering
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image TitleBackground;
	
	public DynamicBeat()
	{
		setTitle("Dynamic Beat");
		
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		setResizable(false);			//can't resize the screen
		
		setLocationRelativeTo(null);	//screen will be at the middle of screen
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When close screen, program will be terminated 
		
		setVisible(true);
		
		
		
		//Based on Main class source file location, load image and get image instance
		TitleBackground = new ImageIcon(Main.class.getResource("../Assets/Images/TitleBackground.jpg")).getImage();
		
		
	}
	
	
	//Override of JFrame's paint
	public void paint(Graphics g)
	{
		//Create back buffer
		if(screenImage == null)
		{
			screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			screenGraphic = screenImage.getGraphics();
		}
		
		screenDraw(screenGraphic);
		
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g)
	{
		g.drawImage(TitleBackground, 0, 0, null);
		this.repaint();
	}
}
