package dynamic_beat_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

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
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../Assets/Images/GameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../Assets/Images/JudgementLine.png")).getImage();
	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../Assets/Images/NoteRoute.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../Assets/Images/NoteRouteLine.png")).getImage();
	private Image selectedImage;
	
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
	
	//Left button
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../Assets/Images/leftButtonEntered.png"));
	private JButton leftButton = new JButton(leftButtonBasicImage);
	
	//Right BUtton
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/rightButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../Assets/Images/rightButtonEntered.png"));
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	//Easy BUtton
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/easyButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../Assets/Images/easyButtonEntered.png"));
	private JButton easyButton = new JButton(easyButtonBasicImage);
	
	//Hard BUtton
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/hardButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../Assets/Images/hardButtonEntered.png"));
	private JButton hardButton = new JButton(hardButtonBasicImage);
	
	//Back BUtton
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../Assets/Images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../Assets/Images/backButtonEntered.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	//Mouse X, Y
	private int mouseX, mouseY;
	
	//is main?
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	//Music
	private Music titleMusic = new Music("TitleMusic.mp3", true);
	private Music selectedMusic;
	
	
	//Array of track
	private ArrayList<Track> trackList = new ArrayList<Track>();
	
	//Current selected song's idx
	private int nowSelected = 0; 
	
	//Difficulty
	private String selectedDifficulty = "Easy";
	
	//Scroe
	private int score = 0;
	
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
	
		//Load trakcs
		LoadTracks();
			
		//Title music Start
		titleMusic.start();
		
		//Create Buttons
		CreateButton();
		
		//Add trakcs
		
		
		
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
		
		screenDraw((Graphics2D)screenGraphic);
		
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics2D g)
	{
		//단순한 background image같은건 drawimage로
		g.drawImage(Background, 0, 0, null);
		
		if(isMainScreen)
		{
			g.drawImage(selectedImage, 340, 100, null);
		}
		else if(isGameScreen)
		{
			//Note Route
			g.drawImage(noteRouteLineImage, 224, 30, null);
			g.drawImage(noteRouteImage, 228, 30, null);
			g.drawImage(noteRouteLineImage, 328, 30, null);
			g.drawImage(noteRouteImage, 332, 30, null);
			g.drawImage(noteRouteLineImage, 432, 30, null);
			g.drawImage(noteRouteImage, 436, 30, null);
			g.drawImage(noteRouteLineImage, 536, 30, null);
			g.drawImage(noteRouteImage, 540, 30, null);
			g.drawImage(noteRouteImage, 640, 30, null);
			g.drawImage(noteRouteLineImage, 740, 30, null);
			g.drawImage(noteRouteImage, 744, 30, null);
			g.drawImage(noteRouteLineImage, 844, 30, null);
			g.drawImage(noteRouteImage, 848, 30, null);
			g.drawImage(noteRouteLineImage, 948, 30, null);
			g.drawImage(noteRouteImage, 952, 30, null);
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
			g.drawString(trackList.get(nowSelected).getGameMusic(), 20, 702);
			
			//Difficulty
			g.drawString(selectedDifficulty, 1190, 702);
			
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
			g.drawString(Integer.toString(score), 565, 702);
			
		}
		
		
		//JFrame에 추가된것들(Add를이용해서) Button, Menubar, etc... 그리기
		paintComponents(g);
		
		this.repaint();
	}
	
	
	private void CreateButton()
	{
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
				EnterMain();				
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
		
		//////////////////////////////////////////Left Button////////////////////////////////////////////////////////////
		//Add Exit button
		leftButton.setBounds(140, 310, 60, 60);
		//테두리에 선있는거 업애기
		leftButton.setBorderPainted(false);
		//사진 사각형으로 둘러싸는거 없애기
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.setVisible(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				selectLeft();
			}
		});
		
		add(leftButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////Right Button////////////////////////////////////////////////////////////
		//Add Exit button
		rightButton.setBounds(1080, 310, 60, 60);
		//테두리에 선있는거 업애기
		rightButton.setBorderPainted(false);
		//사진 사각형으로 둘러싸는거 없애기
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.setVisible(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				selectRight();
			}
		});
		add(rightButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////Easy Button////////////////////////////////////////////////////////////
		//Add Exit button
		easyButton.setBounds(375, 580, 250, 67);
		//테두리에 선있는거 업애기
		easyButton.setBorderPainted(false);
		//사진 사각형으로 둘러싸는거 없애기
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.setVisible(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				GameStart(nowSelected, "easy");
			}
		});
		add(easyButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////Hard Button////////////////////////////////////////////////////////////
		//Add Exit button
		hardButton.setBounds(655, 580, 250, 67);
		//테두리에 선있는거 업애기
		hardButton.setBorderPainted(false);
		//사진 사각형으로 둘러싸는거 없애기
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.setVisible(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				GameStart(nowSelected, "hard");
			}
		});
		add(hardButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		
		//////////////////////////////////////////Back Button////////////////////////////////////////////////////////////
		//Add Exit button
		backButton.setBounds(20, 50, 60, 60);
		//테두리에 선있는거 업애기
		backButton.setBorderPainted(false);
		//사진 사각형으로 둘러싸는거 없애기
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.setVisible(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e)
			{
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				BackMain();
			}
		});
		add(backButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
	}
	
	public void EnterMain()
	{
		isMainScreen = true;
		
		titleMusic.close();
		startButton.setVisible(false);
		quitButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		
		Background = new ImageIcon(Main.class.getResource("../Assets/Images/MainBackground.jpg")).getImage();
		
		selectTrack(0);	
	}
	
	public void selectTrack(int nowSelected)
	{
		if(selectedMusic != null)
		{
			selectedMusic.close();
		}
		
		selectedImage = new ImageIcon(Main.class.getResource("../Assets/Images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getGameMusic(), true);
		selectedMusic.start();
		
	}
	
	public void LoadTracks()
	{
		AddTrack("KonosubaStartImage.png", "KonosubaGameImage.png", "Konosuba_Tendon.mp3");
		AddTrack("DanshiStartImage.png", "DanshiGameImage.png", "Danshi_HighSchoolBoysandBoyhood.mp3");
		AddTrack("AbyssStartImage.png", "AbyssGameImage.png", "Abyss_DaysintheSun.mp3");
	}
	
	public void AddTrack(String startImage, String gameImage, String gameMusic)
	{
		trackList.add(new Track(startImage, gameImage, gameMusic));
	}
	
	public void selectLeft()
	{
		if(nowSelected == 0)
		{
			nowSelected = trackList.size() - 1;
		}
		else
		{
			nowSelected--;
		}
		
		selectTrack(nowSelected);
	}
	
	public void selectRight()
	{
		if(nowSelected == trackList.size() - 1)
		{
			nowSelected = 0;
		}
		else
		{
			nowSelected++;
		}
		
		selectTrack(nowSelected);
	}
	
	public void GameStart(int nowSelected, String difficulty)
	{
		if(selectedMusic != null)
		{
			selectedMusic.close();
		}
		
		isMainScreen = false;
		isGameScreen = true;
		
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(true);
		
		Background = new ImageIcon(Main.class.getResource("../Assets/Images/" + trackList.get(nowSelected).getGameImage())).getImage();
		
		selectedDifficulty = difficulty;
	}
	
	public void BackMain()
	{
		isMainScreen = true;
		isGameScreen = false;
		
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		backButton.setVisible(false);
		
		Background = new ImageIcon(Main.class.getResource("../Assets/Images/MainBackground.jpg")).getImage();
		
		selectTrack(nowSelected);
	}
}
