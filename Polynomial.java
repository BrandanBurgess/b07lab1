import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;

public class Polynomial{
    double coefficients [];
    int exponents [];


    public Polynomial(){
        this.coefficients = new double[] {0};
        this.exponents = new int[] {0};

    }

    public Polynomial(double[] sample, int[] sample2){
        this.coefficients = sample;
        this.exponents = sample2;
    }

    public Polynomial(File polyfile) throws IOException {
		
        BufferedReader read = new BufferedReader(new FileReader(polyfile));
		
        String polynomial = read.readLine(); //Given that polynomial occupies one line in file..
		read.close();

		String[] poly_terms;
		poly_terms = polynomial.split("(?=[+-])");
        
        int term_length = poly_terms.length;


        int[] new_pow = new int[poly_terms.length];
		double[] new_coef = new double[poly_terms.length];

		for (int j = 0; j < term_length; j++) {
			String term_poly = poly_terms[j];
			int splitter = term_poly.indexOf('x');

			if (splitter != -1) {
                new_pow[j] = Integer.parseInt(term_poly.substring(splitter + 1));
                new_coef[j] = Double.parseDouble(term_poly.substring(0, splitter));
			} 

            else {
				new_pow[j] = 0;
                new_coef[j] = Double.parseDouble(term_poly);
				
			}
		}

        this.exponents = new_pow;
		this.coefficients = new_coef;
	}

    public Polynomial add(Polynomial sample){

    
        int maxL = this.coefficients.length + sample.coefficients.length;
		double[] new_coefficients = new double[maxL];
		int[] new_exponents = new int[maxL];


        int i = 0;
        int p = 0; // k
        int q = 0; // j

		while (q < sample.coefficients.length && i < coefficients.length) {
			if (this.exponents[i] > sample.exponents[q]) {
				new_coefficients[p] = sample.coefficients[q];
				new_exponents[p] = sample.exponents[q];
				q++;
			}

            else if (this.exponents[i] < sample.exponents[q]) {
				new_coefficients[p] = this.coefficients[i];
				new_exponents[p] = this.exponents[i];
				i++;
			}

            else {
				new_coefficients[p] = this.coefficients[i] + sample.coefficients[q];
				new_exponents[p] = this.exponents[i];
				i++;
				q++;
			}
			p++;
		}

		while (i < coefficients.length) {
			new_coefficients[p] = this.coefficients[i];
			new_exponents[p] = this.exponents[i];
			i++;
			p++;
		}

		while (q < sample.coefficients.length) {
			new_coefficients[p] = sample.coefficients[q];
			new_exponents[p] = sample.exponents[q];
			q++;
			p++;
		}

        int length_of_sum = 0;
		double[] sum_coefficients = new double[maxL];
		int[] sum_exponents = new int[maxL];
		
        for (int g = 0; g < maxL; g++) {
			if (new_coefficients[g] != 0) {
				sum_coefficients[length_of_sum] = new_coefficients[g];
				sum_exponents[length_of_sum] = new_exponents[g];
				length_of_sum++;
			}
		}

        sum_exponents = Arrays.copyOf(sum_exponents, length_of_sum);
		sum_coefficients = Arrays.copyOf(sum_coefficients, length_of_sum);
	

		return new Polynomial(sum_coefficients, sum_exponents);

    }



    public double evaluate(double x){
        double count = 0;
        int sample_size = this.coefficients.length;

        for (int i = 0; i< sample_size; i++){
            count += this.coefficients[i] * (Math.pow(x, this.exponents[i]));
        }
        return count;
    }



    public boolean hasRoot(double root){
        return (this.evaluate(root) == 0);
    }



    public Polynomial multiply(Polynomial sample) {
		int x = this.exponents.length;
		
        int y  = sample.exponents.length;
		
        int new_length = y + x + 1;

        int[] product_powers = new int[new_length];
		double[] product_coefficients = new double[new_length];
		

		for (int p = 0; p < x; p++) {
			for (int q = 0; q < y; q++) {
				int new_power = this.exponents[p] + sample.exponents[q];
				product_coefficients[new_power] += (this.coefficients[p] * sample.coefficients[q]);
				product_powers[new_power] = new_power;
			}
		}

        int length_of_product = 0;
		double[] p_coefficients = new double[new_length];
		int[] p_exponents = new int[new_length];
		
        for (int i = 0; i < new_length; i++) {
			if (product_coefficients[i] != 0) {
				p_coefficients[length_of_product] = product_coefficients[i];
				p_exponents[length_of_product] = product_powers[i];
				length_of_product++;
			}
		}

        p_exponents = Arrays.copyOf(p_exponents, length_of_product);
		p_coefficients = Arrays.copyOf(p_coefficients, length_of_product);
	

		return new Polynomial(p_coefficients, p_exponents);
	}

    
	public void saveToFile(String fileName) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		for (int i = 0; i < this.coefficients.length; i++)
			{
				if (this.coefficients[i] != 0) 
				{

					if (i != 0 && coefficients[i] > 0) {
						writer.write("+");
					}
					if (i != 0 && coefficients[i] < 0) {
						writer.write("-");
					}
					
					writer.write(Double.toString(this.coefficients[i]));

					if(this.exponents[i] != 0){
						writer.write("x");
						writer.write(Integer.toString(this.exponents[i]));
					}
				}
			}
			writer.close();
			
	
	}

}