package org.myorg;

import java.util.LinkedList;

/*
 * producer => wait , notify
 * consumer => wait, notify
 * 
 */

class ProducerConsumer2 {

private LinkedList<Integer> list = new LinkedList<Integer>();
private final int LIMIT = 10;
Object lock = new Object();

public void producer() throws InterruptedException
{
	int value = 0;
	while(true)
	{
		synchronized(lock)
		{
			while(list.size() == LIMIT)
			{
				System.out.println("Producer waiting as list size is :"+LIMIT);
				lock.wait();
			}
			System.out.println("Adding value: "+ value);
			list.add(value++);
			Thread.sleep(500);
			lock.notify();
		}
	} 
}

public void consumer() throws InterruptedException
{
	while(true)
	{
	  synchronized(lock)
	  {
		while(list.size() == 0)
		{
			System.out.println("Consumer waiting as list size is 0");
			lock.wait();
		}
		int value = list.removeFirst(); 
		System.out.println("Removing value: "+ value);
		lock.notify();
	  }
	}
}

}

class Kickstart
{
	ProducerConsumer2 pc2 = new ProducerConsumer2();
	public void begin()
	{
	
	Thread pr = new Thread ( new Runnable(){
		public void run()
		{
			try {
				pc2.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	});
	
	Thread co = new Thread ( new Runnable(){
		public void run()
		{
			try {
				pc2.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	});
	
	pr.start();
	co.start();
	}	
}