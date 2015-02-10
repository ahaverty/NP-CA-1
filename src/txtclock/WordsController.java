/**
 * Alan Haverty
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for mapping keys to their english word values and also custom key
 * values such as "a quarter" and "half"
 * 
 * @author Alan Haverty
 *
 */
public class WordsController {

	/**
	 * Imports words from a resource/text file to allow the program to map the
	 * integer hour, minute and second values to words in english
	 * 
	 * @param filename
	 * @return The Map object set with the values from an external file
	 * @throws IOException
	 */
	public static Map<Integer, String> importWords(String filename) throws IOException {
		
		//Initialise a Map object that takes an int key and a string value
		Map<Integer, String> wordMap = new HashMap<Integer, String>();
		
		//Create a reader using a file opened by filereader
		BufferedReader in = new BufferedReader(new FileReader(filename));
		
		//Initialise a string
		String line = new String();
		
		//While the text file still has more lines
		while ((line = in.readLine()) != null) {
			//Split the line into parts on the tab character
			String parts[] = line.split("\t");
			
			//Parse the split line into key and value into the map
			wordMap.put(Integer.parseInt(parts[0]), parts[1]);
		}
		
		//Close the reader
		in.close();
		
		//Return the map initialised with keys and values from the external file
		return wordMap;
	}

}
