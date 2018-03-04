import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedditTest3 {

	public static void main(String[] args) {
		
		File fileName;
		BufferedReader readFile = null;
		String line = null;
		char blankSpace = 0;
		float total = 0;
		float counter = 0;
		String regEx = null;
		Pattern analyzeRegEx;
		Matcher stringFinder;
		
		Scanner input = new Scanner(System.in);

		try {

			System.out.println("This class can scan a .txt file (particularly redditPosts.txt and redditAuthors.txt)"
					+ " for an inputted keyword.");
			
			/* System.out.println("Input the filename (with extension) of the file that you would like to parse."); */
			fileName = new File("redditPosts.txt");

			FileReader importFile = new FileReader(fileName);
			System.out.println(fileName.toString() + " has been accessed.");

			readFile = new BufferedReader(importFile);
			System.out.println(fileName.toString() + " is ready to be parsed.");
			
			/* System.out.println("Which word would you like to search for?");
			word = input.nextLine(); */
			regEx = "(russia)([a-z]*)";

			// Skips first line
			readFile.readLine();

			while ((line = readFile.readLine()) != null) {

				for (int i = 0; i < line.length(); i++) {

					blankSpace = line.charAt(i);

					if (blankSpace == ' ') {

						total = total + 1;

					}

				}
			
			analyzeRegEx = Pattern.compile(regEx);
			stringFinder = analyzeRegEx.matcher(line);
			
			while (stringFinder.find()) {
				
				counter = counter + 1;
				
				}

			}

		}
		
		catch (Exception ex) {
			
			System.out.println(ex.toString());
			System.out.println("The file could not be found.");

		}

		input.close();

		System.out.println(counter);
		System.out.println(total);
	
	}
	
}
	