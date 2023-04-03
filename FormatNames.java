import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 * Student Number: 3060115
 *
 */
public class FormatNames {
	 /**
	 *
	 * @param args The program (command line) argument list
	 *             args[0] = The input filename
	 *             args[1] = The output filename that'll be created
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {

		// Setting the file name(input) and file name(output).
		//String inputFileName = "input.txt"; 
		File inputFile = new File(args[0]);
		if(inputFile.exists() ==false || inputFile.isDirectory() == true) { 
			System.out.println("Please provide a valid input file name.");
			return;
		}

		//String outputFileName = "formatted.txt"; 
		File outputFile = new File (args[1]);
		if(outputFile.exists() ==true && outputFile.isDirectory() == true) { 
			System.out.println("Please provide a valid output file name: A directory with same name exist");
			return;
		}

		// Setting up a new Scanner to read the input file.
		Scanner sc = new Scanner(inputFile);

		String delims="\\s+";  // delimiter for white spaces
		String line = " ";
		String [] tokens;
		// processing line by line
		while (sc.hasNextLine())
		{
			line += sc.nextLine() + "\n";
		}
		tokens = line.split(delims);

		StringBuilder sb = new StringBuilder();
		boolean newSentence = true;                // flag

		for (int i = 0; i < tokens.length; i++) {
			String word = tokens[i];

			if (word.length() == 0) {
				continue; 
			}
			// formatting the dates using RegX for digits (\d+)
			if (word.matches("\\d+")) {
				String day = word.substring(0, 2);
				String month = word.substring(2, 4);
				String year = word.substring(4);
				word = "  " + day +"/" + month + "/" + year + "\n";                
			}
			
			// formatting the names 
			char firstChar = word.charAt(0);
			// Capitalizing the first character of each word 
			if (newSentence && Character.isLowerCase(firstChar))
			{
				firstChar = Character.toUpperCase(firstChar);
			}
			sb.append(firstChar);
			
			// appending the rest of the lowercase letters to the capitalized letter
			if (word.length() > 1) {
				sb.append(word.substring(1).toLowerCase()); 
			}
			sb.append(" ");
		}
		// output to console
		// System.out.println(sb.toString().trim());
				
		// Setting up a new PrintWriter to write the output file.
		PrintWriter printWriter = new PrintWriter (outputFile);
		printWriter.print(sb);
		printWriter.close();
		
	} // main

} // FormatNames
