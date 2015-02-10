/**
 * Alan Haverty
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

import java.io.IOException;
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
		
		Map<Integer, String> wordMap = new HashMap<Integer, String>();
		
		try {
			wordMap = WordsController.importWords("words_eng.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(wordMap.get(4));
	}

}
