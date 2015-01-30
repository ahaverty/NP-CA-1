package ie.dit.student.haverty.alan;

public class TxtClock {

	/**
	 * Accepts an argument or uses NTP or system time and prints the time in
	 * English to the console.
	 * 
	 * @param args
	 * @author Alan Haverty
	 */
	public static void main(String args[]) {
		String userInput = args[0];

		// When the user does not provide arguments
		if (args.length == 0) {

			// TODO replace with call to NTP or system time
			Time time = new Time();
			System.out.println(time.toString());
		}
		// When the user provides one argument
		else if (args.length == 1) {

			// Check if the users input is a valid 24hr time
			if (TimeController.validateTime(userInput)) {

				// Extract the valid time and create a time object
				Time time = TimeController.extractTime(userInput);

				// Print the time using the Time objects overwritten toString
				// method
				System.out.println(time.toString());

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

	}

	/**
	 * Prints instructions to the console and exits the program.
	 */
	private static void warnInvalidInput() {
		
		System.out.println("Options:");
		System.out.println("1: Provide one argument in the 24hr time format"
				+ " \"HH:MM:SS\" or \"HH:MM\".");
		System.out.println("2: Do not provide any argument to use the current system time.");

		// Exit the program using a non-zero positive integer
		System.exit(1);
	}

}