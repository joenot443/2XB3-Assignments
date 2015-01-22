import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Testing {
	//Our testing class must throw FileNotFoundException should the file not exist.
	public static void main(String[] args) throws IOException {
		
		//Begin testing by reading from input.txt using PrintStream
		
		Scanner input = new Scanner(new File("./Data/input.txt"));
		//Read the first line in the file to find the integer which defines the # of cases
		
		int n = input.nextInt();
		
		//Must skip the first line
		input.nextLine();
		
		//Create an OutputStream and a PrintStream for our output file
		//The PrintStream will be passed along with our test function
		
		OutputStream os = new FileOutputStream("./Data/output.txt");
		PrintStream ps = new PrintStream(os);
		
		for (int i=0; i<n*4; i++){
		//Next, scan the first test case
		String line = input.nextLine();
		String[] stringParts = line.split(",");
		//We now have the inputs as a list of strings, they must be individually converted to doubles
		double[] doubleParts = new double[stringParts.length];
		//Convert to double using parseDouble
		for (int j = 0; j<doubleParts.length; j++){
			doubleParts[j] = Double.parseDouble(stringParts[j]);
			}
		
		//Now double parts contains the doubles we wish to pass to testCase
		//We will use modulo to determine which test type we want to use
		//1: GCD, 2: Rotate, 3:Voltage, 4:Current
		testCase(doubleParts, i%4 + 1, ps);

		}
		//Now we will test the remaining, untested functions (polarForm, squareRoot, conjugate, and equals) 
		additionalTests(ps);
	}
	public static void testCase(double[] inputs, int type, PrintStream ps) throws FileNotFoundException{
		//First, specify which file we will be printing to
		
		
		//Next determine what method is being tested
		switch (type) {
		//GCD Test
			case 1:	
			Complex a = new Complex(inputs[0], inputs[1]);
			Complex b = new Complex(inputs[2], inputs[3]);
			//Perform the test
			Complex gcd = Complex.gcd(a, b); 
			//Output a string
			String out = "The greatest common divisor for complex number "+a.a()+" + "+a.b()+"i"+" + "+b.a()+" + "+b.b()+"i is "+gcd.a()+" + "+gcd.b();
			//Print it to our file
			ps.println(out);
			break;
		//Rotate test 
			case 2:
			Complex a2 = new Complex(inputs[0], inputs[1]);
			int angle = (int) inputs[2];
			Complex rotate = Complex.rotate(a2, angle);
			String out2 = "The resulting complex number for rotating " + a2.a()+" + "+a2.b()+"i "+angle+" degrees"+" is "+rotate.a()+" + "+rotate.b()+"i";
			ps.println(out2);
			break;
		//Voltage test
			case 3:
			Complex a3 = new Complex(inputs[0], inputs[1]);
			Complex b3 = new Complex(inputs[2], inputs[3]);
			Complex voltage = Complex.get_voltage(a3, b3);
			String out3 = "The resulting voltage for a current of "+a3.a()+" + "+a3.b()+"i"+" and an impedance of "+b3.a()+" + "+b3.b()+"i is "+voltage.a()+" + "+voltage.b();
			ps.println(out3);
			break;
		//Current test 
			case 4:
			Complex a4 = new Complex(inputs[0], inputs[1]);
			Complex b4 = new Complex(inputs[2], inputs[3]);
			Complex current = Complex.get_current(a4, b4);
			String out4 = "The resulting current for a voltage of "+a4.a()+" + "+a4.b()+"i"+" and an impedance of "+b4.a()+" + "+b4.b()+"i is "+current.a()+" + "+current.b();
			ps.println(out4);
			break;
		}
		return;
	}
	
	public static void additionalTests(PrintStream ps){
		
		//Test cases for polar form
		//The test cases are listed as a double array and are iterated over so as to not duplicate code
		double[] polarTests = new double[] {1,4,-2,5,22,5,435,1523,-22.5,412,22,534,-2,-33,42452,-93.527,0.29,-244,765.25383,2332}; 
		//polarForm test
		for (int i=0; i < 10; i++){
			//Create a complex number to test on
			Complex a = new Complex(polarTests[i*2], polarTests[i*2+1]);
			//Find its polar form
			double[] polarForm = a.polarForm(); 
			//Print out the resulting statement to our output file
			String out = "The polar form for complex number "+a.a()+" + "+a.b()+"i"+" is a length of "+polarForm[0]+" and an angle of " +polarForm[1]+" rads.";
			ps.println(out);
		}
		
		//Test cases for squareRoot
		double[] squareRootTests = new double[] {52,243,663,-34,134,-244,-352.6,-344,928475,718.32432,83258.3,-343,5532,95320,-423.42,43253,22,53,13,22,32};
		for (int i=0; i < 10; i++){
			Complex a = new Complex(squareRootTests[i*2], squareRootTests[(i*2)+1]);
			Complex sqr = a.squareRoot(); 
			String out = "The square root of complex number "+a.a()+" + "+a.b()+"i"+" is "+sqr.a()+" + " +sqr.b()+"i.";
			ps.println(out);
		}
		//Test cases for conjugate
		double[] conjugateTests = new double[] {4225,5123,22.42,534.42,434,5153,43.53,523,775,45.5,364,22,1,2,3,4,0,0,343.53,63};
		for (int i=0; i < 10; i++){
			Complex a = new Complex(conjugateTests[i*2], conjugateTests[i*2+1]);
			Complex conj = a.conjugate(); 
			String out = "The conjugate of complex number "+a.a()+" + "+a.b()+"i"+" is "+conj.a()+" + " +conj.b()+"i.";
			ps.println(out);
		}
		//Test cases for equals
		double[] equalsTest = new double[] {1,1,1,1,2,2,2,2,-242,452,55,2.423,33,22,33,22,152,152,325,423,99,991,99,991,534,123,534,123,0,0,0,0,1235,1235,1235,1235,0.001,0.002,0.001,0.002};
		for (int i=0; i < 10; i++){
			Complex a = new Complex(equalsTest[i*4], equalsTest[i*4+1]);
			Complex b = new Complex(equalsTest[i*4+2], equalsTest[i*4+3]);
			boolean equals = a.equals(b); 
			String out = "The statement that complex number "+a.a()+" + "+a.b()+"i"+" is equal to "+b.a()+" + "+b.b()+"i is "+equals;
			ps.println(out);
		}
	}

}
