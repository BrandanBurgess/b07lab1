public class Polynomial{
    double coefficients [];


    public Polynomial(){
        this.coefficients = new double[] {0};
    }

    public Polynomial(double[] sample){
        this.coefficients = sample;
    }

    public Polynomial add(Polynomial sample){

        Polynomial temp = new Polynomial();

        if (this.polynomial.length > sample.length){
            temp = this.polynomial;
            for (int i = 0; i < sample.length; i++) {
                temp.coefficients[i] += sample.coefficients[i];
            } 
        }

        else{
            temp = sample;
            for (int i = 0; i < this.polynomial.length; i++){
                temp.coefficients[i] += this.polynomial.coefficients[i];
            }
        }

        return temp;

    }

    public double evaluate(double x){
        double count = 0;
        int sample_size = this.coefficients.length;

        for (int i = 0; i< sample_size; i++){
            count += this.coefficients[i] * (Math.pow(x, i));
        }
        return count;
    }

    public boolean hasroot(double root){
        return (this.evaluate(root) == 0);
    }

}