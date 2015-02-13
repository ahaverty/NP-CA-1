/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock; // The package this class belongs to

import java.io.IOException; // Import allowing class to handle Input/Output exceptions
import java.net.InetAddress; // Import to allow class to convert a domain name to an ip address
import java.util.Calendar; // Calendar class used to allow the handling of time objects

// Two imports from NTP apache commons net .jar for making the NTP client connection and TimeInfo object for holding the time result from the server
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

// --- End apache commons imports --- //

/**
 * Class that makes NTP request to NTP pool for current time using the apache
 * commons net jar found in lib/
 * 
 * @author Alan Haverty
 *
 */
public class NetworkTimeController {

	/**
	 * Requests the current time from a ntp pool, timeout is set to 10 seconds,
	 * function returns a Time object
	 * 
	 * @param ntpServer
	 * @return a Time object with the current time taken from the ntp server
	 */
	public static Time getTimeFromServer(String ntpServer) {

		// Initialise a new NTP client
		NTPUDPClient client = new NTPUDPClient();

		// Set the NTP client with a timout of 10 seconds
		client.setDefaultTimeout(10000);

		// Initialise a TimeInfo object for holding the NTP result
		TimeInfo info = null;

		// Initialise a new Time object to store the time from the TimeInfo
		// object
		Time time = new Time();

		// Boolean to track if the request timed out
		boolean timedOut = false;

		// Wrapping the NTP request in a try catch
		// Attempt to make NTP request
		try {
			// Open the NTP connection
			client.open();

			// Set the ntp server address taken from function argument
			InetAddress hostAddr = InetAddress.getByName(ntpServer);

			// Print the server details

			// Make the request by requesting the time from the server
			info = client.getTime(hostAddr);

			// Close the NTP connection
			client.close();

		} catch (IOException e) {
			// Catch code for catching when the server times out
			// TODO return to allow main program to request system time or just
			// check if the Time object is empty?
			System.out.println("The NTP request timed out.");

			// Set the Boolean to track if the request timed out to True if it
			// made it in here
			timedOut = true;

		} // Ending try catch

		// Initialise a calendar object
		Calendar cal = Calendar.getInstance();

		// If the NTP request did not time out, set the time object to the NTP
		// time
		if (timedOut == false) {

			// Set the calendar object using the NTP packet result's
			// milliseconds since
			// epoch
			long ntpEpochMillis = info.getMessage().getTransmitTimeStamp().getTime();

			// Set the Calendar object to the time retrieved from the NTP packet
			// by setting the Calendar using the epoch millis
			cal.setTimeInMillis(ntpEpochMillis);

			// Set a time object with the hour, minutes and seconds taken from
			// the calendar object by passing into the Time's constructor
			time.setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		}
		// Check if the NTP server timedout to allow the program to later
		// request the current system time instead
		else {
			// Set the time to null so the main program can decide to request
			// the current system time due to the NTP request failure
			time = null;
		}

		// Return the custom set time object
		return time;

	} // Closing 'getTimeFromServer' function

} // Closing NetworkTimeController class
