import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedditTest10 {

	public static void main(String[] args) {

		String fileInput = null;
		String response = null;
		String[] results = new String[6];
		Scanner input = new Scanner(System.in);
		boolean program = true;

		System.out.println("Input the filename (with extension) of the file that you would like to parse.");
		fileInput = input.nextLine();

		while (program) {
			
			results = wordFinder(fileInput);

			for (int i = 0; i < 6; i++) {

				System.out.println(results[i]);

			}

			System.out.println("Would you like to search the same file again? (yes or no)"); 
			response = input.nextLine();

			if (response.equalsIgnoreCase("yes")) {

				program = true;

			}

			else if (response.equalsIgnoreCase("no")) {

				program = false;

			}

		}
		
		input.close();

	}

	public static String[] wordFinder(String fileInput) {

		File fileName = null;
		File userFile = new File("redditAuthors.txt");
		File upvoteFile = new File("redditUpvotes.txt");
		BufferedReader readFile = null;
		BufferedReader readUserFile = null;
		BufferedReader readUpvoteFile = null;
		String response = null;
		String word = null;
		String line = null;
		String regEx = null;
		String[] lineArray = new String[6467];
		String[] userArray = new String[6467];
		String[] upvoteArray = new String[6467];
		char blankSpace = 0;
		Pattern analyzeRegEx;
		Matcher stringFinder;
		int j = 0;
		int k = 0;
		int l = 0;
		int userCounter = 1;
		int upvoteCounter = 0;
		int wordArrayLength = 0;
		float counter = 0;
		float total = 0;
		float percentage = 0;

		Scanner input = new Scanner(System.in);

		try {

			total = 0;
			counter = 0;

			fileName = new File(fileInput);

			FileReader importFile = new FileReader(fileName);
			FileReader importUserFile = new FileReader(userFile);
			FileReader importUpvoteFile = new FileReader(upvoteFile);
			
			System.out.println(fileName.toString() + ", redditUpvotes.txt, and redditAuthors.txt have been accessed.");

			readFile = new BufferedReader(importFile);
			readUserFile = new BufferedReader(importUserFile);
			readUpvoteFile = new BufferedReader(importUpvoteFile);
			System.out.println(fileName.toString() + ", redditUpvotes.txt, and redditAuthors.txt are ready to be parsed.");

			System.out.println(
					"Would you like to search for a single word or a list of words? (respond with: single or list)");
			response = input.nextLine();

			if (response.equalsIgnoreCase("single")) {

				System.out.println("Which word would you like to search for?");
				word = input.nextLine();
				regEx = word + "([a-z]*)";

				// Skips first lines
				readFile.readLine();
				readUserFile.readLine();
				readUpvoteFile.readLine();
				
				while ((line = readUserFile.readLine()) != null) {
					
					userArray[j] = line;
					j++;
					
				}
				
				while ((line = readUpvoteFile.readLine()) != null) {
					
					upvoteArray[l] = line;
					l++;
					
				}

				while ((line = readFile.readLine()) != null) {
					
					lineArray[k] = line;

					for (int i = 0; i < line.length(); i++) {

						blankSpace = line.charAt(i);

						if (blankSpace == ' ') {

							total = total + 1;

						}

					}

					if (line.charAt(line.length() - 1) != 0) {

						total = total + 1;

					}
					
					if (line.contains(word)) {
						
						System.out.println(userCounter + ". ( User: " + userArray[k] + " ) Number of upvotes: " + upvoteArray[k] + ", " + line);
						userCounter++;
						upvoteCounter = Integer.parseInt(upvoteArray[k]) + upvoteCounter;
						
					}
					
					k++;

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

				// Skips first lines
				readFile.readLine();
				readUserFile.readLine();
				readUpvoteFile.readLine();
				
				while ((line = readUserFile.readLine()) != null) {
					
					userArray[j] = line;
					j++;
					
				}
				
				while ((line = readUpvoteFile.readLine()) != null) {
					
					upvoteArray[l] = line;
					l++;
					
				}

				while ((line = readFile.readLine()) != null) {
					
					lineArray[k] = line;

					for (int m = 0; m < line.length(); m++) {

						blankSpace = line.charAt(m);

						if (blankSpace == ' ') {

							total = total + 1;

						}

					}

					if (line.charAt(line.length() - 1) != 0) {

						total = total + 1;

					}
					
					k++;

					for (int i = 0; i < wordArrayLength; i++) {
						
						if (line.contains(wordArray[i])) {
							
							System.out.println(userCounter + ". ( User: " + userArray[k] + " ) Number of upvotes: " + upvoteArray[k] + ", " + line);
							userCounter++;
							upvoteCounter = Integer.parseInt(upvoteArray[k]) + upvoteCounter;
							
						}

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

			System.out.println("The file could not be found.");
			System.out.println(ex.toString());

		}
		
		percentage = (counter / total) * 100;
		
		String[] dataInfoArray = { "\nThe preceding list contains the users whose posts contained the inputted word(s).",
				"Total number of words found in " + fileName + ": " + total,
				"Total number of instances of the inputted word(s): " + counter,
				"Percentage of instances of the specified word(s) out of the total number of words found: " + percentage + "%",
				"Total number of users who posted this: " + userCounter,
				"Total number of upvotes recieved by these posts: " + upvoteCounter };

		return dataInfoArray;

	}

}