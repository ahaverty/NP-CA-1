/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

import java.io.File;	// Importing file
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	 * "It is ten minutes past eleven o'clock")
	 * TODO Need to comment this class!
	 * 
	 * @return The time as an English sentence.
	 */
	public static String timeInEnglish(Time time) {
		String timeInWords = new String();

		Map<Integer, String> wordMap = new HashMap<Integer, String>();

		File wordFile = openWordFile("words_eng.txt");

		try {
			wordMap = WordsController.importWords(wordFile);
		} catch (IOException e) {
			System.out.println("Incorrect file has been opened: "
					+ e.getMessage());
			System.exit(-2);
		}

		int minute = time.getMinute();
		int hour = time.getHour();
		int second = time.getSecond();

		String hourStr = new String();
		String minuteStr = new String();
		String secondStr = wordMap.get(second);
		String tenseStr = new String();

		if (hour > 12 && hour < 24) {
			hourStr = wordMap.get(hour - 12);
		} else if (hour == 0) {
			hourStr = wordMap.get(12);
		} else {
			hourStr = wordMap.get(hour);
		}

		if (minute > 0 && minute < 31) {
			tenseStr = " past ";

			if (minute == 15) {
				minuteStr = wordMap.get(101);
			} else if (minute == 30) {
				minuteStr = wordMap.get(102);
			} else {
				minuteStr = wordMap.get(minute) + " minute";

				if (minute > 1) {
					minuteStr += "s";
				}
			}

		} else if (minute > 30 && minute < 60) {
			int adjustedMinute = 60 - minute;
			tenseStr = " to ";

			if (minute == 45) {
				minuteStr = wordMap.get(101);
			} else {
				minuteStr = wordMap.get(adjustedMinute) + " minute";

				if (adjustedMinute > 1) {
					minuteStr += "s";
				}
			}

			if (hour == 23) {
				hourStr = wordMap.get(12);
			} else if (hour > 11 && hour < 23) {
				hourStr = wordMap.get((hour - 12) + 1);
			} else {
				hourStr = wordMap.get(hour + 1);
			}
		}

		// TODO add morning, evening, night etc..
		if (second == 0) {
			timeInWords = "It is exactly " + minuteStr + tenseStr + hourStr
					+ " o'clock";
		} else {
			timeInWords = "It is " + minuteStr + tenseStr + hourStr
					+ " o'clock and " + secondStr + " second";
			if (second > 1) {
				timeInWords += "s";
			}
		}

		return timeInWords;
	}

	private static File openWordFile(String filename) {
		File textFile = null;
		try {
			URL fileURL = LanguageController.class.getName().getClass().getResource("ie/dit/student/haverty/alan/txtclock/words_eng.txt");
			textFile = new File(fileURL.getFile());
		} catch (NullPointerException e) {
			System.out.println("Error: Unable to find file: '" + filename
					+ "'. Please check that it exists.");

			// TODO Change to unique exit value and document throughout project
			System.exit(-1);
		}
		return textFile;
	}
}
