package ie.dit.student.haverty.alan.txtclock;

/**
 * Controller class to manage the translation of the Time object into english
 * words
 * 
 * @author Alan Haverty
 *
 */
public class LanguageController {

	Time time;

	private enum tense_type {
		PAST, TO, HOUR
	};

	tense_type tense;

	/**
	 * Constructor to set the time for the controller class and to kick of the
	 * setTense function
	 * 
	 * @param time
	 */
	public LanguageController(Time time) {
		this.time = time;
		setTense();
	}

	/**
	 * Method to set the tense of the English sentence according to the minute
	 */
	private void setTense() {
		int minute = time.getMinute();

		if (minute > 0 && minute < 31) {
			// Set the Controllers tense to PAST if minutes between 1-30
			tense = tense_type.PAST;
		} else if (minute > 30 && minute < 60) {
			// Set the Controllers tense to TO if minutes between 31-59
			tense = tense_type.TO;
		}
	}

	/**
	 * Time as an English spoken sentence (e.g
	 * "It's ten minutes past 11 o'clock")
	 * 
	 * @return The time as an English sentence.
	 */
	public String printTimeInEnglish() {
		String timeInEnglish = new String();

		if (tense == tense_type.HOUR) {

			// TODO match integer values to English word!
			timeInEnglish += "It's " + time.getHour() + " o'clock.";

		} else if (tense == tense_type.PAST) {

			timeInEnglish += "It's " + time.getMinute();

			if (time.getMinute() == 1) {
				timeInEnglish += " minute past ";

			} else {

				timeInEnglish += " minutes past ";
			}

			timeInEnglish += time.getHour();

		} else if (tense == tense_type.TO) {

			int minute = 60 - time.getMinute();

			timeInEnglish += "It's " + minute;

			if (minute == 1) {
				timeInEnglish += " minute to ";

			} else {
				timeInEnglish += " minutes to ";

			}

			if (time.getHour() < 23) {
				timeInEnglish += time.getHour() + 1;

			} else {
				timeInEnglish += 1;
			}

		}

		return timeInEnglish;
	}

}
