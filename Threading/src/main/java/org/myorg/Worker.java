package org.myorg;

public class Worker {

 private int count = 0;
 
 public synchronized void increment() //synchronized keyword - the thread will acquire lock on Worker Object till it finishes this method and then releases the lock
 {
	 count++;
 }
 
 public void begin()
 {
	 Thread t1 = new Thread (new Runnable(){
		
		 public void run()
		 {
		 
			 for (int i =0; i<1000000; i++)
			 {
				 //count++; //this is not an atomic operation. First the caller will read count, increment it and write back to the variable.
				 increment();
			 }
		 }
	 });
	 
	 t1.start();
	 
	 Thread t2 = new Thread (new Runnable(){
			
		 public void run()
		 {
		 
			 for (int i =0; i<1000000; i++)
			 {
				 //count++;
				 increment();
			 }
		 }
	 });
	 
	 t2.start();
	 
	 try {
		t1.join();
		t2.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	 
	 System.out.println("Counter : " + count);
 }
		
		
}


