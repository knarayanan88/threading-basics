package org.myorg;

/**
 * Testing Multiple Locks - Synchronized Block
 */

class Worker2 {
	
Object lock1 = new Object();
Object lock2 = new Object();


public void stageOne()
{
	synchronized(lock1)
	{
		sleep();
	}
}

public void stageTwo()
{
	synchronized(lock2)
	{
		sleep();
	}
}

public void sleep()
{
	try {
		Thread.sleep(5);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
}
	
public void process()
{
	for (int i=0; i<500; i++)
	{
		stageOne();
		stageTwo();
	}
}
	
public void begin()
{
	long start = System.currentTimeMillis();

	Thread t1 = new Thread (new Runnable(){
	public void run()
	{
		process();
	}
	
});	

	Thread t2 = new Thread (new Runnable(){
	public void run()
	{
		process();
	}
	
});

t1.start();
t2.start();

try {
	t1.join();
	t2.join();
} catch (InterruptedException e) {

	e.printStackTrace();
}

System.out.println("Time Taken : "+(System.currentTimeMillis() - start));


}
}
