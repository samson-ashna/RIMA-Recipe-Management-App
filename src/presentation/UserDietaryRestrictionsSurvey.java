package presentation;

import javax.swing.JOptionPane;

/**
 * 
 */
public class UserDietaryRestrictionsSurvey {
	
	/**
	 * askQuestion is a Sting type that receives an array containing 
	 * every question and answer as the user's input stored in an array.
	 */
	private static String askQuestion(String[] question) {
		//store the empty arrays
		String questionformat = "";
		String answerformat = "";
		
		//have questions displayed and looped through with the options one by one
		for(int i = 0; i < question.length; i++)
			questionformat += question[i] + "\n";		
		do {
			answerformat = JOptionPane.showInputDialog(null, questionformat);
			if(answerformat == null) {
				int exit = JOptionPane.showConfirmDialog(null, "Would you like to save your progress and continue later?", "Quit", JOptionPane.YES_NO_OPTION);
				 if (exit == JOptionPane.YES_OPTION) 
				 { 
					 JOptionPane.getRootFrame().dispose();
			        }
				else
					continue;
			}
		} 
		//answer is stored as well after checking if valid
		while (answerformat == null || !(isStringValid(answerformat)));
		return answerformat;
	}
	
	
	
		/**
		 * isStringValid accepts a String and checks to see if option A, B, C or D is picked
		 * and returns a boolean value of either true or false that is stored in the database
		 */
		private static boolean isStringValid(String options) {
			options = options.toLowerCase(); //lowercase doesn't matter
			if(options.equals("a") || options.equals("b") || options.equals("c") || options.equals("d")) {
				return true; //one of the 4 options must be chosen
			}
			else {
				JOptionPane.showMessageDialog(null, "Please pick option A, B, C or D");
				return false;
			}
		}
		
		
		/**
		 * isOptionCorrect takes the two parameters and checks if an option is chosen
		 * and returns a boolean value if the option is valid
		 */
		private static boolean isOptionCorrect(String validanswer, String response) {
			response = response.toUpperCase();
			if(validanswer.equals(response)) {
				JOptionPane.showMessageDialog(null, "Next Question >>");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid option, please choose again: \n" + validanswer);
				return false;
			}
		}

		public static void main(String[] args) {
			new UserDietaryRestrictionsSurvey();
		}
		public UserDietaryRestrictionsSurvey() {	
			String[][] question = new String[12][5]; // 12 by 5 array to store questions
			
			question[0][0] = "1. How old are you?";
			question[0][1] = "A) Under 18";
			question[0][2] = "B) 19-30";
			question[0][3] = "C) 31-60";
			question[0][4] = "D) 61+";
			
			question[1][0] = "2. Do you cook?";
			question[1][1] = "A) Yes, all the time.";
			question[1][2] = "B) Yes, moderately.";
			question[1][3] = "C) Yes, only when I have to.";
			question[1][4] = "D) No, not at all.";
			
			question[2][0] = "3. How often do you cook?";
			question[2][1] = "A) Every day";
			question[2][2] = "B) 3-4 times a week";
			question[2][3] = "C) Not so often";
			question[2][4] = "D) I don't cook";
			
			question[3][0] = "4. Do you cook by/for yourself or do you do this with/for somebody else?";
			question[3][1] = "A) Just for me";
			question[3][2] = "B) For me and my family/friends/partner";
			
			question[4][0] = "5. What kind of meals do you prepare? ";
			question[4][1] = "A) Easy (10-15 minutes)";
			question[4][2] = "B) Intermediate (20-60 minutes)";
			question[4][3] = "C) Elaborate (more than an hour)";
			
			question[5][0] = "6. Do you look up some recipes or do you know what you are cooking?";
			question[5][1] = "A) I know what I'm cooking";
			question[5][2] = "B) I look up some recipies";
			
			question[6][0] = "7. What does your diet consist of?";
			question[6][1] = "A) Vegetarian";
			question[6][2] = "B) Pescatarian";
			question[6][3] = "C) Vegan";
			question[6][4] = "D) None";
			
			question[7][0] = "8. Does your preferences include...";
			question[7][1] = "A) Low Carb";
			question[7][2] = "B) Gluten Free";
			question[7][3] = "C) Dairy Free";
			question[7][4] = "D) Keto";
			
			question[8][0] = "9. Do you have any avoidances to leave out in the recipies?";
			question[8][1] = "A) Yes";
			question[8][2] = "B) No";
			
			question[9][0] = "10. What type of nutrition do you prefer?";
			question[9][1] = "A) Healthy";
			question[9][2] = "B) High Protein/High Fiber";
			question[9][3] = "C) Low Sugar/Low Fat";
			question[9][4] = "D) None";
			
			question[10][0] = "11. What is your cooking experience?";
			question[10][1] = "A) Amateur";
			question[10][2] = "B) Intermediate";
			question[10][3] = "C) Advanced";
			
			question[11][0] = "11. What is your favourite cuisine?";
			question[11][1] = "A) African";
			question[11][2] = "B) American";
			question[11][3] = "C) Asian";
			question[11][4] = "D) Italian";
			
			int i = 0;
			String response = "";
			do {
				response = askQuestion(question[i]);
				i++;
			} 
			while(i < question.length);
			
		}
}

