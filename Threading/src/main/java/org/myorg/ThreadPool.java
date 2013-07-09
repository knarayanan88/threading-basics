package org.myorg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processors implements Runnable
{
    int id;
	public Processors(int id){
		this.id = id;
	}
	
	public void run() {
       System.out.println("Starting Task : "+id + " by Thread : "+ Thread.currentThread().getName());
       
       try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {

		e.printStackTrace();
	}
       
       System.out.println("Completed Task: "+id + " by Thread : "+ Thread.currentThread().getName());
       
	}
	
}

public class ThreadPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long start  = System.currentTimeMillis();
		
		ExecutorService executor = Executors.newFixedThreadPool(2); //New Thread Manager -> ExecutorService.	
		
		for (int i=0; i<5; i++)
		{
			executor.submit(new Processors(i));
		}
		
		executor.shutdown(); //This will not immediately shutdown the ExecutorService. Will wait till all task are completed.
		
		System.out.println("All Tasks Submitted..");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS); //The Executor will wait for the jobs to complete till 1 day is complete.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All Tasks Completed...");
		
		System.out.println(System.currentTimeMillis() - start);
	}

}
