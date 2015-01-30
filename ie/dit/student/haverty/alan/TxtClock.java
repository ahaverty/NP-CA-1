package ie.dit.student.haverty.alan;

<<<<<<< HEAD
public class TxtClock {

	/**
	 * Accepts an argument or uses NTP or system time and prints the time in
	 * English to the console.
	 * 
	 * @param args
	 * @author Alan Haverty
	 */
	public static void main(String args[]) {
		
		// When the user does not provide arguments
		if (args.length == 0) {

			// TODO replace with call to NTP or system time
			Time time = new Time();
			System.out.println(time.toString());
		}
		// When the user provides one argument
		else if (args.length == 1) {
			String userInput = args[0];

			// Check if the users input is a valid 24hr time
			if (TimeController.validateTime(userInput)) {

				// Extract the valid time and create a time object
				Time time = TimeController.extractTime(userInput);

				// Print the time using the Time objects overwritten toString
				// method
				System.out.println(time.toString());

			}
			// When the users input was not a valid 24hr time
			else {
				// Call the warn function to output instructions to the console
				// and exit the program
				warnInvalidInput();
			}

		}
		// When the user input more than one argument
		else {
			// Call the warn function to output instructions to the console
			// and exit the program
			warnInvalidInput();
		}

	}

	/**
	 * Prints instructions to the console and exits the program.
	 */
	private static void warnInvalidInput() {
		
		System.out.println("Options:");
		System.out.println("1: Provide one argument in the 24hr time format"
				+ " \"HH:MM:SS\" or \"HH:MM\".");
		System.out.println("2: Do not provide any argument to use the current system time.");

		// Exit the program using a non-zero positive integer
		System.exit(1);
=======
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtClock {

	// RegEx string for 24hr time with option of including seconds.
	// Seconds nested grouped for easier extraction without extracting colon.
	private static String time_pattern = "([01]?[0-9]|2[0-3]):([0-5][0-9])(:([0-5][0-9]))?";
	private static Pattern pattern;

	/**
	 * TODO Main function
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		pattern = Pattern.compile(time_pattern);
		
		// TODO extract tests into their own class
		testCaseTimes();
		
		// TODO encapsulate the following better
		if (args.length == 1) {
			
			String userInput = args[0];
			Calendar time;

			if (validateTime(userInput)) {

				time = extractTime(userInput);

				System.out.print("True\t");

				System.out.print(timeToDigitString(time));
			} else {
				System.out.print("False\t");
				System.out.print(userInput);
			}
		} else {
			System.out.println("Incorrect amount of arguments provided.");
			System.out.println("Please provide a time in 24hr format seperated by colons.");
			System.out.println("Seconds are optional. HH:MM:SS or HH:MM");
		}

	}

	/**
	 * Validates an input string using a RegEx pattern matcher.
	 * 
	 * @param time
	 *            in string format HH:MM:SS or HH:MM
	 * @return True if the input string is a valid time, False if it is not
	 */
	private static boolean validateTime(String time) {
		Matcher matcher = pattern.matcher(time);

		return matcher.matches();
	}

	/**
	 * Extracts the time into a calendar object taken from the input string
	 * using a RegEx pattern matcher.
	 * 
	 * @param time
	 *            in string format HH:MM:SS or HH:MM
	 * @return A blank Calendar object with the Hour(24hr), Minute and Second(00
	 *         if not in string)
	 */
	private static Calendar extractTime(String time) {
		Matcher matcher = pattern.matcher(time);

		Calendar calendar = Calendar.getInstance();
		// clearing calendar for cases without seconds defined
		calendar.clear();

		if (matcher.find()) {
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(matcher.group(1)));
			calendar.set(Calendar.MINUTE, Integer.parseInt(matcher.group(2)));

			if (matcher.group(4) != null) {
				calendar.set(Calendar.SECOND, Integer.parseInt(matcher.group(4)));
			}
		}

		return calendar;
	}

	/**
	 * Converts a Calendar object to a string taking the Hour, Minutes and
	 * Seconds.
	 * 
	 * @param cal
	 * @return string in the form "HH:MM:SS"
	 */
	private static String timeToDigitString(Calendar cal) {
		String timeAsString = new String();

		// using string formatter to add '0' in front of single digit times.
		// e.g 1:45 -> 01:45
		timeAsString += String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));
		timeAsString += ":";
		timeAsString += String.format("%02d", cal.get(Calendar.MINUTE));
		timeAsString += ":";
		timeAsString += String.format("%02d", cal.get(Calendar.SECOND));

		return timeAsString;
	}

	/**
	 * Function to hold temporary test cases
	 */
	private static void testCaseTimes() {

		String[] testTimes = { "23:40:54", "5:29", "01:00:03", "23:59:59",
				"14:10:", "26:40:54", "01:61:54", "20:40:72", "", "alan",
				"-1:40:09" };
		Calendar time;

		for (String timeText : testTimes) {

			if (validateTime(timeText)) {

				time = extractTime(timeText);

				System.out.print("True\t");

				System.out.print(timeToDigitString(time));
			} else {
				System.out.print("False\t");
				System.out.print(timeText);
			}

			System.out.print("\n");

		}
>>>>>>> origin/master
	}

}