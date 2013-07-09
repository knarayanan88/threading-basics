package org.myorg;

/*class Runner extends Thread
{

	@Override
	public void run() {
       
		for (int i = 0; i<10; i++)
		{
			System.out.println("Hello :"+ i);
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	
} 
*/

class Runner implements Runnable
{

	public void run() {
		
		for (int i = 0; i<10; i++)
		{
			System.out.println("Hello :"+ i);
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
	
}


public class Application {

	public static void main(String[] args) {

//		Runner runner1 = new Runner();
//		runner1.start();
//	
//		Runner runner2 = new Runner();
//		runner2.start();
	
        Thread thread1 = new Thread(new Runnable() { 


        	public void run() {
        		
        		for (int i = 0; i<5; i++)
        		{
        			System.out.println("Hello :"+ i);
        		}
        		
        		        		
        		try {
        			Thread.sleep(100);
        		} catch (InterruptedException e) {

        			e.printStackTrace();
        		}

        	}

        });
        
         thread1.start();
 
        System.out.println("In Main");
	}
	
   
}
