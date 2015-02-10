/**
 * Alan Haverty
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class to manage the translation of the Time object into english
 * spoken words
 * 
 * @author Alan Haverty
 *
 */
public class LanguageController {

	/**
	 * Time as an English spoken sentence (e.g
	 * "It is ten minutes past eleven o'clock")
	 * 
	 * @return The time as an English sentence.
	 */
	public static String timeInEnglish(Time time) {
		String timeInWords = new String();

		Map<Integer, String> wordMap = new HashMap<Integer, String>();

		try {
			// TODO include move command in the install document for text file
			// into src
			// TODO or create build file for running automatically javac and
			// move cmd
			wordMap = WordsController.importWords("ie/dit/student/haverty/alan/txtclock/words_eng.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		int minute = time.getMinute();
		int hour = time.getHour();

		String hourStr = new String();
		String minuteStr = new String();
		String tenseStr = new String();

		if (hour > 12 && hour < 24) {
			hourStr = wordMap.get(hour - 12);
		} else {
			hourStr = wordMap.get(hour);
		}

		if (minute > 0 && minute < 31) {
			tenseStr = " past ";

			if (minute == 15) {
				minuteStr = wordMap.get(101);
			}
			else if(minute == 30){
				minuteStr = wordMap.get(102);
			}
			else {
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
				hourStr = wordMap.get(1);
			} else if (hour > 11 && hour < 23) {
				hourStr = wordMap.get((hour - 12) + 1);
			} else {
				hourStr = wordMap.get(hour + 1);
			}
		}

		// TODO add morning, evening, night etc..
		timeInWords = "It is " + minuteStr + tenseStr + hourStr + " o'clock.";

		return timeInWords;
	}
}
