import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class RedditTest11 {

	public static void main(String[] args) {

		String line = null;

		BufferedReader[] readFiles = accessFiles();
		
		try {
			
			while ((line = readFiles[0].readLine()) != null) {
				
				System.out.println(line);
				
			}
			
		} 
		
		catch (Exception ex) {
			
			System.out.println("The files could not be found.");
			
		}

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
		
		catch(Exception ex) {
			
			System.out.println("The files could not be found.");
			
		}
		
		return readFileArray;
		
	}

}
