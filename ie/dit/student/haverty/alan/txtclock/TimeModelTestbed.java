package ie.dit.student.haverty.alan.txtclock;

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

		String[] testTimes = { "23:40:54", "5:29", "01:00:03", "23:59:59",
				"14:10:", "26:40:54", "01:61:54", "20:40:72", "", "alan",
				"-1:40:09" };

		for (String time : testTimes) {
			if (TimeController.validateTime(time)) {
				String correctTime = TimeController.extractTime(time).toString();
				System.out.println(correctTime);
			}
		}

		Time time = new Time(22, 59, 10);

		System.out.println(time.toString());
		
		LanguageController englishTimePrinter = new LanguageController(time);
		System.out.println(englishTimePrinter.printTimeInEnglish());

	}

}
