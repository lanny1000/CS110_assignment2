import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class RedditTest5 {

	public static void main(String[] args) {

		File fileName1;
		File fileName2;
		BufferedReader readFile1 = null;
		BufferedReader readFile2 = null;
		String line1 = null;
		String line2 = null;
		int fileEntries = 6465;
		int i = 0;
		int j = 0;
		String[] file1Array = new String[fileEntries];
		String[] file2Array = new String[fileEntries];
		int postCount = 0;
		// int uCount = 0;

		try {

			fileName1 = new File("redditAuthors.txt");
			fileName2 = new File("redditPosts.txt");

			FileReader importFile1 = new FileReader(fileName1);
			FileReader importFile2 = new FileReader(fileName2);
			System.out.println(fileName1.toString() + " and " + fileName2.toString() + " have been accessed.");

			readFile1 = new BufferedReader(importFile1);
			readFile2 = new BufferedReader(importFile2);
			System.out.println(fileName1.toString() + " and " + fileName2.toString() + " are ready to be parsed.");

			readFile1.readLine();
			readFile2.readLine();

			while ((line1 = readFile1.readLine()) != null) {

				file1Array[i] = line1;

				i++;

			}

			while ((line2 = readFile2.readLine()) != null) {

				file2Array[j] = line2;

				j++;

			}

			for (int k = 0; k < fileEntries - 1; k++) {

				if (file2Array[k] == file2Array[k + 1]) {
					
					postCount = postCount + 1;

					while (file2Array[k] == file2Array[k + 1]) {
						
						k++;

					}

					/*
					 * if (file1Array[k] != file1Array[k + 1]) {
					 * 
					 * uCount = uCount + 1;
					 * 
					 * }
					 */

				}

			}
			
			System.out.println("There are " + postCount + " repeated posts.");
			// System.out.println("They have been posted by " + uCount + " unique users.");


		}

		catch (Exception ex) {

			System.out.println("The files could not be found.");

		}

	}

}
