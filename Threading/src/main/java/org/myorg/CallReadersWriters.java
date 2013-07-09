package org.myorg;

import java.util.Date;

class Readers implements Runnable {
	ReadersWriterInterface obj;

	public Readers(ReadersWriterInterface obj) {
		this.obj = obj;
	}

	public void run() {
		Date date = new Date();
		System.out.println("Started Reader Thread : "
				+ Thread.currentThread().getName() + " at " + date.toString());
		try {
			obj.reader("Flight-1");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Writers implements Runnable {
	ReadersWriterInterface obj;
	static int j = 100;

	public Writers(ReadersWriterInterface obj) {
		this.obj = obj;
	}

	public void run() {
		Date date = new Date();
		System.out.println("Started Writer Thread : "
				+ Thread.currentThread().getName() + " at " + date.toString());
		try {
			obj.writer("Flight-1", j++);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class CallReadersWriters {

	public static void main(String[] args) {

		final FirstReadersWriterSolution obj = new FirstReadersWriterSolution();
		// final SecondReadersWriterSolution obj = new
		// SecondReadersWriterSolution();
		// final ThirdReadersWriterSolution obj = new
		// ThirdReadersWriterSolution();
		// final ReentrantRWLock obj = new ReentrantRWLock();

		Thread[] users = new Thread[15];

		users[0] = new Thread(new Writers(obj));
		users[1] = new Thread(new Readers(obj));
		users[2] = new Thread(new Writers(obj));
		users[3] = new Thread(new Readers(obj));
		users[4] = new Thread(new Readers(obj));
		users[5] = new Thread(new Readers(obj));
		users[6] = new Thread(new Readers(obj));
		users[7] = new Thread(new Readers(obj));
		users[8] = new Thread(new Writers(obj));
		users[9] = new Thread(new Readers(obj));
		users[10] = new Thread(new Writers(obj));
		users[11] = new Thread(new Writers(obj));
		users[12] = new Thread(new Readers(obj));
		users[13] = new Thread(new Readers(obj));
		users[14] = new Thread(new Readers(obj));

		for (int i = 0; i < 15; i++) {
			users[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 15; i++) {
			try {
				users[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Completed...");
	}

//	public Integer getMapValue(ReadersWriterInterface r, String key) {
//		return r.getMapValue(r, key);
//	}

}
