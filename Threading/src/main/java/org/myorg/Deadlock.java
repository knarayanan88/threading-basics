package org.myorg;

public class Deadlock { //Deadlock occurring at times...

final Object lock1 = new Object ();
final Object lock2 = new Object();
private int counter = 0;

    //Theoratically deadlock should happen in the below way: 
	//Thread1 acquires lock1 first and starts executing the loop of j; before it completes, Thread2 acquires lock2 and then quickly 
	//waits for lock1 to get released by thread1. Thread 1 completes the j loop, until then thread 2 is waiting for lock1 (note that 
	//it already has the lock2 acquired first). Once Thread1 completes the j loop, it tries to acquire lock2 which is already acquired 
	//by Thread2. Hence Thread1 has lock1 and waiting for release of lock2 , while thread 2 has lock 2 and waiting for release of lock1.
	//This situation is called DEADLOCK. To resolve this simply synchronize the locks in the same order...

public void firstTask()
{
	
	synchronized(lock1)
	{
		for (int j=0; j<999999999; j++)
		{
			
		}
		
		synchronized(lock2)
		{
			for (counter=0; counter<1000000000; counter++)
			{
				if (counter%100 == 0)
				System.out.println(counter);
			}
			
		}
	}
}

public void secondTask()
{
	synchronized(lock2)
	{
		for (int j=0; j<100; j++)
		{
			
		}
		synchronized(lock1)
		{
			for (counter=0;counter<1000000000; counter++)
			{
				if (counter%100 == 0)
				System.out.println(counter);
			}
			
		}
	}
}

public void finishTask()
{
	System.out.println("Counter Value : "+ counter);
}

public void begin()
{
	Thread t1 = new Thread(new Runnable(){
		public void run()
		{
			firstTask();
		}
	});
	
	Thread t2 = new Thread(new Runnable(){
		public void run()
		{
			secondTask();
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
	
	
	System.out.println("Completed Main Thread..");
	
}
	
}
