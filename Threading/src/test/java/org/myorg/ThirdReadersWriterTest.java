package org.myorg;

import junit.framework.TestCase;

import org.junit.Test;

public class ThirdReadersWriterTest extends TestCase {

	CallReadersWriters c = new CallReadersWriters();
	ThirdReadersWriterSolution t3 = new ThirdReadersWriterSolution();
	
   @Test
   public void testExecution() 
   {
	
		Thread [] users = new Thread[15];


		users[0] =  new Thread (new Writers(t3));
		users[1] =  new Thread (new Readers(t3));
		users[2]  = new Thread (new Writers(t3));
		users[3] = new Thread (new Readers(t3));
		users[4] = new Thread (new Readers(t3));
		users[5] = new Thread (new Readers(t3));
		users[6] = new Thread (new Readers(t3));
		users[7] = new Thread (new Readers(t3));
		users[8]  = new Thread (new Writers(t3));
	    users[9] = new Thread (new Readers(t3));
	    users[10] = new Thread (new Writers(t3));
	    users[11] = new Thread (new Writers(t3));
	    users[12] = new Thread (new Readers(t3));
	    users[13] = new Thread (new Readers(t3));
	    users[14] = new Thread (new Readers(t3));
		
	    for (int i=0; i< 15; i++)
	    {
	    	users[i].start();
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    for (int i=0; i< 15; i++)
	    {
	    	try {
				users[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
}
