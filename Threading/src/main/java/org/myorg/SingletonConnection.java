package org.myorg;

import java.util.concurrent.Semaphore;

public class SingletonConnection {

private static final SingletonConnection instance = new SingletonConnection();
	
private SingletonConnection(){
	
}

public static SingletonConnection getInstance()
{
	return instance;
}

Semaphore sem = new Semaphore(10); //At a time, 10 threads can connect.

private int connections = 0; //No of connections created..


public void connect()
{
	try {
		sem.acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	doConnect();
	
	sem.release();
}

public void doConnect()
{
	synchronized(this)
	{
		connections++;
		System.out.println("Current Connections: "+connections);
	}
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
//	synchronized(this)
//	{
//		connections--;
//	}
}

}
