package dynamic_beat_1;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread
{
	//Note Image
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/NoteBasic.png")).getImage();
	
	//Note Position
	private int x = 0;
	private int y = 580 - (1000 / Main.NOTE_DROPDELAY * Main.NOTE_DROPSPEED) * Main.REACH_TIME;
	
	//Note Type
	private String noteType;
	
	public Note(String noteType)
	{
		this.noteType = noteType;
		
		if(noteType.equals("S"))
		{
			x = 228;
		}
		else if(noteType.equals("D"))
		{
			x = 332;
		}
		else if(noteType.equals("F"))
		{
			x = 436;
		}
		else if(noteType.equals("Space"))
		{
			x = 540;
		}
		else if(noteType.equals("J"))
		{
			x = 744;
		}
		else if(noteType.equals("K"))
		{
			x = 848;
		}
		else if(noteType.equals("L"))
		{
			x = 952;
		}
		
	}
	
	public void screenDraw(Graphics2D g)
	{
		if(noteType.equals("Space"))
		{
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
		else
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		
	}
	
	public void drop()
	{
		y += Main.NOTE_DROPSPEED;
	}
	
	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				drop();
				Thread.sleep(Main.NOTE_DROPDELAY);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
