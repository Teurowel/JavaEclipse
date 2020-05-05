import java.util.ArrayList;

public class Main 
{

	public static void main(String[] args) 
	{
		
/////////////////////////////////////extends Thread///////////////////////////////////
//		//Arrayt of all threads
//		ArrayList<Thread> threadList = new ArrayList<Thread>();
//		
//		System.out.println("main run");
//		
//		for(int i = 0; i < 10; ++i)
//		{
//			ThreadTest test = new ThreadTest(i);
//			
//			//this will call run funtion in thread
//			test.start();
//			threadList.add(test);
//		}
//		
//		for(int i = 0; i < threadList.size(); ++i)
//		{
//			try
//			{
//				//Wait until this thread ends...
//				threadList.get(i).join();
//			}
//			catch(InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println("commonvar: " + ThreadTest.commonVar);
//		System.out.println("main end");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
/////////////////////////////////////implements Runnable///////////////////////////////////
		Thread test = new Thread(new ThreadTest2());
		
		//Start can be called only once, if you want to start again, you need make a new one
		test.start();

	}

}
