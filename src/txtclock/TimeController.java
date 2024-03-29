/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;	// The package this class belongs to

import java.util.regex.Matcher;	// Import to allow the class to use the regex matcher
import java.util.regex.Pattern; // Import to allow the class to use the regex pattern to create a regex matcher

/**
 * Controller class used to validate string inputs and parse the strings into
 * Time objects
 * 
 * @author Alan Haverty
 *
 */
public class TimeController {

	// RegEx string for 24hr time with option of including seconds.
	// Seconds nested grouped for easier extraction without extracting colon.
	private static String time_pattern = "([01]?[0-9]|2[0-3]):([0-5][0-9])(:([0-5][0-9]))?";
	
	// Set a class Pattern variable
	private static Pattern pattern;

	/**
	 * Validates an input string using a RegEx pattern matcher.
	 * 
	 * @param time
	 *            in string format HH:MM:SS or HH:MM
	 * @return True if the input string is a valid time, False if it is not
	 */
	public static boolean validateTime(String time) {

		// Compile a Pattern object with the RegEx 24hr string
		pattern = Pattern.compile(time_pattern);

		// Create a Matcher object using the Pattern object on the time
		// parameter
		Matcher matcher = pattern.matcher(time);

		// Return True if the parameter string matched the RegEx, False if it
		// did not
		return matcher.matches();
	}

	/**
	 * Extracts the time into a Time object taken from the input string using a
	 * RegEx pattern matcher.
	 * 
	 * @param time
	 *            in string format HH:MM:SS or HH:MM
	 * @return A Time object with the time set
	 */
	public static Time extractTime(String timeString) {
		// Create a new blank Time object to later hold the extracted time
		Time time = new Time();

		// Compile a Pattern object with the RegEx 24hr string
		pattern = Pattern.compile(time_pattern);

		// Create a Matcher object using the Pattern object on the time
		// parameter
		Matcher matcher = pattern.matcher(timeString);

		// Initialise integer variables to store the extracted digits from the
		// parameter
		int hour = 0;
		int minute = 0;
		int second = 0;

		// TODO handle invalid time strings being passed in!

		// If the matcher finds a subsequence within the input string
		if (matcher.find()) {

			// Parse the 1st subsequence into the hour variable
			hour = Integer.parseInt(matcher.group(1));
			// Parse the 2nd subsequence into the minute variable
			minute = Integer.parseInt(matcher.group(2));

			// If the matcher matched the fourth subsequence i.e Seconds
			// NOTE: Skipped the 3rd subsequence which is the second's semicolon
			// ':'
			if (matcher.group(4) != null) {
				// Parse the fourth subsequence into the 'second' variable
				second = Integer.parseInt(matcher.group(4));
			}
		} // End the if matcher block

		// Set the time of the Time object
		time.setTime(hour, minute, second);

		// Return the set Time object
		return time;
	} // End extractTime function

} // End TimeController class
