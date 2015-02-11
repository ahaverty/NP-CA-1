/**
 * Alan Haverty
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to test the Word Controller
 * @author Alan Haverty
 *
 */
public class WordsControllerTestbed {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		
		System.out.println("Current dir: " + System.getProperty("user.dir"));
		
		Map<Integer, String> wordMap = new HashMap<Integer, String>();
		
		try {
			URL fileURL = WordsControllerTestbed.class.getName().getClass().getResource("/ie/dit/student/haverty/alan/txtclock/words_eng.txt");
			File textFile = new File(fileURL.getFile());
			wordMap = WordsController.importWords(textFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(wordMap.get(4));
	}

}
