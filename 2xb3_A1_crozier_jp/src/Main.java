

public class Main {

	public static void main(String[] args) {
		
		Complex c = new Complex(-3,-22);
		Complex d = new Complex(155,34);
		
		Complex j = c.divide(d);
		System.out.println(j.a());
		System.out.println(j.b());
		
		Complex f = c.squareRoot();
		f.toString();
		
	}

}
	