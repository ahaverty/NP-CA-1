/**
 * Alan Haverty
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Test-bed class for testing functions and objects
 * 
 * @author Alan Haverty
 *
 */
public class TimeModelTestbed {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] testTimes = { "13:20", "14:15", "15:30", "15:35",
				"16:45", "17:00", "23:59"};

		for (String time : testTimes) {
			if (TimeController.validateTime(time)) {
				
				Time testTime = TimeController.extractTime(time);
				
				String timeToPrint = LanguageController.timeInEnglish(testTime);
				
				System.out.println(time + " : " + timeToPrint);
			}
		}
		
		String ntpServer = "pool.ntp.org";
		String timestamp = new String();
		
		
		Time ntpTime = NetworkTimeController.getTimeFromServer(ntpServer);
		System.out.println(ntpServer + " says it is: " + LanguageController.timeInEnglish(ntpTime));
		
		try{
		System.out.println(new File(".").getCanonicalPath());
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
