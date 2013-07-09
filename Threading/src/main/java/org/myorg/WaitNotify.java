package org.myorg;

//App.java is the driver for this code..

//Some Notes: 
//-------------

//1. wait() and notify() are methods of the Object class.
//2. They need to be called from a synchronized block and both the synchronized block must obtain lock on the same object!!
//3. wait() relinquishes the lock on the object mentioned in the synchronized(); But notify() doesn't relinquish. The lock is relinquished when the synchronized block completes.
//4. When a dedicated lock is used, use the same lock on the synchronized blocks of both wait and notify calls and also wait and notify calls should be lock.wait() and lock.notify()
//5. If above condition is not satisfied, IllegalMonitorStateException is thrown.
//6. Both wait() and notify() are ways to communicate between threads and in order to communicate they are depending on the lock feature and queue feature of an Object. Basically, the lock object is the binding force between the communication. Both should be using the same lock object. Hence wait and notify have been put in the Object class.
//7. wait() and notify() both throws InterruptedException. Need to handle them by throwing it upwards or using try and catch block.

class Systems
{
    Object lock = new Object(); //Dedicated lock for producer to wait
    
	public void produce()
	{
		synchronized(lock)
		{
			System.out.println("Inside the Producer.");
		
		    //wait needs to be called from a synchronized block. The wait() relinquishes its lock and goes into block state until it is notified..
     		try {
				lock.wait();  // Waits on the dedicated lock object.
				//wait();    --> Wait for lock on the this objct (here Systems).
			} catch (InterruptedException e) {

				e.printStackTrace();
			} //Producer Thread waits until it receives notification to continue by the thread that has occupied the same lock as wait had relinquished.
	       System.out.println("Producer Resuming the task after obtaining the lock from the consumer..");	
		}
		
	}
	
	public void consume()
	{
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		synchronized(lock) //Notifies all threads waiting on the dedicated lock object..
		{
			System.out.println("Inside the Consumer.");

          //Perform some task of consuming..
			
			lock.notify(); //notifies the first thread that is waiting on the same object which is the producer thread here.. 
			//notify(); --> Notifies the threads waiting on the this object (here it is Systems)
			//notify does not relinquish the lock.. See below for example: 
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
      //The lock is relinquished here only after the synchronized block completes.
	}
		
	}

class WaitNotify {

	Systems s = new Systems();
	
	public void begin(){
	
	Thread producer = new Thread (new Runnable(){
		public void run() 
		{
			   s.produce();
		}
		
	});	

	 
	Thread consumer = new Thread (new Runnable(){
		public void run()
		{
			s.consume();
		}
	
		
	});
	
	producer.start();
	consumer.start();
	  }
}


