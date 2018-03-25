import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedditTest12 {

	public static void main(String[] args) {
		
		String response = null;
		String[] results = new String[6];
		Scanner input = new Scanner(System.in);
		boolean program = true;

		while (program) {
			
			results = wordFinder();

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
	
	public static BufferedReader[] accessFiles() {

		BufferedReader[] readFileArray = new BufferedReader[5];

		try {

			File[] fileArray = { new File("redditAuthors.txt"), new File("redditPosts.txt"),
					new File("redditCommentsCount.txt"), new File("redditUpvotes.txt"), new File("redditURLs.txt") };

			FileReader[] importFileArray = { new FileReader(fileArray[0]), new FileReader(fileArray[1]),
					new FileReader(fileArray[2]), new FileReader(fileArray[3]), new FileReader(fileArray[4]) };

			System.out.println("The files have been accessed.");

			for (int i = 0; i < 5; i++) {

				readFileArray[i] = new BufferedReader(importFileArray[i]);

			}

			System.out.println("The files are ready to be parsed.");

		}

		catch (Exception ex) {

			System.out.println("The files could not be found.");

		}

		return readFileArray;

	}
	
	public static String[] wordFinder() {

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
			
			BufferedReader[] readFiles = accessFiles();

			System.out.println(
					"Would you like to search for a single word or a list of words? (respond with: single or list)");
			response = input.nextLine();

			if (response.equalsIgnoreCase("single")) {

				System.out.println("Which word would you like to search for?");
				word = input.nextLine();
				regEx = word + "([a-z]*)";

				// Skips first lines
				readFiles[1].readLine();
				readFiles[0].readLine();
				readFiles[3].readLine();
				
				while ((line = readFiles[0].readLine()) != null) {
					
					userArray[j] = line;
					j++;
					
				}
				
				while ((line = readFiles[3].readLine()) != null) {
					
					upvoteArray[l] = line;
					l++;
					
				}

				while ((line = readFiles[1].readLine()) != null) {
					
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
				readFiles[1].readLine();
				readFiles[0].readLine();
				readFiles[3].readLine();
				
				while ((line = readFiles[0].readLine()) != null) {
					
					userArray[j] = line;
					j++;
					
				}
				
				while ((line = readFiles[3].readLine()) != null) {
					
					upvoteArray[l] = line;
					l++;
					
				}

				while ((line = readFiles[1].readLine()) != null) {
					
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

		}
		
		percentage = (counter / total) * 100;
		
		String[] dataInfoArray = { "\nThe preceding list contains the users whose posts contained the inputted word(s).",
				"Total number of words found in redditPosts.txt: " + total,
				"Total number of instances of the inputted word(s): " + counter,
				"Percentage of instances of the specified word(s) out of the total number of words found: " + percentage + "%",
				"Total number of users who posted this: " + userCounter,
				"Total number of upvotes recieved by these posts: " + upvoteCounter };

		return dataInfoArray;

	}

}
