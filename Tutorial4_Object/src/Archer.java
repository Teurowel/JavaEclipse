
public class Archer 
{
	private String name;
	private String power;
	
	public Archer(String name, String power)
	{
		this.name = name;
		this.power = power;
	}
	
	public boolean equals(Object obj)
	{
		Archer temp = (Archer)obj;
		
		System.out.println("Equals?");
		
		if(name == temp.name && power == temp.power)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
