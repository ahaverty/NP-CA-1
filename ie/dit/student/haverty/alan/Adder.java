package ie.dit.student.haverty.alan;

import java.util.ArrayList;
import java.util.List;

/**
 * Adder is a command-line utility which accepts exactly two command-line
 * arguments each representing a real number. Adder prints on screen the result
 * of adding the two numbers supplied
 * 
 * @author Alan Haverty
 *
 */
public class Adder {

	/**
	 * Main function to add a users inputted arguments and return the equation
	 * and result on-screen. The number of arguments that the program can accept
	 * is dynamically defined by the final variable NUM_OF_ARGS. The Adder
	 * program also handles incorrect data types entered and incorrect number of
	 * arguments.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Define how many arguments/numbers to accept from the user here for
		// the entire program
		final int NUM_OF_ARGS = 2;

		// Initialise the list later used to store the 2 numbers inputed by
		// the user
		List<Double> userInput = new ArrayList<Double>();

		// initialise the result variable used when adding the numbers
		Double result;

		// if there are exactly 2 arguments provided
		if (args.length == NUM_OF_ARGS) {

			// loop twice to parse the arguments into doubles into the list
			for (int i = 0; i < NUM_OF_ARGS; i++) {

				// attempt to parse the arguments into double format
				try {
					userInput.add(i, Double.parseDouble(args[i]));

				} catch (NumberFormatException e) {
					// If the argument failed to parse into a double, print an
					// error
					System.err.println("Argument "
							+ i
							+ " '"
							+ args[i]
							+ "' must be a real number in integer or decimal form\n(e.g 1 or 12.3 or -0.8625)");
					// Exit the system using a positive non-zero exit status
					System.exit(1);
				}
			} // end for-loop for parsing the arguments

		} else {
			// Inform the user when an incorrect number of arguments have been
			// entered
			System.err.println("The 'Adder' program needs exactly "
					+ NUM_OF_ARGS + " arguments in the form of real numbers");
			System.err.println("integer or decimal format e.g 1 or 12.3 or -0.8625).\n");
			System.err.println("You have supplied: " + args.length);

			// Exit the system using a positive non-zero exit status
			System.exit(1);
		} // End the else loop for when arguments.length are not equal to 2

		// Start the if loop if 2 arguments were provided
		if (args.length == NUM_OF_ARGS) {

			// declare the arguments that were input
			System.out.print("Adding ");

			// passing the list into function to get human readable equation
			// string back
			System.out.print(printListAsAdditionEquation(userInput));
			System.out.print("\n");

		} // end the if loop for when args.length = NUM_OF_ARGS

		// Return the sum of the user's inputs
		result = sumOfContentOfList(userInput);

		// Print the result of the equation to the user
		System.out.print("The sum of ");

		System.out.print(printListAsAdditionEquation(userInput));

		System.out.print(" is equal to: " + result);

	}

	/**
	 * Return the contents of a provided list of Doubles as an equation in the
	 * String form 'arg1 + arg2 + arg3'.
	 * 
	 * @param list
	 * @return The list in String equation format.
	 */
	private static String printListAsAdditionEquation(List<Double> list) {

		// Initialise new blank string for the result
		String equationString = new String();

		// index variable for counting position in list
		int index = 0;

		// loop through the list adding the number and a '+' character to the
		// String that will be returned
		for (Double number : list) {

			// Parse the next number from the list into type String and add it
			// to the equationString
			equationString += Double.toString(number);

			// check if the for loop has reached the last item in the list to
			// avoid adding an unneeded '+' character to the end.
			if (index != (list.size() - 1)) {
				equationString += " + ";
				index++;
			}

		} // end the for loop for printing arguments

		return equationString;

	} // end printListAsAdditionEquation function

	/**
	 * Function to add the contents of a list of Doubles and returning the
	 * result of type Double
	 * 
	 * @param list
	 * @return the sum of the contents of the provided list in the form of a
	 *         Double
	 */
	private static Double sumOfContentOfList(List<Double> list) {

		// initialise a variable to 0 to hold the result of the addition
		Double sum = (double) 0;

		// Loop through the list adding each item to the sum variable
		for (Double number : list) {
			sum += number;
		}

		return sum;

	} // End sumOfContentOfList function

}
