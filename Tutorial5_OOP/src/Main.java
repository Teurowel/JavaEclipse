
public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Hero[] heros = new Hero[3];
		
		heros[0] = new Warrior("Warrior");
		heros[0].count = 3;
		heros[1] = new Archer("Archer");
		heros[2] = new Wizard("Wizard");
		
		for(int i = 0; i < heros.length; ++i)
		{
			heros[i].attack();
			System.out.println(heros[i].count);
			
			if(heros[i] instanceof Warrior)
			{
				Warrior temp = (Warrior)heros[i];
				temp.groundCutting();
				System.out.println(temp.count);
			}
			if(heros[i] instanceof Archer)
			{
				Archer temp = (Archer)heros[i];
				temp.fireArrow();
				System.out.println(temp.count);
			}
			if(heros[i] instanceof Wizard)
			{
				Wizard temp = (Wizard)heros[i];
				temp.freezing();
				System.out.println(temp.count);
			}
		}

	}

}
