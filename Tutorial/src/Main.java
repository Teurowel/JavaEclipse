import java.util.Scanner;

public class Main extends Player
{

	public static void main(String[] args) 
	{
//		Student s1 = new Student("Me", 10, 10, 10, "11", 1, 1.2);
//		
//		s1.show();
//		
//		Teacher t1 = new Teacher("teacehr", 10, 10, 10, "12", 1, 1);
//		t1.show();
//		
//		
//		Student[] students = new Student[100];
//		for(int i = 0; i < 100; ++i)
//		{
//			students[i] = new Student("Me", 10, 10, 10, i + "", 1, 1.2);
//			students[i].show();
//		}
//		
//		
//		Scanner scan = new Scanner(System.in);
//		System.out.print("How many?: ");
//		int number = scan.nextInt();
//		
//		Student[] students2 = new Student[number];
//		for(int i = 0; i < number; ++i)
//		{
//			System.out.print("Name?: ");
//			String name = scan.next();
//			
//			System.out.print("age?: ");
//			int age = scan.nextInt();
//			
//			students2[i] = new Student(name, age, 10, 10, i + "", 1, 1.2);
//		}
//		
//		for(int i = 0; i < number; ++i)
//		{
//			students2[i].show();
//		}
		
		
		
		
//		Main main = new Main();
//		main.play("what");
//		main.pause();
//		main.stop();
		
		
	

	}

	@Override
	void play(String songName) 
	{
		// TODO Auto-generated method stub
		System.out.println("Play " + songName);
	}

	@Override
	void pause()
	{
		// TODO Auto-generated method stub
		System.out.println("pause");
	}

	@Override
	void stop() 
	{
		// TODO Auto-generated method stub
		System.out.println("stop");
	}

}
