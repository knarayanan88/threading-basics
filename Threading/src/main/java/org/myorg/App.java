package org.myorg;

import java.util.Scanner;
import org.myorg.*;

class Processor extends Thread
{
	//volatile is to prevent caching by any thread and any change made by any other thread to this variable is visible to this thread.
	private volatile boolean runFlag = true; 

	@Override
	public void run() {
		
		while (runFlag) //runFlag can be cached by the thread as it is not being used in the below run code snippet.
		{
			System.out.println("Running");
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown()
	{
		runFlag = false;
	}
	
}


public class App {

	public static void main(String[] args) {
         
//		Processor proc = new Processor();
//		proc.start();
//		
//		new Scanner(System.in).nextLine();
//		
//		proc.shutdown();
		
//		Worker w = new Worker();
//		w.begin();

//		Worker2 w2 = new Worker2();
//		w2.begin();
		
//		WaitNotify w3 = new WaitNotify();
//		w3.begin();

//		PCProblem pc1 = new PCProblem();
//		pc1.begin();
		
//		Kickstart pc = new Kickstart();
//		pc.begin();
		
//		ReEntrantLock re = new ReEntrantLock();
//		re.begin();
		
//		Deadlock d = new Deadlock();
//      d.begin();
	
	}   
}
