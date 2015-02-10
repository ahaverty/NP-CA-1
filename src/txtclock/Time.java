/**
 * Alan Haverty
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Model class for the object used to hold the time which primarily uses the
 * Calendar object
 * 
 * @author Alan Haverty
 */
public class Time {

	private Calendar time;

	/**
	 * Constructor used to setup a Time object with Hour=00, Minute=00 and
	 * Second=00
	 */
	public Time() {

		// Call the primary constructor for this class
		this(0, 0, 0);
	}

	/**
	 * Constructor used to setup a Time object with a custom Hour, Minute and
	 * Second=00
	 * 
	 * @param hour
	 * @param minute
	 */
	public Time(int hour, int minute) {

		// Call the primary constructor for this class
		this(hour, minute, 0);
	}

	/**
	 * Constructor used to setup a Time object with a custom Hour, Minute and
	 * Second
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public Time(int hour, int minute, int second) {

		// Create and initialise a Calendar object to store the time
		time = Calendar.getInstance();
		// Clear the calendar of time/date
		time.clear();

		// Call the main setter class to set this object's time variable
		setTime(hour, minute, second);
	}

	/**
	 * Converts the Time object to a human readable string in the format
	 * "HH:mm:ss"
	 * 
	 * @return timeAsString in the form "HH:mm:ss"
	 */
	@Override
	public String toString() {
		// Create a new blank string variable
		String timeAsString = new String();

		// Setup a formatter and use it to convert the Calendar object to the
		// String format 'HH:mm:ss'
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		timeAsString = timeFormatter.format(time.getTime());

		return timeAsString;
	}

	/**
	 * Setter method to set the Time object's Hour and Minute with a Second
	 * value of :00
	 * 
	 * @param hour
	 * @param minute
	 */
	public void setTime(int hour, int minute) {
		setTime(hour, minute, 0);
	}

	/**
	 * Setter method to set the Time object's Hour, Minute and Second
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public void setTime(int hour, int minute, int second) {
		time.set(Calendar.HOUR_OF_DAY, hour);
		time.set(Calendar.MINUTE, minute);
		time.set(Calendar.SECOND, second);
	}
	
	public int getHour() {
		return time.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinute() {
		return time.get(Calendar.MINUTE);
	}
	
	public int getSecond() {
		return time.get(Calendar.SECOND);
	}

}