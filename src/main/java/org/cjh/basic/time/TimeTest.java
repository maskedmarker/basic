package org.cjh.basic.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class TimeTest {

	@Test
	public void calendar() {
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTimeInMillis()); // the current time as UTC milliseconds from the epoch
		
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss:SSS");
		String str = format.format(c.getTime());
		System.out.println(str);
		
	}
	
	@Test
	public void time() {
		Calendar c = Calendar.getInstance();
		
		// the current time as UTC milliseconds from the epoch
		System.out.println(c.getTimeInMillis()); 
		System.out.println(c.getTime().getTime());
	}
}
