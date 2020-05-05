import java.util.Random;

public class ThreadTest extends Thread
{
	private int id = -1;
	
	public static int commonVar = 0;
	
	public ThreadTest(int id)
	{
		this.id = id;
	}
	
	@Override
	public void run()
	{
		System.out.println(id + "thread run");
		
		Random r = new Random(System.currentTimeMillis());
		
		try
		{
			//random number in 3000
			long s = r.nextInt(3000);
			
			//stop thread
			Thread.sleep(s);
			accessCommonVar();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println(id + "thread end");
	}
	
	public synchronized static void accessCommonVar()
	{
		commonVar++;
	}
}


