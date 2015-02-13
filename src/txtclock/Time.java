/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock; // The package this class belongs to

import java.text.SimpleDateFormat; // Import to allow the class use of the formatter for formatting Calendar objects values into custom String values
import java.util.Calendar; // Allow the class to use the Calendar class which is the holder for the classes time behind the getter methods

/**
 * Custom Model class that stores the time for the project. Model primarily uses
 * the Calendar object with setters and getter for the hour, minute and second.
 * Using a custom Time model class to avoid using long Calendar object calls
 * throughout the rest of the project
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

		// Call the primary constructor for this class passing arguments from
		// this constructor
		this(hour, minute, 0);
	}

	/**
	 * Primary Constructor used to setup a Time object with a custom Hour,
	 * Minute and Second
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
		// Trigger the time formatter on the instances time
		timeAsString = timeFormatter.format(time.getTime());

		// Return the time as a human readable string
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
		// Call to the primary Model Constructor setting the seconds to 0 when
		// no seconds were provided
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
		// Set the hour, using the Calendar's 24hr HOUR_OF_DAY field
		time.set(Calendar.HOUR_OF_DAY, hour);
		// Set the hour, using the Calendar's Minute field
		time.set(Calendar.MINUTE, minute);
		// Set the hour, using the Calendar's Second field
		time.set(Calendar.SECOND, second);
	}

	/**
	 * Setter method to set the time object to the current systems time
	 */
	public void setToCurrentSystemTime() {
		// Set the time to the current system time using the Calendar method
		time = Calendar.getInstance();
	}

	/**
	 * Getter method to retrieve the integer value set for the instances hour
	 * 
	 * @return the hour
	 */
	public int getHour() {
		// Get the Calendar objects hour in 24hr format (0-23)
		return time.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Getter method to retrieve the integer value set for the instances minute
	 * 
	 * @return the minute
	 */
	public int getMinute() {
		// Get the Calendar objects minute (0-59)
		return time.get(Calendar.MINUTE);
	}

	/**
	 * Getter method to retrieve the integer value set for the instances second
	 * 
	 * @return the second
	 */
	public int getSecond() {
		// Get the Calendar objects second (0-59)
		return time.get(Calendar.SECOND);
	}

} // End Time class