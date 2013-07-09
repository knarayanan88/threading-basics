package org.myorg;

import java.util.ArrayList;
import java.util.List;

//Written by me.. Has many problems .. Try to fix it.. The correct PC with low level synchronization is ProducerConsumer2.java

class ProducerConsumer1
{
	List<Integer> list = new ArrayList<Integer>(100);
	
	public void producer() throws InterruptedException 
	{
		System.out.println("Inside Producer..");
		synchronized (this)
		{
			int i=0;
			
			do
			{
				while (list.size() == 100)
				{
				    System.out.println("List size exceeded.. Producer waiting for Consumer to start removing elements..");
					wait();
				}
					System.out.println("Producer adding element: "+i);
				    list.add(i);
					Thread.sleep(500);
					
					if (list.size() == 1)
					{
						System.out.println("If atleast one element is available to the consumer, notify it to start consuming..");
						notify();
					}
					i++;
			} while(i<10000);
	    }

		
	}
		
	
	public void consumer() throws InterruptedException
	{
		synchronized(this)
		{

			do
			{
				System.out.println("Inside Consumer..");
				while (list.size() == 0)
				{
					System.out.println("List has no elements.. Consumer waiting to receive notification from Producer Thread");
					wait();
				}

			
		    System.out.println("Removing element : "+ list.get(0));			
			list.remove(0);
			
			if(list.size() == 99)
			{
				notify();
			}
			
			Thread.sleep(2000);
			}while (list.size() <= 100);
			
		}
	}
}


class PCProblem {

	ProducerConsumer1 pc1 = new ProducerConsumer1();
	
	public void begin()
	{
		Thread p = new Thread(new Runnable(){
			public void run()
			{
				try {
					Thread.sleep(3000);
					pc1.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread c = new Thread(new Runnable(){
			public void run()
			{
				try {
					Thread.sleep(3000);
					pc1.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		p.start();
		c.start();
	}
 
		
	}


