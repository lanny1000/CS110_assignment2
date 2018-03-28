import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedditData {

	public static void main(String[] args) {

		String response = null;
		String[] results = new String[7];
		Scanner input = new Scanner(System.in);
		boolean program = true;

		while (program) {

			System.out.println("Would you like to search through the file containing the posts "
					+ "or the file containing the links (URLs)? \n(respond with: post or url)");
			response = input.nextLine();

			if (response.equalsIgnoreCase("post")) {

				results = subStrInPostsFinder();

			}

			else if (response.equalsIgnoreCase("url")) {

				results = subStrInURLsFinder();

			}

			for (int i = 0; i < 7; i++) {

				System.out.println(results[i]);

			}

			System.out.println("Would you like to search again?");
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

	// This method accesses the five .txt files, storing there respective bufferedreaders in an array 
	// and outputting the array, called readFileArray
	// Individual bufferedreaders are accessed by calling their index in readFileArray
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
	
	// This methods searches the file redditPosts.txt for one or multiple substrings (converted to
	// a regular expression) specified by the user.
	public static String[] subStrInPostsFinder() {

		String line = null;
		String[] lineArray = new String[6467];
		String[] userArray = new String[6467];
		String[] upvoteArray = new String[6467];
		String[] commentArray = new String[6467];
		char blankSpace = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int m = 0;
		int userCounter = 1;
		int upvoteCounter = 0;
		int commentCounter = 0;
		int wordArrayLength = 0;
		float counter = 0;
		float total = 0;
		float percentage = 0;
		Pattern analyzeRegEx;
		Matcher stringFinder;

		Scanner input = new Scanner(System.in);

		try {

			total = 0;
			counter = 0;
			
			// Access files
			BufferedReader[] readFiles = accessFiles();

			System.out.println("How many words would you like to search for?");
			wordArrayLength = input.nextInt();

			String[] wordArray = new String[wordArrayLength];
			
			// Inputting and assigning the specified words (substrings)
			for (int i = 0; i < wordArrayLength; i++) {

				if (i == 0) {

					System.out.printf("Input a word for index %d of the list.\n", i + 1);
					input.nextLine();
					wordArray[i] = input.nextLine();

				}

				else {

					System.out.printf("Input a word for index %d of the list.\n", i + 1);
					wordArray[i] = input.nextLine();

				}

			}

			// Skips first lines
			readFiles[1].readLine();
			readFiles[0].readLine();
			readFiles[3].readLine();
			
			// The following while loops assign strings in each text file to an entry in an array
			while ((line = readFiles[0].readLine()) != null) {

				userArray[j] = line;
				j++;

			}

			while ((line = readFiles[3].readLine()) != null) {

				upvoteArray[l] = line;
				l++;

			}

			while ((line = readFiles[2].readLine()) != null) {

				commentArray[m] = line;
				m++;

			}

			while ((line = readFiles[1].readLine()) != null) {

				lineArray[k] = line;
				
				// The following for loop and if statement count the total number of words found in the file
				for (m = 0; m < line.length(); m++) {

					blankSpace = line.charAt(m);

					if (blankSpace == ' ') {

						total = total + 1;

					}

				}

				if (line.charAt(line.length() - 1) != 0) {

					total = total + 1;

				}
				
				// Iterate the index of the array containing the entries of the post file
				k++;

				// The following for loop identifies a line containing the inputted substring and 
				// establishes a one-to-one mapping across the four arrays
				// It then prints the values contained within the linked array entries using concatenation
				// Counter summations for the number of comments and upvotes are also evaluated
				for (int i = 0; i < wordArrayLength; i++) {

					if (line.contains(wordArray[i])) {

						System.out.println(userCounter + ". ( User: " + userArray[k] + " ) ( Number of upvotes: "
								+ upvoteArray[k] + " ) ( Number of comments: " + commentArray[k] + " ) Post: " + line);
						userCounter++;
						upvoteCounter = Integer.parseInt(upvoteArray[k]) + upvoteCounter;
						commentCounter = Integer.parseInt(commentArray[k]) + commentCounter;

					}
					
					// The following statements create a regular expression from the inputted word(s)
					analyzeRegEx = Pattern.compile(wordArray[i] + "([a-z]*)");
					stringFinder = analyzeRegEx.matcher(line);

					while (stringFinder.find()) {

						counter = counter + 1;

					}

				}

			}

		}

		catch (Exception ex) {

			System.out.println("The file could not be found.");
			System.out.println(ex.toString());

		}

		percentage = (counter / total) * 100;
		
		// A string array containing the data is returned at the end of the method
		String[] dataInfoArray = {
				"\nThe preceding list contains the users (along with the number of "
						+ "upvotes and comments) whose posts contained the inputted word(s).",
				"Total number of words found in redditPosts.txt: " + total,
				"Total number of instances of the inputted word(s): " + counter,
				"Percentage of instances of the specified word(s) out of the total number of words found: " + percentage
						+ "%",
				"Total number of users who posted the inputted word(s): " + userCounter,
				"Total number of upvotes received by these posts: " + upvoteCounter,
				"Total number of comments received by these posts: " + commentCounter };

		return dataInfoArray;

	}
	
	// This methods searches the file redditURLs.txt for one or multiple substrings (converted to
	// a regular expression) specified by the user.
	public static String[] subStrInURLsFinder() {

		String line = null;
		String[] lineArray = new String[6467];
		String[] userArray = new String[6467];
		String[] upvoteArray = new String[6467];
		String[] commentArray = new String[6467];
		char blankSpace = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int m = 0;
		int userCounter = 1;
		int upvoteCounter = 0;
		int commentCounter = 0;
		int wordArrayLength = 0;
		float counter = 0;
		float total = 0;
		float percentage = 0;
		Pattern analyzeRegEx;
		Matcher stringFinder;

		Scanner input = new Scanner(System.in);

		try {

			total = 0;
			counter = 0;

			BufferedReader[] readFiles = accessFiles();

			System.out.println("How many words would you like to search for?");
			wordArrayLength = input.nextInt();

			String[] wordArray = new String[wordArrayLength];
			
			// Inputting and assigning the specified words (substrings)
			for (int i = 0; i < wordArrayLength; i++) {

				if (i == 0) {

					System.out.printf("Input a word for index %d of the list.\n", i + 1);
					input.nextLine();
					wordArray[i] = input.nextLine();

				}

				else {

					System.out.printf("Input a word for index %d of the list.\n", i + 1);
					wordArray[i] = input.nextLine();

				}

			}

			// Skips first lines
			readFiles[4].readLine();
			readFiles[0].readLine();
			readFiles[3].readLine();

			while ((line = readFiles[0].readLine()) != null) {

				userArray[j] = line;
				j++;

			}
			
			// The following while loops assign strings in each text file to an entry in an array
			while ((line = readFiles[3].readLine()) != null) {

				upvoteArray[l] = line;
				l++;

			}

			while ((line = readFiles[2].readLine()) != null) {

				commentArray[m] = line;
				m++;

			}

			while ((line = readFiles[4].readLine()) != null) {

				lineArray[k] = line;
				
				// The following counts the total number of words found in the URL file
				for (m = 0; m < line.length(); m++) {

					blankSpace = line.charAt(m);

					if (blankSpace == ' ') {

						total = total + 1;

					}

				}

				if (line.charAt(line.length() - 1) != 0) {

					total = total + 1;

				}
				
				// Iterate the index of the array containing the entries of the URL file
				k++;
				
				// The following for loop identifies a line containing the inputted substring and 
				// establishes a one-to-one mapping across the four arrays
				// It then prints the values contained within the linked array entries using concatenation
				// Counter summations for the number of comments and upvotes are also evaluated
				for (int i = 0; i < wordArrayLength; i++) {

					if (line.contains(wordArray[i])) {

						System.out.println(userCounter + ". ( User: " + userArray[k] + " ) ( Number of upvotes: "
								+ upvoteArray[k] + " ) ( Number of comments: " + commentArray[k] + " ) URL: " + line);
						userCounter++;
						upvoteCounter = Integer.parseInt(upvoteArray[k]) + upvoteCounter;
						commentCounter = Integer.parseInt(commentArray[k]) + commentCounter;

					}
					
					// The following statements create a regular expression from the inputted word(s)
					analyzeRegEx = Pattern.compile(wordArray[i] + "([a-z]*)");
					stringFinder = analyzeRegEx.matcher(line);

					while (stringFinder.find()) {

						counter = counter + 1;

					}

				}

			}

		}

		catch (Exception ex) {

			System.out.println("The file could not be found.");
			System.out.println(ex.toString());

		}

		percentage = (counter / total) * 100;
		
		// A string array containing the data is returned at the end of the method
		String[] dataInfoArray = {
				"\nThe preceding list contains the users (along with the number of "
						+ "upvotes and comments) whose posts contained the inputted word(s).",
				"Total number of words found in redditURLs.txt: " + total,
				"Total number of instances of the inputted word(s): " + counter,
				"Percentage of instances of the specified word(s) out of the total number of words found: " + percentage
						+ "%",
				"Total number of users who posted the inputted word(s): " + userCounter,
				"Total number of upvotes received by these URLs: " + upvoteCounter,
				"Total number of comments received by these URLs: " + commentCounter };

		return dataInfoArray;

	}

}