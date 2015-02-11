/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

/**
 * Class to test program with dummy input and expected output. NOTE: MUST RUN
 * WITH PARAMETER -ea TO ENABLE ASSERTS!
 * 
 * @author Alan Haverty
 *
 */
public class TestTxtClock {

	/**
	 * Main test class for testing the TxtClock program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Valid 24hr time strings
		String[] testTimesValid = { "13:20", "14:15", "15:30", "15:35",
				"16:45", "17:00", "23:59", "00:40", "22:19:00", "04:08:15",
				"22:19:30", "12:00", "00:00" };

		// Expected values for time to English sentence conversions
		String[] testTimesEnglish = {
				"It is exactly twenty minutes past one o'clock",
				"It is exactly a quarter past two o'clock",
				"It is exactly half past three o'clock",
				"It is exactly twenty-five minutes to four o'clock",
				"It is exactly a quarter to five o'clock",
				"It is exactly five o'clock",
				"It is exactly one minute to twelve o'clock",
				"It is exactly twenty minutes to one o'clock",
				"It is exactly nineteen minutes past ten o'clock",
				"It is eight minutes past four o'clock and fifteen seconds",
				"It is nineteen minutes past ten o'clock and thirty seconds",
				"It is exactly twelve o'clock", "It is exactly twelve o'clock" };

		// Invalid strings for input
		String[] testTimesInvalid = { "24:00", "25:00", "-1:20", "one:05",
				"13:20:", "22:19:60", "21:01:5", ":::", "nan", "null", "\0",
				"0:0", "9398487.32409540397638734565" };

		// For loop for each valid test time string
		for (int i = 0; i < testTimesValid.length; i++) {

			// Assert if each test time string is valid/ meets regex requirement
			// Assert will fail if validateTime returns false
			assert (TimeController.validateTime(testTimesValid[i]));

			// Print out the input to the console for debug/tracking purposes
			System.out.println("Input '" + testTimesValid[i]
					+ "' passed validation as expected.");
		}

		// For loop for each invalid test time string
		for (int i = 0; i < testTimesInvalid.length; i++) {

			// Assert if each test time string is invalid/meets regex
			// requirement to ensure no invalid strings can pass through
			// Assert will fail if validateTime returns true
			assert (TimeController.validateTime(testTimesInvalid[i]) == false);

			// Print out the input to the console for debug/tracking purposes
			System.out.println("Input '" + testTimesInvalid[i]
					+ "' failed validation as expected.");
		} // End for loop for invalid strings

		// For loop for validating the English sentence conversion works as
		// expected
		for (int i = 0; i < testTimesValid.length; i++) {

			// Extract time from test string to Time object
			Time timeValid = TimeController.extractTime(testTimesValid[i]);

			// Convert extracted Time object to English sentence
			String timeInEnglish = LanguageController.timeInEnglish(timeValid);

			// Assert that the converted string matches the tests strings as
			// expected
			assert (timeInEnglish.equalsIgnoreCase(testTimesEnglish[i]));
			
			// Print out the input to the console for debug/tracking purposes
			System.out.println(testTimesValid[i] + " : " + timeInEnglish);
			System.out.println(testTimesValid[i]
					+ " : Translated to english sentence as expected");
		} // End for loop for testing the english sentence converter

	} // End TestTxtClock main method

} // End TestTxtClock class