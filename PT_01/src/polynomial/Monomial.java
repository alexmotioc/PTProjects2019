package polynomial;

public class Monomial implements Comparable<Monomial> {

	private int degree;
	private double coeff;

	
	public Monomial(double coeff,int degree)
	{
		this.degree = degree;
		this.coeff = coeff;
	}


	public int getDegree() {
		return degree;
	}


	public void setDegree(int degree) {
		this.degree = degree;
	}


	public double getCoeff() {
		return coeff;
	}


	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
	public boolean equals(Monomial m) {
		if(degree==m.degree && coeff==m.coeff) {
			return true;
		}
		else
			return false;
	}
	
	public String toString() {
		return coeff + "x^" + degree;
	}
	
	public Monomial divide(Monomial m)
	{
		Monomial result;
		
		result = new Monomial(this.getCoeff()/m.getCoeff(),this.getDegree() - m.getDegree());
		
		return result;
		
	}

	@Override
	public int compareTo(Monomial o) {
		// TODO Auto-generated method stub
		if(this.degree < o.degree)
			return 1;
		if(this.degree > o.degree)
			return -1;
		
		return 0;
	}
	
	
}
