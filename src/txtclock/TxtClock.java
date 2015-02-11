/**
 * Alan Haverty
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;

import ie.dit.student.haverty.alan.txtclock.LanguageController;
import ie.dit.student.haverty.alan.txtclock.NetworkTimeController;

public class TxtClock {

	/**
	 * Accepts a 24hr time argument or uses NTP or system time and prints the
	 * time in English to the console.
	 * 
	 * @param args
	 * @author Alan Haverty
	 */
	public static void main(String args[]) {
		
		String methodUsed = new String();
		
		// Set the NTP server url to be used
		String ntpServer = "pool.ntp.org";
		
		// Initialise an empty Time object that later stores the time taken
		// from the NTP server or the local system time
		Time time = new Time();

		// When the user does not provide arguments
		if (args.length == 0) {
			
			Time ntpTime = new Time();

			// Attempt to get the time from an NTP server
			ntpTime = NetworkTimeController.getTimeFromServer(ntpServer);
			
			if(ntpTime != null){
				methodUsed = "NTP Server";
				time = ntpTime;
			}else{
				methodUsed = "Current System Time";
				time.setToCurrentSystemTime();
			}
			
		} // End if loop for when no arguments were supplied
		
		// When the user provides one argument
		else if (args.length == 1) {

			String userInput = args[0];

			// Check if the users input is a valid 24hr time
			if (TimeController.validateTime(userInput)) {

				// Extract the valid time and create a time object
				time = TimeController.extractTime(userInput);
				
				methodUsed = "User Input";

				

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
		
		System.out.println("Time taken from: " + methodUsed);
		
		System.out.println(time.toString());
		System.out.print("\n");
		
		// Print the time using the Time objects overwritten toString
		// method
		System.out.println(LanguageController.timeInEnglish(time));
		System.exit(0);

	}

	/**
	 * Prints instructions to the console and exits the program.
	 */
	private static void warnInvalidInput() {
		
		System.out.println("TxtClock has detected incorrect input.");
		System.out.println("Options:");
		System.out.println("1: Provide one argument in the 24hr time format"
				+ " \"HH:MM:SS\" or \"HH:MM\".");
		System.out.println("2: Do not provide any argument to use the current system time.");

		//TODO document and unique exit codes
		// Exit the program using a non-zero positive integer
		//TODO remove from method and place underneath each call with unique error id
		System.exit(-1);
	}

}