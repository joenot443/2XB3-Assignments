package sort;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Output {
	
	public static String[][] insertTests;
	public static String[][] comparableInsertTests;
	public static String[][] binaryInsertTests;
	
	public static String[][] mergeTests;
	public static String[][] heapTests;
	
	public static void outputText() throws FileNotFoundException{
		
		//First we'll create String[][] of each line's worth of test cases
		
		String[][] line1Tests = {insertTests[0], comparableInsertTests[0], binaryInsertTests[0], mergeTests[0], heapTests[0]};
		String[][] line2Tests = {insertTests[1], comparableInsertTests[1], binaryInsertTests[1], mergeTests[1], heapTests[1]};
		String[][] line3Tests = {insertTests[2], comparableInsertTests[2], binaryInsertTests[2], mergeTests[2], heapTests[2]};

		//Sort the output tests using standard insertion sort
		
		sortOutputTests(line1Tests);
		sortOutputTests(line2Tests);
		sortOutputTests(line3Tests);
		
		//Now create an output line for each set of tests
		//Iterate through both dimensions of the array, adding each element to our output string at a time
		
		String line1OutputString = "16";
		String line2OutputString = "256";
		String line3OutputString = "4096";

		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 2; j++){				
				line1OutputString = line1OutputString + "," + line1Tests[i][j]+"s";		
				line2OutputString = line2OutputString + "," + line2Tests[i][j]+"s";	
				line3OutputString = line3OutputString + "," + line3Tests[i][j]+"s";
			}
		}
		//Create an OutputStream and a PrintStream for outputting our data to a text file
		OutputStream os = new FileOutputStream("./data/a2_output.txt");
		PrintStream ps = new PrintStream(os);
		
		//Print the outputs to each line
		ps.println(line1OutputString);
		ps.println(line2OutputString);
		ps.println(line3OutputString);

	}
	
	public static void sortOutputTests (String[][] x) {
		//First find the length of the array to be sorted
		
		int N = x.length;
		//Iterate through this array
		for (int i = 0; i < N; i++)
		{
			//Loop through the array, moving elements back until a smaller element is found
			for (int j = i; j > 0 && Double.parseDouble(x[j][1]) < Double.parseDouble(x[j-1][1]); j--){
				//Move the element back in the array
				exchange(x, j, j-1);
			}
		}
	}
	
	//Method to exchange elements in a String[][]
	public static void exchange(String[][] x, int i, int j){
		String[] a = x[i];
		x[i] = x[j];
		x[j] = a;
	}
}
