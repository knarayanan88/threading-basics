package org.myorg;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablesFutures {

//Callable interface helps to return a value/exception from the running thread. The Future object  collects the output and can display it from the calling thread..
	public static void main(String[] args) {
	
		ExecutorService executor = Executors.newCachedThreadPool(); //This takes thread as and when needed to the pool..
		
		Future<Integer> future = executor.submit(new Callable<Integer>(){ //anonymous thread task that returns an Integer from the task. 

			public Integer call() throws Exception {
				
				Random random = new Random();
				System.out.println("Starting..");
				
				int duration = random.nextInt(5000);
				Thread.sleep(duration);
				
				System.out.println("Finished..");
				
				return duration;
			}
						
		});
		
		executor.shutdown(); //This is needed to stop the managerial thread.. 
		
	     //Getting the value from Future object - future.get() throws 2 exceptions as below..
		//future.get() will get blocked till the executor shuts down.. Hence no need of executor.awaitTermination call...
		
		try {
			System.out.println("Value returned from the callable thread 1 : "+ future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  //Callables can also throw exception	from the call().. The future.get() will throw the ExecutionException

		ExecutorService executor2 = Executors.newCachedThreadPool(); //This takes thread as and when needed to the pool..
		Future<Integer> future2 = executor2.submit(new Callable<Integer>(){ //anonymous thread task that return an exception from the task. 

			public Integer call() throws Exception {
				
				Random random = new Random();
				System.out.println("Starting..");
				
				
				int duration = random.nextInt(5000);
				
				if (duration > 2000)
				{
					throw new IOException("Sleeping for too long");
				}
				Thread.sleep(duration);
				
				System.out.println("Finished..");
				
				return duration;
			}
						
		});
		
		executor2.shutdown();

		try {
			System.out.println("Value returned from the callable thread 1 : "+ future2.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println(e.getMessage());
			
		}

	
	}

}
