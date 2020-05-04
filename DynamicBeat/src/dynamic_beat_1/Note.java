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
	private int y = 580 - 1000 / Main.NOTE_DROPDELAY * Main.NOTE_DROPSPEED;
	
	//Note Type
	private String noteType;
	
	public Note(int x, String noteType)
	{
		this.x = x;
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g)
	{
		if(noteType.equals("short"))
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		else if(noteType.equals("long"))
		{
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
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
