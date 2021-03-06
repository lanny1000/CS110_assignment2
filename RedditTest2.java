import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RedditTest2 {

	public static void main(String[] args) {

		File fileName;
		BufferedReader readFile = null;
		String line = null;
		float total = 0;
		float counter = 0;
		String pattern = null;
		char letter = 0;
		
		Scanner input = new Scanner(System.in);

		try {

			System.out.println("This class can scan a .txt file (particularly redditPosts.txt and redditAuthors.txt)"
					+ " for an inputted keyword.");
			System.out.println("Input the filename (with extension) of the file that you would like to parse.");
			fileName = new File(input.nextLine());

			FileReader importFile = new FileReader(fileName);
			System.out.println(fileName.toString() + " has been accessed.");

			readFile = new BufferedReader(importFile);
			System.out.println(fileName.toString() + " is ready to be parsed.");
			
			System.out.println("What keyword would you like to search for?");
			pattern = input.nextLine();

			// Skips first line
			readFile.readLine();

			while ((line = readFile.readLine()) != null) {

				if (line.contains("")) {

					for (int i = 0; i < line.length(); i++) {

						letter = line.charAt(i);

						if (letter == ' ') {

							total = total + 1;

						}

					}

				}
				
				/* if (line.toLowerCase().contains(pattern)) { */

					/* for (int i = 0; i < pattern.length(); i++) {

						if (line.toLowerCase().charAt(i) == pattern.charAt(0)) { */

							/* for (int j = 0; j < pattern.length(); j++) {

								if (line.toLowerCase().charAt(j) == pattern.charAt(j)) {

									counter = counter + 1;

								}

							} */

						// }

					// }
					
					if (line.indexOf(pattern) >= 0) {
				
						counter = counter + 1;
						
					}
					
				/* String[] lineContents = new String[line.length()];
				
					for (int i = 0; i < line.length(); i++) {
						
						lineContents[i] = line.substring(0, line.length());
						
							if (lineContents[i].contains(pattern)) {
							
								counter = counter + 1;
							
						}
						
					} */
			
			}

			// }

			System.out.println("Number of instances of the keyword: " + counter);
			System.out.println("Total number of words: " + total);
			float percentage = (counter / total) * 100;
			System.out.println("Percentage of instances found in the file: " + percentage + "%");
		
		}

		catch (Exception ex) {
			System.out.println(ex.toString());
			System.out.println("The file could not be found.");

		}

		input.close();

		try {
			readFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}