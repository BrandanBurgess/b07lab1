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

        if (this.coefficients.length > sample.coefficients.length){
            temp.coefficients = this.coefficients;
            for (int i = 0; i < sample.coefficients.length; i++) {
                temp.coefficients[i] += sample.coefficients[i];
            } 
        }

        else{
            temp = sample;
            for (int i = 0; i < this.coefficients.length; i++){
                temp.coefficients[i] += this.coefficients[i];
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

    public boolean hasRoot(double root){
        return (this.evaluate(root) == 0);
    }

}