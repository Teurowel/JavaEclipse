package dynamic_beat_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame
{
	//For double buffering
	private Image screenImage;
	private Graphics screenGraphic;
	
	//Based on Main class source file location, load image and get image instance
	private Image Background = new ImageIcon(Main.class.getResource("../Assets/Images/TitleBackground.jpg")).getImage();
	
	//Menu bar
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../Assets/Images/menuBar.png")));
	
	//ExitButton
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/ExitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../Assets/Images/ExitButtonEntered.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);
	
	//StartButton
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/StartButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../Assets/Images/StartButtonEntered.png"));
	private JButton startButton = new JButton(startButtonBasicImage);
	
	//QuitButton
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/QuitButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../Assets/Images/QuitButtonEntered.png"));
	private JButton quitButton = new JButton(quitButtonBasicImage);
	
	//Mouse X, Y
	private int mouseX, mouseY;
	public DynamicBeat()
	{
		
		////////////////////////////////////////Basic Settings//////////////////////////////////////////////
		//기본적인 메뉴바 안보이게
		setUndecorated(true);
		
		setTitle("Dynamic Beat");
		
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		setResizable(false);			//can't resize the screen
		
		setLocationRelativeTo(null);	//screen will be at the middle of screen
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When close screen, program will be terminated 
		
		setVisible(true);
		
		//paintComponents를 호출하면 background가 회색으로
		setBackground(new Color(0, 0, 0, 0));
		
		setLayout(null);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
//////////////////////////////////////// UI ////////////////////////////////////////////////////////////////////////////////
	
		//////////////////////////////////////////Exit Button////////////////////////////////////////////////////////////
		//Add Exit button
		exitButton.setBounds(1245, 0, 30, 30);
		//테두리에 선있는거 업애기
		exitButton.setBorderPainted(false);
		//사진 사각형으로 둘러싸는거 없애기
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				//Music buttonPressedSound = new Music("ButtonPressedSound.mp3", false);
				//buttonPressedSound.start();
				
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}
				
				System.exit(0);
			}
		});
		add(exitButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////Start Button////////////////////////////////////////////////////////////
		//Add Exit button
		startButton.setBounds(800, 450, 400, 100);
		//테두리에 선있는거 업애기
		startButton.setBorderPainted(false);
		//사진 사각형으로 둘러싸는거 없애기
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				startButton.setVisible(false);
				quitButton.setVisible(false);
				
				Background = new ImageIcon(Main.class.getResource("../Assets/Images/MainBackground.jpg")).getImage();
			}
		});
		add(startButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////Quit Button////////////////////////////////////////////////////////////
		//Add Exit button
		quitButton.setBounds(800, 570, 400, 100);
		//테두리에 선있는거 업애기
		quitButton.setBorderPainted(false);
		//사진 사각형으로 둘러싸는거 없애기
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				//Music buttonPressedSound = new Music("ButtonPressedSound.mp3", false);
				//buttonPressedSound.start();
				
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}
				
				System.exit(0);
			}
		});
		add(quitButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
		
		
////////////////////////////////////////////////////////////Menu Bar////////////////////////////////////////////////////////////
		//set menubar's position and size
		menuBar.setBounds(0, 0, 1280, 30);
		
		//메뉴바에 마우스 클릭좌표 얻어올수있게 추가
		menuBar.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e)
			{
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		//메뉴바에 마우스 드래그
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e)
			{
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		//Add menubar in JFrame
		add(menuBar);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
//////////////////////////////////////// Music ////////////////////////////////////////////////////////////////////////////////
		//Create title music
		Music titleMusic = new Music("TitleMusic.mp3", true);
		titleMusic.start();
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		//단순한 background image같은건 drawimage로
		g.drawImage(Background, 0, 0, null);
		
		//고정된 메뉴바나 움직이지않는 역동적이지않은 UI같은건 paintComponents를 이용해서 그려야한다
		paintComponents(g);
		
		this.repaint();
	}
}
