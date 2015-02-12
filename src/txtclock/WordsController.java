/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock; // The package this class belongs to

import java.io.BufferedReader; // Import needed for class to read and buffer data from an external file
import java.io.File; // Import allowing class to handle opening and reading external files
import java.io.FileNotFoundException; // Import for controlling exceptions when a file can't be found
import java.io.FileReader; // Import for allowing a filereader to be created from the file for the bufferedReader
import java.io.IOException; // Import allowing class to handle Input/Output exceptions
import java.net.URL; // Import allowing class to use URL objects to reference the external text file
import java.util.HashMap; // Import allowing the class to use HashMap objects later used to map the english words to their keys
import java.util.Map; // Import allowing the class to use Map objects in conjunction with the HashMap import

/**
 * Class for mapping keys to their English word values and also custom key
 * values such as "a quarter" and "half"
 * 
 * @author Alan Haverty
 */
public class WordsController {

	/**
	 * Imports words from a resource/text file to allow the program to map the
	 * integer hour, minute and second values to words in English
	 * 
	 * @param filename
	 * @return The Map object set with the values from an external file
	 * @throws IOException
	 */
	public static Map<Integer, String> importWords(File file) throws IOException {

		// Initialise a Map object that takes an int key and a string value
		Map<Integer, String> wordMap = new HashMap<Integer, String>();

		// Create a reader using a file opened by filereader
		BufferedReader in = new BufferedReader(new FileReader(file));

		// Initialise a string
		String line = new String();

		// While the text file still has more lines
		while ((line = in.readLine()) != null) {

			// Attempt to parse the file data into the key and values of the
			// wordMap
			try {
				// Split the line into parts on the tab character and store in
				// an array
				String parts[] = line.split("\t");

				// Parse the split line from the array into key and value into
				// the map
				wordMap.put(Integer.parseInt(parts[0]), parts[1]);

			} catch (NumberFormatException e) {
				// This catch block is triggered when the string could not be
				// parsed into an integer, usually meaning there is invalid key
				// and values in the text file being imported.

				// Warn the user of the error
				System.out.println("Invalid text contained in the file being imported.");
				System.out.println("Please ensure there are no invalid characters or whitespaces.");
				System.out.println("Exiting TxtClock program.");

				// Exit the system
				System.exit(-1);
			} // End catch block preventing invalid input from the text file

		} // End while for reading in from the text file

		// Close the reader
		in.close();

		// Return the map initialized with keys and values from the external
		// file
		return wordMap;

	} // End the importWords function

} // End the WordsController class