import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class RedditTest7 {
	
	public static int repeatedStrFinder(String response) {
		
		File fileName;
		BufferedReader readFile = null;
		String line = null;
		int fileEntries = 6467;
		int i = 0;
		String[] fileArray = new String[fileEntries];
		int strCount = 0;
		boolean outcome = false;
		
		try {
			
			fileName = new File(response);

			FileReader importFile = new FileReader(fileName);
			System.out.println(fileName.toString() + " has been accessed.");

			readFile = new BufferedReader(importFile);
			System.out.println(fileName.toString() + " is ready to be parsed.");

			readFile.readLine();

			while ((line = readFile.readLine()) != null) {

				fileArray[i] = line;
				i++;

			}

			for (int j = 0; j < fileEntries - 1; j++) {
				
				if (fileArray[j] == fileArray[j + 1]) {
						
						outcome = true;
						strCount++;

					}
				
				else {
					
					outcome = false;
					
				}
				
			}
			
		}
		
		catch (Exception ex) {
			
			System.out.println("The file could not be found.");
			
		}
		
		return(strCount);
		
	}

	public static void main(String[] args) {
		
		System.out.println("This is a cleaner version of RedditTest5.java.");
		
		String response = "redditPosts.txt";
		int counter = repeatedStrFinder(response);
		System.out.println(Integer.toString(counter));

	}

}
