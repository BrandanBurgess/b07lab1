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

    public Polynomial add(Polynomial sample){

        Polynomial temp = new Polynomial();

        int maxL = this.coeff.length + sample.coeff.length;
		double[] new_coefficients = new double[length];
		int[] new_exponents = new int[length];

		

        int i = 0;
        int p = 0; // k
        int q = 0; // j

		while (q < sample.coeff.length && i < coeff.length) {
			if (this.powers[i] > sample.powers[q]) {
				newCoeff[p] = sample.coeff[q];
				newPowers[p] = sample.powers[q];
				q++;
			}

            else if (this.powers[i] < p.powers[q]) {
				newCoeff[p] = this.coeff[i];
				newPowers[p] = this.powers[i];
				i++;
			}

            else {
				newCoeff[p] = this.coeff[i] + sample.coeff[q];
				newPowers[p] = this.powers[i];
				i++;
				j++;
			}
			p++;
		}

		while (i < coeff.length) {
			newCoeff[p] = this.coeff[i];
			newPowers[p] = this.powers[i];
			i++;
			p++;
		}

		while (j < sample.coeff.length) {
			newCoeff[p] = sample.coeff[q];
			newPowers[p] = sample.powers[q];
			q++;
			p++;
		}

    }

    public double evaluate(double x){
        double count = 0;
        int sample_size = this.coefficients.length;

        for (int i = 0; i< sample_size; i++){
            count += this.coefficients[i] * (Math.pow(x, i));
        }
        return count;
    }

    public boolean hasRoot(double root){
        return (this.evaluate(root) == 0);
    }

}