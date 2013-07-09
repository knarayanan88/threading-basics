package org.myorg;

import java.util.Date;


class SeatStatus implements Runnable
{
	private Train t = new Train();
	
	public SeatStatus(Train tr)
	{
		this.t = tr;
	}
	public void run()
	{
		System.out.println("Started Reader Thread : "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date = new Date();
		System.out.println("Thread "+Thread.currentThread().getName()+": Remaining Seats at "+date.getTime()+" :"+t.getRemainingSeats());
	}
}

class SeatReserve implements Runnable
{
	private Train t = new Train();
	private int numOfSeatsToBook; 
	
	public SeatReserve(Train tr, int numOfSeatsToBook)
	{
		this.t = tr;
		this.numOfSeatsToBook = numOfSeatsToBook;
	}
	
	public void run()
	{
		
		System.out.println("Started Writer-Reserve Thread : "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (t.bookTickets(numOfSeatsToBook))
		{
			System.out.println("Thread "+Thread.currentThread().getName()+": Booked "+numOfSeatsToBook+" seats..Remaining Seats: "+t.getRemainingSeats());
		}
		else
		{
			System.out.println("Thread "+Thread.currentThread().getName()+ " No Seats Available to Book..");
		}
	}
}

class SeatCancel implements Runnable
{
	private Train tr = new Train();
	private int numOfSeatsToCancel;
	
	public SeatCancel(Train tr, int numOfSeatsToCancel)
	{
		this.tr = tr;
		this.numOfSeatsToCancel = numOfSeatsToCancel;
	}
	
	public void run()
	{
		System.out.println("Started Writer-Cancel Thread : "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   tr.cancelTickets(numOfSeatsToCancel);
	   
	   System.out.println("Thread :"+ Thread.currentThread().getName()+" Cancelled 1 Ticket. Remaining Seats: "+tr.getRemainingSeats());
	}

	
}

public class TrainBooking {

	public static void main(String[] args) {

		Train t = new Train();
		
		Thread [] users = new Thread[17];
		
		users[0] = new Thread(new SeatStatus(t));
		users[1] = new Thread(new SeatReserve(t, 2));
		users[2] = new Thread(new SeatReserve(t, 3));
		users[3] = new Thread(new SeatStatus(t));
		users[4] = new Thread(new SeatStatus(t));
		users[5] = new Thread(new SeatCancel(t, 1));
		users[6] = new Thread(new SeatStatus(t));
		users[7] = new Thread(new SeatStatus(t));
		users[8] = new Thread(new SeatReserve(t, 1));
		users[9] = new Thread(new SeatReserve(t, 2));
		users[10] = new Thread(new SeatStatus(t));
		users[11] = new Thread(new SeatStatus(t));
		users[12] = new Thread(new SeatCancel(t, 1));
		users[13] = new Thread(new SeatReserve(t, 4));
		users[14] = new Thread(new SeatStatus(t));
		users[15] = new Thread(new SeatReserve(t, 1));
		users[16] = new Thread(new SeatStatus(t));
		
		for (int i=0; i< 17; i++)
		{
			users[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

