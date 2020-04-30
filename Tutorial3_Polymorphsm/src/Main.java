import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) 
	{
//		Fruit f = new Peach();
//		f.show();
//		
//		f = new Banana();
//		f.show();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Peach: 1, Banana:2");
		
		int select = scan.nextInt();
		
		Fruit f;
		if(select == 1)
		{
			f = new Peach();
			f.show();
		}
		else if(select == 2)
		{
			f = new Banana();
			f.show();
		}
		

	}

}
