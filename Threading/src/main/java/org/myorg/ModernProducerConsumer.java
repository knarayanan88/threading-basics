package org.myorg;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ModernProducerConsumer {
	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10); 
	//Best Datastructure which is intrinsically threadsafe and also takes care of low level synchronization features like wait and notify.
	
	public static void main(String a[]) 
	{
		Thread p = new Thread (new Runnable(){
			public void run()
			{
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread c = new Thread (new Runnable(){
			public void run()
			{
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		p.start();
		c.start();
		
		try {
			p.join(); //main waits till p thread is complete
			c.join(); //main waits till c thread is complete
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void producer() throws InterruptedException
	{
		Random rnd = new Random();
		
		System.out.println("In Producer");
		
		while(true)
		{
			int value = rnd.nextInt(100); 
			queue.put(value); //Automatically waits if queue size is full.
			System.out.println("Value Added :"+ value);
		}
	}

	public static void consumer() throws InterruptedException
	{
		System.out.println("In Consumer");
		
		Random rnd = new Random();
		
		while(true)
		{
			Thread.sleep(100);
			
			if (rnd.nextInt(10) == 0 ){
			int value = queue.take(); //Automatically waits if there are no elements in the queue.
			System.out.print("Value Taken :"+ value);
			
			System.out.println("Size of the Queue: "+  queue.size());
			
			}
			
		}
		
		
	}

}



