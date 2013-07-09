package org.myorg;

public interface ReadersWriterInterface {
	
	public void reader(String s) throws InterruptedException;
	public void writer(String s, Integer val) throws InterruptedException;
	public Integer getMapValue(ReadersWriterInterface r, String s);


}
