
public class Main 
{

	public static void main(String[] args)
	{
		Archer a1 = new Archer("Elf", "High");
		Archer a2 = new Archer("Elf", "High");
		
		System.out.println(a1 == a2);
		System.out.println(a1.equals( a2));

	}

}
