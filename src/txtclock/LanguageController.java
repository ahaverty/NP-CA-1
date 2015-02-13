/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock; // The package this class belongs to

import java.io.File; // Import allowing class to handle opening and reading external files 
import java.io.FileNotFoundException; // Import for controlling exceptions when a file can't be found
import java.io.IOException; // Import allowing class to handle Input/Output exceptions
import java.net.URL; // Import allowing class to use URL objects to reference the external text file
import java.util.ArrayList; // Import allowing the class to use ArrayList objects
import java.util.HashMap; // Import allowing the class to use HashMap objects later used to map the english words to their keys
import java.util.Map; // Import allowing the class to use Map objects in conjunction with the HashMap import

/**
 * Controller class to manage the translation of the Time object into English
 * spoken words
 * 
 * @author Alan Haverty
 *
 */
public class LanguageController {

	/**
	 * Time as an English spoken sentence (e.g
	 * "It is ten minutes past eleven o'clock") TODO Need to comment this class!
	 * 
	 * @return The time as an English sentence.
	 */
	public static String timeInEnglish(Time time) {

		// Initialise an empty string to hold the English sentence later
		// outputted to the console
		String timeInWords = new String();

		// Initialise a Map object that will hold the key and values for the
		// digit to English words taken from the text file
		Map<Integer, String> wordMap = new HashMap<Integer, String>();

		// Call a method from this class to retrieve a text file containing the
		// digits and their respective words
		File wordFile = openWordFile("words_eng.txt");

		// Attempt to import from the text file
		try {
			// Pass the File to the importWords function to extract the key and
			// values and return a Map object
			wordMap = WordsController.importWords(wordFile);

		}
		// Catch Exception when an incorrect file is being passed to the
		// importWords function
		catch (IOException e) {
			// Print the error to the console along with the exception message
			System.out.println("Incorrect file has been opened: "
					+ e.getMessage());
			
			// Exit the program and print the error code
			System.out.println("Exit Code: -3");
			System.exit(-3);
		} // End catch block

		// Retrieve the minute, hour and second values into new variables from
		// the time object that was passed into this function
		int minute = time.getMinute();
		int hour = time.getHour();
		int second = time.getSecond();

		// Initialise new strings that will hold the English words for the hour,
		// minute and tense of the English sentence. They are not initialized
		// with their integer english values yet as the hour differs depending
		// on the minute and the minute differs depending on the tense e.g
		// 10:40, the minute would actually be twenty as we do not say fourty
		// past ten, we say twenty to eleven
		String hourStr = new String();
		String minuteStr = new String();
		String tenseStr = new String();

		// The second can be directly initialized with its English value as the
		// second doesn't depend on the tense of the sentence.
		// NOTE: The integer value for the second is being passed into the
		// wordMap to retrieve the equivalent English word
		String secondStr = wordMap.get(second);

		// ----- Algorithm to create the English sentence ----- //

		// If the hour is a 24hr value
		if (hour > 12 && hour < 24) {
			// Convert the hour to its am/pm equivalent by subtracting 12 hours
			hourStr = wordMap.get(hour - 12);
		}
		// Or if the hour is 0 (i.e. 12 midnight)
		else if (hour == 0) {
			// Retrieve the English word 'twelve'
			hourStr = wordMap.get(12);
		}
		// If none of the above matched the hour
		else {
			// Simply get the english word for the hour as is, from the wordMap
			hourStr = wordMap.get(hour);
		}

		// Else if block for handling minutes, tense and hour changes of the
		// sentence

		// If the minute is on the right hand side of the clock (i.e 'PAST')
		if (minute > 0 && minute < 31) {

			// Set the tense string to print 'past'
			tenseStr = " past ";

			// If the minute is specifically 15 minutes
			if (minute == 15) {
				// Retrieve a special defined value from wordMap (i.e 'a
				// quarter')
				minuteStr = wordMap.get(101);
			} // End if minute is 15

			// If the minute is specifically 15 minutes
			else if (minute == 30) {
				// Retrieve a special defined value from wordMap (i.e 'half')
				minuteStr = wordMap.get(102);
			} // End if minute = 30

			// If the minute doesn't match one of the above special cases, then
			// just get its English equivalent as is
			else {
				// Get the english word for the integer value and also append
				// 'minute to the string'
				minuteStr = wordMap.get(minute) + " minute";

				// If the minute is greater than 1 (i.e. 10:02)
				if (minute > 1) {
					// Append an 's' character to the minuteStr, effectively
					// making the word 'minute' plural = 'minutes'
					minuteStr += "s";
				} // End if minute is 1

			} // End if minute is not a special case

		} // End if the minute is on the right hand side of the clock

		// If the minute is on the left hand side of the clock (i.e. 'TO')
		else if (minute > 30 && minute < 60) {

			// adjust the minute by subtracting the int from 60. This allows us
			// to output how many minutes are left until the next hour
			int adjustedMinute = 60 - minute;

			// set the tense to 'to'
			tenseStr = " to ";

			// If the minute is specifically 45
			if (minute == 45) {
				// Retrieve a special defined value from wordMap (i.e 'a
				// quarter')
				minuteStr = wordMap.get(101);
			} // End if the minute is 45

			// If the minute is not a special case
			else {
				// Get the equivalent english word by using the adjusted minute
				// value on the Word Map. Also append the word 'minute' to the
				// string
				minuteStr = wordMap.get(adjustedMinute) + " minute";

				// If the adjusted minute is greater than 1, (i.e. plural)
				if (adjustedMinute > 1) {
					// Append an 's' character to the minuteStr, effectively
					// making the word 'minute' plural = 'minutes'
					minuteStr += "s";
				} // End if for plural minutes
			} // End if for non special case minutes

			// If the hour is 23 (Note: not 24 because the tense is = to)
			if (hour == 23) {
				// Set the hour to twelve
				hourStr = wordMap.get(12);
			}
			// If the hour is going to be greater than 11
			else if (hour > 11 && hour < 23) {
				// Convert to an am/pm hour and also get the next hour to be
				// (due to tense of sentence)
				hourStr = wordMap.get((hour - 12) + 1);
			}
			// For every other hour (00 - 10)
			else {
				// Get the next hour in English from the word map
				hourStr = wordMap.get(hour + 1);
			}
		} // End else if block for handling minutes, tense and hour changes

		// If the second is exactly 0 (:00)
		if (second == 0) {
			// Compile the strings into one sentence, not including the seconds,
			// instead saying it is exactly some time..
			timeInWords = "It is exactly " + minuteStr + tenseStr + hourStr
					+ " o'clock";
		}
		// Else if the second is something other than 0 (:00)
		else {
			// Compile the strings into one sentence, including the seconds
			timeInWords = "It is " + minuteStr + tenseStr + hourStr
					+ " o'clock and " + secondStr + " second";

			// If the second is plural
			if (second > 1) {
				// Append an 's' character to the sentence
				timeInWords += "s";
			} // End plural second check
		}
		// ----- End algorithm to create the English sentence ----- //

		// Return the compiled english sentence
		return timeInWords;
	}

	/**
	 * Function returns a file used to later extract the file data into a word
	 * Map to allow the conversion of an integer value into its English
	 * equivalent word.
	 * 
	 * @param filename
	 * @return the text file
	 */
	private static File openWordFile(String filename) {

		// Initialise a null file
		File textFile = null;

		// Attempt to retrieve the file
		try {
			// Get a pointer URL to the file, using the project sourcepath as a
			// starting point
			URL fileURL = LanguageController.class.getName().getClass().getResource("/ie/dit/student/haverty/alan/txtclock/"
					+ filename);
			// open the text file using the URL pointer from above
			textFile = new File(fileURL.getFile());
		}// End try parenthesis

		// Catch exception when the file can't be found
		catch (NullPointerException e) {
			// Print an error to the console along with the filename that
			// couldn't be found
			System.out.println("Error: Unable to find file: '" + filename
					+ "'. Please check that it exists.");

			// Exit the program and print the error code
			System.out.println("Exit Code: -4");
			System.exit(-4);
		} // End catch exception when the file couldn't be found

		// Return the text file
		return textFile;
	} // End openWordFile() function

} // End LanguageController class
