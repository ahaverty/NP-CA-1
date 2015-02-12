/**
 * Alan Haverty DT211/3
 * C12410858
 * alan.haverty@student.dit.ie
 * Network Programming CA 1 - NTP TxtClock
 */
package ie.dit.student.haverty.alan.txtclock;	// The package this class belongs to

/**
 * Main class for TxtClock program
 * @author Alan Haverty
 */
public class TxtClock {

	/**
	 * Accepts a 24hr time argument or uses NTP or system time and prints the
	 * time in English to the console.
	 * 
	 * @param args
	 * @author Alan Haverty
	 */
	public static void main(String args[]) {

		// Initialise a blank string later used to store how the program
		// retrieved the time
		String methodUsed = new String();

		// Set the NTP server url to be used
		String ntpServer = "pool.ntp.org";

		// Initialise an empty Time object that later stores the time taken
		// from the NTP server or the local system time or the user input
		Time time = new Time();

		// When the user does not provide arguments
		if (args.length == 0) {

			// Initialise a Time object to temporarily hold the time returned
			// from NTP server
			Time ntpTime = new Time();

			// Attempt to get the time from an NTP server by calling static
			// method from NetworkTimeController class
			ntpTime = NetworkTimeController.getTimeFromServer(ntpServer);

			// If the Time object returned from the NetworkTimeController was
			// not null
			if (ntpTime != null) {

				// Set the method used to retrieve the time, for later printing to console
				methodUsed = "NTP Server";
				
				// Set the main function's time object to the result of the NTP request 
				time = ntpTime;
				
			} // End if statement for when NTP returned a time that was not null

			// If the Time object returned from the NetworkTimeController was
			// null (Usually meaning the NTP request failed or timed out)
			else {
				// Warn the user about the failure to retrieve the time from the
				// remote NTP server
				System.out.println("Warning: Failed to retrieve the time from a remote NTP server.");
				System.out.println("Using local system time instead.");

				// Set the method used to retrieve the time, for later printing to console
				methodUsed = "Current System Time";
				// Set the time to the system's current time
				time.setToCurrentSystemTime();
			} // End else loop for when NTP failed to return a time

		} // End if loop for when no arguments were supplied

		// When the user provides one argument
		else if (args.length == 1) {

			// Retrieve the first argument passed into the program
			String userInput = args[0];

			// Check if the users input is a valid 24hr time
			if (TimeController.validateTime(userInput)) {

				// Extract the valid time and create a time object
				time = TimeController.extractTime(userInput);

				// Set the method used to retrieve the time, for later printing
				// to console
				methodUsed = "User Input";

			} // End the if for when user input was a valid time format
				// Else when the users input was not a valid 24hr time
			else {
				// Call the warn function to output instructions to the console
				// and exit the program
				warnInvalidInput();
				// TODO document and unique exit codes
				System.exit(-1);
			} // End the else when input was not in a valid 24hr time format

		} // End the else if for when the user provided one argument
			// Else when the user input more than one argument
		else {
			// Call the warn function to output instructions to the console
			// and exit the program
			warnInvalidInput();
			// TODO document and unique exit codes
			System.exit(-1);
		} // End else when the user input more than one argument

		// Tell the user what method was used to retrieve the time
		// (NTP Server/System Time/User Input)
		System.out.println("Time taken from: " + methodUsed);

		// Print the time, using the Time objects overwritten toString method,
		// in regular 24hr format (e.g 22:14:34) for reference
		System.out.println(time.toString());

		// Skip a line on the console to separate the output for easier reading
		System.out.print("\n");

		// Print the time
		System.out.println(LanguageController.timeInEnglish(time));

		// Exit with 0 to suggest the program ran successfully
		System.exit(0);

	} // End main() TxtClock function

	/**
	 * Prints user instructions to the console. Encapsulated due to multiple
	 * calls in the main() function
	 */
	private static void warnInvalidInput() {

		// Console messages to instruct the user how to use the program properly
		System.out.println("TxtClock has detected incorrect input.");
		System.out.println("Options:");
		System.out.println("1: Provide one argument in the 24hr time format"
				+ " \"HH:MM:SS\" or \"HH:MM\".");
		System.out.println("2: Do not provide any argument to use a remote NTP server to get the time.");

	} // End warnInvalidInput function

} // End TxtClock class