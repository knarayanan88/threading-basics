package org.myorg;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {

	private int count = 0;
	private Lock relock = new ReentrantLock(); //This is an alternative to using synchronzied on the increment method..
	
	private Condition cond = relock.newCondition();// This is for wait/notify : cond.await() and cond.signal and cond.signalAll.
	
	public  void increment()
	{

			for (int i=0; i< 10000; i++)
			{
				count++;
			}

	}
	public void firstTask() throws InterruptedException
	{	
		relock.lock(); //Once a thread has got this lock, no other thread can call increment until the first thread unlocks it..
		
		System.out.println("Waiting..");
		
		cond.await(); 
		
		System.out.println("Woken up!");
		
		try
		{
			increment();
		}
		finally
		{
			relock.unlock(); //This will not be called if increment throws an exception...Hence put this in a try, finally block..
		}
	}
	
	public void secondTask() throws InterruptedException
	{
		Thread.sleep(100);
		relock.lock();
		
		System.out.println("Press the return Key.");
		new Scanner(System.in).nextLine();
		cond.signal();
		
		
		try
		{
			increment();
		}
		finally
		{
			relock.unlock(); //This will not be called if increment throws an exception...Hence put this in a try, finally block..
		}
	}
	
	public void finished()
	{
		System.out.println("Count is : "+ count);
	}
	
	public void begin() 
	{
		Thread t1 = new Thread(new Runnable(){
			
			public void run()
			{
				try {
					firstTask();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run()
			{
				try {
					secondTask();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		finished();
		
	}

}
