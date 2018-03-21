import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedditTest8 {

	public static void main(String[] args) {

		String response = null;
		String[] results = new String[2];
		Scanner inputMain = new Scanner(System.in);
		boolean program = true;

		System.out.println("This class can scan a .txt file (particularly redditPosts.txt and redditAuthors.txt)"
				+ " for an inputted keyword or a list of keywords.");

		System.out.println("Input the filename (with extension) of the file that you would like to parse.");
		response = inputMain.nextLine();

		while (program) {
			
			results = wordFinder(response);

			for (int i = 0; i < 2; i++) {

				System.out.println(results[i]);

			}

			System.out.println("Would you like to search the same file again? (yes or no)"); 
			response = inputMain.nextLine();

			if (response.equalsIgnoreCase("yes")) {

				program = true;

			}

			else if (response.equalsIgnoreCase("no")) {

				program = false;

			}

		}
		
		inputMain.close();

	}

	public static String[] wordFinder(String response) {

		File fileName = null;
		BufferedReader readFile = null;
		String word = null;
		String line = null;
		char blankSpace = 0;
		String regEx = null;
		Pattern analyzeRegEx;
		Matcher stringFinder;
		int counter = 0;
		int total = 0;
		int wordArrayLength = 0;

		Scanner input = new Scanner(System.in);

		try {

			total = 0;
			counter = 0;

			fileName = new File(response);

			FileReader importFile = new FileReader(fileName);
			System.out.println(fileName.toString() + " has been accessed.");

			readFile = new BufferedReader(importFile);
			System.out.println(fileName.toString() + " is ready to be parsed.");

			System.out.println(
					"Would you like to search for a single word or a list of words? (respond with: single or list)");
			response = input.nextLine();

			if (response.equalsIgnoreCase("single")) {

				System.out.println("Which word would you like to search for?");
				word = input.nextLine();
				regEx = word + "([a-z]*)";

				// Skips first line
				readFile.readLine();

				while ((line = readFile.readLine()) != null) {

					for (int i = 0; i < line.length(); i++) {

						blankSpace = line.charAt(i);

						if (blankSpace == ' ') {

							total = total + 1;

						}

					}

					if (line.charAt(line.length() - 1) != 0) {

						total = total + 1;

					}

					analyzeRegEx = Pattern.compile(regEx);
					stringFinder = analyzeRegEx.matcher(line);

					while (stringFinder.find()) {

						counter = counter + 1;

					}

				}

			}

			else if (response.equalsIgnoreCase("list")) {

				System.out.println("How many words would you like to search for?");
				wordArrayLength = input.nextInt();

				String[] wordArray = new String[wordArrayLength];

				for (int i = 0; i < wordArrayLength; i++) {
					
					if (i == 0) {

					System.out.printf("Input a word for index %d of the array.\n", i + 1);
					input.nextLine();
					wordArray[i] = input.nextLine();
					
					}
					
					else {
						
						System.out.printf("Input a word for index %d of the array.\n", i + 1);
						wordArray[i] = input.nextLine();
						
					}

				}

				// Skips first line
				readFile.readLine();

				while ((line = readFile.readLine()) != null) {

					for (int i = 0; i < line.length(); i++) {

						blankSpace = line.charAt(i);

						if (blankSpace == ' ') {

							total = total + 1;

						}

					}

					if (line.charAt(line.length() - 1) != 0) {

						total = total + 1;

					}

					for (int i = 0; i < wordArrayLength; i++) {

						analyzeRegEx = Pattern.compile(wordArray[i] + "([a-z]*)");
						stringFinder = analyzeRegEx.matcher(line);

						while (stringFinder.find()) {

							counter = counter + 1;

						}

					}

				}

			}

		}

		catch (Exception ex) {

			System.out.println(ex.toString());
			System.out.println("The file could not be found.");

		}

		//input.close();
		
		String[] dataInfoArray = { "Total number of words found in " + fileName.toString() + ": " + Integer.toString(total),
				"Number of instances of the inputted word(s): " + Integer.toString(counter) };

		return dataInfoArray;

	}

}
