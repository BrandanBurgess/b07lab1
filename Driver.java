import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Driver {
	public static void main(String [] args) {

		double[] coeff1 = {5,7};
		int[] pow1 = {0, 2};
		Polynomial poly1 = new Polynomial(coeff1, pow1);

		double[] coeff2 = {-21, 4, 1};
		int[] pow2 = {0, 1, 2};
		Polynomial poly2 = new Polynomial(coeff2, pow2);

		double[] coeff3 = {1, 2, 3 ,4};
		int[] pow3 = {1, 2, 3, 4};
		Polynomial poly3 = new Polynomial(coeff3, pow3);


		System.out.println("Tests Below");


		System.out.println("<><><><><><><><><><><><><><><><><>");
		System.out.println("Test Add");
		System.out.println("Adding Poly1 to Poly1");
		Polynomial sum11 = poly1.add(poly1);
		System.out.println("Expected: [10.0, 14.0] and [0, 2]");
		System.out.println("Received: " + Arrays.toString(sum11.coefficients) + " and " + Arrays.toString(sum11.exponents));
		System.out.println();

		

		System.out.println("<><><><><><><><><><><><><><><><><>");
		System.out.println("Test Multiply");

		Polynomial mult1 =  poly1.multiply(poly1);
		System.out.println("Expected: [25.0, 70.0, 49.0] and [0, 2, 4]");
		System.out.println("Received: " + Arrays.toString(mult1.coefficients) + " and " + Arrays.toString(mult1.exponents));
		System.out.println();


		Polynomial mult2 = poly1.multiply(poly2);
		System.out.println("Expected: [-105.0, 20.0, -112.0, 28.0, 49.0] and [0, 1, 2, 3, 4]");
		System.out.println("Received: " +  Arrays.toString(mult2.coefficients) + " and " + Arrays.toString(mult2.exponents));
		System.out.println();




		System.out.println("<><><><><><><><><><><><><><><><><>");

		System.out.println("Test Evaluate");

		System.out.println("Evaluating Poly1 at 5");
		System.out.println("Expected: 180.0");
		System.out.println("Received: " +  poly1.evaluate(5));
		System.out.println();

		System.out.println("Evaluating Poly3 at 6");
		System.out.println("Expected: 5910.0");
		System.out.println("Received: " +  poly3.evaluate(6));
		System.out.println();



		System.out.println("<><><><><><><><><><><><><><><><><>");
		System.out.println("Test Has Root");

		System.out.println("Polynomial 2 Root at 3");
		System.out.println("Expected: true");
		System.out.println("Received: " +  poly2.hasRoot(3));
		System.out.println();

		System.out.println("Polynomial 1 Root at 1.38");
		System.out.println("Expected: false");
		System.out.println("Received: " +  poly1.hasRoot(1.38));
		System.out.println();



		System.out.println("<><><><><><><><><><><><><><><><><>");
		System.out.println("Testing Read file");

		System.out.println("Reading file at input.txt");
		File f1 = new File("input.txt");
		try 
		{
			Polynomial p1 = new Polynomial(f1);
			System.out.println("Expected: [6.0, 8.0, 3.0, 1.0] and [0, 4, 5, 6]");
			System.out.println("Received: " +  Arrays.toString(p1.coefficients) + " and " + Arrays.toString(p1.exponents));
			System.out.println();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		
		System.out.println("<><><><><><><><><><><><><><><><><>");
		System.out.println("Testing Save to File");

		System.out.println("Saving polynomial 3 to a file output1.txt");
		try
		{
			poly3.saveToFile("output1.txt");
			System.out.println("Successfully written to output1.txt\n");
		}
		catch (Exception e) 
		{
			System.err.println("Error saving to the: " + e.getMessage());
		}


		System.out.println("Reading output1.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader("output1.txt"))) 
		{
			String line = reader.readLine();
			while (line != null) 
			{
				System.out.println("Got: " + line);
				line = reader.readLine();
			}
		} 
		catch (IOException e) 
		{
			System.err.println("Error reading the file: " + e.getMessage());
		}
		System.out.println("Expected: 1.0x1+2.0x2+3.0x3+4.0x4");
	}
}