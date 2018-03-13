import java.io.*;
import java.util.Scanner;

public class RedditTest {

	public static void main(String[] args) {

		boolean program = true;
		String fileName = null;
		String line = null;
		String keyword = null;
		String response = null;
		BufferedReader readFile = null;

		try (Scanner input = new Scanner(System.in)) {

			System.out.println("Input the filename (with extension) of the file that you would like to parse.");
			fileName = input.nextLine();

			try {

				while (program) {
					
					int counter = 0;

					FileReader importFile = new FileReader(fileName);
					System.out.println(fileName + " has been accessed.");

					readFile = new BufferedReader(importFile);
					System.out.println(fileName + " is ready to be parsed.");

					readFile.readLine();
					line = readFile.readLine();

					System.out.println("What keyword would you like to search for?");
					keyword = input.nextLine();

					while ((line = readFile.readLine()) != null) {
						
						if (line.contains(keyword)) {

							counter = counter + 1;

						}

					}

					if (counter == 1) {

						System.out.println("There is 1 instance of " + keyword + ".");

					}

					else if (counter == 0) {

						System.out.println("There are 0 instances of " + keyword + ".");

					}

					else {

						System.out.println("There are " + counter + " instances of: " + keyword + ".");

					}

					System.out.println("Would you like to search the same file again? (yes or no)");
					response = input.nextLine();

					if (response.equalsIgnoreCase("yes")) {

						program = true;

					}

					else {

						program = false;

					}

				}
			}

			catch (Exception ex) {

				System.out.println("The file could not be found.");
				program = false;

			}

		}
		try {
			readFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}