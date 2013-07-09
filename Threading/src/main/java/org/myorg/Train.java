package org.myorg;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Train {

	private static final int TOTALSEATS = 10;
	private int remainingSeats; 
	private final ReadWriteLock rwLock;
    private final Lock readLock;
    private final Lock writeLock;
	
	public Train()
	{
		this.remainingSeats = TOTALSEATS;
		rwLock = new ReentrantReadWriteLock(true);
		readLock = rwLock.readLock();
		writeLock = rwLock.writeLock();
	}
	
	public int getRemainingSeats() {
		readLock.lock();
		try
		{
			return remainingSeats;
		}
		finally
		{
			readLock.unlock();
		}
	}

	public boolean bookTickets(int bookedSeats)  {
		writeLock.lock();
		try 
		{
			if (remainingSeats >= bookedSeats)
			{
				this.remainingSeats = remainingSeats - bookedSeats;
				return true;
			}
			else
				return false;
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	public void cancelTickets(int numSeats)
	{
		writeLock.lock();
		this.remainingSeats = remainingSeats+numSeats;
		writeLock.unlock();
		
	}

}

