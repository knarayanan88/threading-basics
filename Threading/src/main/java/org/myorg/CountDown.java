package org.myorg;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class StudentTest implements Runnable
{
	private int code, studentId;
	private CountDownLatch latch; //latch is a synchronized counter that helps in decrementing its value to 0 inorder to take a particular action
	
	public StudentTest (int examCode, int studentId, CountDownLatch latch)
	{
		this.code = examCode;
		this.studentId = studentId;
		this.latch = latch;
	}
	
	public void run()
	{
		//Student has to answer the questions in the exam.
		System.out.println("Student: "+studentId+" is writing Test with Exam Code: "+code);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Student :"+studentId+" has completed the Test with Exam Code : "+code);
		latch.countDown();
	}
}


public class CountDown {

	public static void main(String[] args) {
		
		int examCode; 
		
		ExecutorService executor = Executors.newFixedThreadPool(5); //5 Student Threads assigned the task of writing the Exam.
		
		//This latch is atomic and is decremented by each Student once they finish the exam.
		CountDownLatch latch = new CountDownLatch(5); //5 students have to return the test paper until then the invigilator will wait.
		
		
		for (int i=0; i<5; i++)
		{
			if (i%2 == 0)
			{
				examCode = 1000;
			}
			else
			{
				examCode = 1001;
			}
				executor.submit(new StudentTest(examCode, i, latch));
		}
		
		executor.shutdown();
		
		System.out.println("Students Started Writing the exam");
		
		try {
			latch.await(); //the latch awaits till all the 5 student threads have completed their tests and the latch value becomes 0
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All 5 Students have returned the test papers to the invigilator and now invigilator can stop and go.");

	}

}
