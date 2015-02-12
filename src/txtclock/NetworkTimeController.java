/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;	// The package this class belongs to

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Calendar;

// Imports from NTP apache commons net jar for NTP client connection and TimeInfo object for holding the time result
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;

/**
 * Class that makes NTP request to NTP pool for current time
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

		boolean timedOut = false;

		// Wrapping the ntp request in a try catch
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

			timedOut = true;

		} // Ending try catch

		// Initialise a calendar object
		Calendar cal = Calendar.getInstance();

		//
		if (timedOut == false) {
			// Set the calendar object using the NTP result's milliseconds since
			// epoch

			long ntpEpochMillis = info.getMessage().getTransmitTimeStamp().getTime();

			cal.setTimeInMillis(ntpEpochMillis);

			// Set a time object with the hour, minutes and seconds taken from
			// the calendar object
			time.setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		} else {
			time = null;
		}

		// Return the custom set time object
		return time;
	} // Closing 'getTimeFromServer' function

} // Closing NetworkTimeController class
