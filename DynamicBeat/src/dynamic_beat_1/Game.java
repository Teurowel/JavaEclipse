package dynamic_beat_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread
{
	/////////////////////////////////////Images/////////////////////////////////////////////
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../Assets/Images/GameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../Assets/Images/JudgementLine.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../Assets/Images/NoteRouteLine.png")).getImage();
	
	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../Assets/Images/NoteRoute.png")).getImage();
	private Image noteRoutePressedImage = new ImageIcon(Main.class.getResource("../Assets/Images/NoteRoutePressed.png")).getImage();
	
	private Image noteRouteSImage = noteRouteImage;
	private Image noteRouteDImage = noteRouteImage;
	private Image noteRouteFImage = noteRouteImage;
	private Image noteRouteSpace1Image = noteRouteImage;
	private Image noteRouteSpace2Image = noteRouteImage;
	private Image noteRouteJImage = noteRouteImage;
	private Image noteRouteKImage = noteRouteImage;
	private Image noteRouteLImage = noteRouteImage;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Song name, difficulty
	private String songName;
	private String difficulty;
	
	//Game Music
	private Music gameMusic;
	
	//Array of Notes
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	//Array of beats
	Beat[] beats = null;
	
	public Game(String songName, String difficulty)
	{
		this.songName = songName;
		this.difficulty = difficulty;
		
		gameMusic = new Music(this.songName, false);		
		createNotes();
		gameMusic.start();
		
	}
	
	public void ScreenDraw(Graphics2D g)
	{
		//Note Route
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		
		//Game Info Image
		g.drawImage(gameInfoImage, 0, 660, null);
		
		//Note Judgement Line Image
		g.drawImage(judgementLineImage, 0, 580, null);
		
		//Draw Informations
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		
		//Song's name
		g.drawString(songName, 20, 702);
		
		//Difficulty
		g.drawString(difficulty, 1190, 702);
		
		//KeyBoard
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		
		//Score
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("Score", 565, 702);
		
		//Notes
		for(int i = 0; i < noteList.size(); ++i) 
		{
			Note note = noteList.get(i);
			note.screenDraw(g);
		}
	}
	
	@Override
	public void run()
	{
		dropNotes();
	}
	
	public void pressS()
	{
		noteRouteSImage = noteRoutePressedImage;
	
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseS()
	{
		noteRouteSImage = noteRouteImage;
	}
	
	public void pressD()
	{
		noteRouteDImage = noteRoutePressedImage;
		
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseD()
	{
		noteRouteDImage = noteRouteImage;
	}
	
	public void pressF()
	{
		noteRouteFImage = noteRoutePressedImage;
		
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseF()
	{
		noteRouteFImage = noteRouteImage;
	}
	
	public void pressSpace()
	{
		noteRouteSpace1Image = noteRoutePressedImage;
		noteRouteSpace2Image = noteRoutePressedImage;
		
		new Music("drumBig1.mp3", false).start();
	}
	
	public void releaseSpace()
	{
		noteRouteSpace1Image = noteRouteImage;
		noteRouteSpace2Image = noteRouteImage;
	}
	
	public void pressJ()
	{
		noteRouteJImage = noteRoutePressedImage;
		
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseJ()
	{
		noteRouteJImage = noteRouteImage;
	}
	
	public void pressK()
	{
		noteRouteKImage = noteRoutePressedImage;

		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseK()
	{
		noteRouteKImage = noteRouteImage;
	}
	
	public void pressL()
	{
		noteRouteLImage = noteRoutePressedImage;

		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseL()
	{
		noteRouteLImage = noteRouteImage;
	}
	
	public void close()
	{
		this.interrupt();
		
		gameMusic.close();
	}
	
	public void createNotes()
	{
		if(songName.equals("Konosuba_Tendon.mp3"))
		{
			int startTime = 1000 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] {
				new Beat(startTime, "S"),
				new Beat(startTime + gap * 10, "D"),
				new Beat(startTime + gap * 20, "F"),
				new Beat(startTime + gap * 30, "J"),
			};
		}
		
	}
	
	public void dropNotes()
	{
		
		int i = 0;
		while(!isInterrupted())
		{
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime())
			{
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				dropped = true;
				
				i++;
				if(i == beats.length)
				{
					break;
				}
			}
			
			//If it does not drop notes, sleep the thread
			if(dropped == false)
			{
				try
				{
					Thread.sleep(5);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
