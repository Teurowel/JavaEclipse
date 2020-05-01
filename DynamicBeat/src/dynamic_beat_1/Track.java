package dynamic_beat_1;

//Represent One Song
public class Track 
{
	private String startImage;
	private String gameImage;
	private String gameMusic;
	
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	
	public Track(String startImage, String gameImage, String gameMusic) 
	{
		super();
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.gameMusic = gameMusic;
	}
	
}
