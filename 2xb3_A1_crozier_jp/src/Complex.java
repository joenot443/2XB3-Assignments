public class Complex {

	// The real part of the complex number
	private final double a;
	// The imaginary part of the complex number
	private final double b;

	// Constructor for a Complex number

	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	// Default constructor for a Complex number
	public Complex() {
		this.a = 0;
		this.b = 0;
	}

	// Getters
	public double a() {
		return this.a;
	}

	public double b() {
		return this.b;
	}

	// Addition method
	// Adds a Complex c, to itself and returns a new Complex

	public Complex add(Complex c) {
		// new Complex = (a1 + a2) + (b1 + b2)i
		return new Complex((this.a() + c.a()), (this.b() + c.b()));
	}

	// Subtraction method
	// Subtracts a Complex c, from itself and returns a new Complex

	public Complex subtract(Complex c) {
		// new Complex = (a1 - a2) + (b1 = b2)i
		return new Complex((this.a() - c.a()), (this.b() - c.b()));
	}

	// Multiplication method
	// Multiplies a Complex c with itself and returns a new Complex

	public Complex multiply(Complex c) {
		// new Complex = (a1*a2 - b1*b2) + (a1*b2 + a2*b1)
		return new Complex((this.a() * c.a() - this.b() * c.b()), (this.a()
				* c.b() + c.a() * this.b()));

	}

	// Division method
	// Divides a Complex c by itself and returns a new Complex

	public Complex divide(Complex c) {
		// new Complex = (a1*a2 + b1*b2)/(a2^2 + b2^2) + [ (a2*b1 - a1*b2)/(a2^2
		// + b2^2) ]i
		return new Complex(((this.a() * c.a() + this.b() * c.b()))
				/ (c.a() * c.a() + c.b() * c.b()), (c.a() * this.b() - this.a()
				* c.b())
				/ (c.a() * c.a() + c.b() * c.b()));

	}

	// Copy method
	// Copies a complex number and returns a new, identical Complex

	public Complex copy() {
		return new Complex(this.a(), this.b());
	}

	// isZero method
	// Tests to see if the complex number has both an a and b value of 0

	public boolean isZero() {
		// a+bi = 0
		if (this.a() == 0 && this.b() == 0) {
			return true;
		}
		return false;
	}

	 public Complex squareRoot() {
		 //The principal square root of a complex number (z) can be defined using the formula:
		 //sqrt(z) = sqrt(r) * (z+r) / (|z+r|)
		 //Where r = |z| (modulus)
		 
		 double r = this.modulus();
		 double sqrR = Math.sqrt(r);

		 //Convert sqrt(r) into a complex number to use the defined Complex operations
		 Complex sqrRC = new Complex(sqrR, 0);
		 //Convert r into a complex number to use the defined Complex operations
		 Complex rC = new Complex(r, 0);
		 //Returns the square root of the provided Complex
		 return sqrRC.multiply((this.add(rC).divide(new Complex((this.add(rC).modulus()), 0))));
	 }

	// Modulus method
	// Returns the modulus ("vector length") of a Complex
	// Use the math library to find the sqrt, then cast it as a double

	public double modulus() {
		// mod = sqrt(a^2 + b^2)
		return Math.sqrt(this.a() * this.a() + this.b() * this.b());
	}

	// Norm method
	// Returns the norm of a Complex

	public double norm() {
		// norm = a^2 + b^2
		return this.a() * this.a() + this.b() * this.b();
	}

	// Polar form method
	// Converts a Complex number to polar form and prints the result. It does
	// not return a new Complex.
	public double[] polarForm() {
		// r = sqrt(a^2+b^2)
		// theta = arctan(b/a);
		double r = this.modulus();
		double theta = Math.atan2(this.b(), this.a());
		System.out.println("r =" + r + " theta =" + theta + " rads");
		return new double[] {r, theta};
		// Return a new Complex in polar form
	}

	// Conjugate form method
	// Finds the conjugate of a given Complex, this is done by swapping the sign
	// of the a and b values.
	public Complex conjugate() {
		// new Complex = a + -bi
		return new Complex(this.a(), -this.b());
	}

	// Rounding method
	// Rounds both the a and b values of a Complex to their nearest whole
	// numbers and returns a new Complex

	public Complex roundToInteger() {
		// Use the math library to round the double values to the nearest int value
		// This is different from simply casting them as an int
		return new Complex(Math.round(this.a()), Math.round(this.b()));

	}

	// Equals test method
	// Simply test if the Complex is equal to another Complex by comparing their
	// a and b values
	public boolean equals(Complex c) {
		// If a1 == a2 and b1 == b2, the Complex numbers are equal.
		if (this.a() == c.a() && this.b() == c.b()) {
			return true;
		}
		// Otherwise, they're not equal.
		return false;
	}

	// toString method
	// Return the value of the complex number as a readable string
	@Override
	public String toString() {
		String returnString = this.a() + " + " + this.b() + "i";

		System.out.println(returnString);
		return returnString;
	}

	// GCD method
	// This method returns the greatest common denominator between two Complex
	// numbers.

	public static Complex gcd(Complex a, Complex b) {

		// Create pointers for the two Complex numbers we'll be working with
		// This is so we do not have to reproduce code
		Complex c1;
		Complex c2;

		// First, find which number has the greatest norm
		if (a.norm() >= b.norm()) {

			c1 = a;
			c2 = b;
		}
		// In either case, c1 is the Complex with a greater norm
		else {
			c1 = b;
			c2 = a;
		}

		// Give r an original value of 1,1.
		// This allows our loop to run, the values 1,1, have no significance.

		Complex r = new Complex(1, 1);

		while (!r.isZero()) {

			// Find the quotient, c1/c2

			Complex q = c1.divide(c2);

			// Round the values of q to their nearest int value

			q = q.roundToInteger();

			// Find the remainder of the division
			// r= c1 - q*c2

			r = c1.subtract(q.multiply(c2));

			// Now set c1 to c2, and c2 to r

			c1 = c2.copy();
			c2 = r.copy();

			// At this point, if r != 0, the loop will continue.
			// Once it does, c1 will contain the value for our GCD.

		}

		// Return c1, our GCD

		return c1;

	}

	// Rotate method
	// Rotates the Complex number clockwise around the complex plane.

	public static Complex rotate(Complex a, int angle) {
		// The formula for rotating a 2D vector about the origin is:
		// x' = x*cos(t) - y*sin(t)
		// y' = y*cos(t) + x*sin(t)
		// Where t is our angle of rotation.

		// This same principle can easily be extended to complex numbers.
		// Simply treat x as our a and y as our b
		// It is important to note that Math.cos() and Math.sin() expect a value
		// in radians.
		// We must convert our value to degrees using Math.toDegrees().
		double angleRad = Math.toRadians(angle);
		double newA = (a.a() * Math.cos(angleRad) + a.b()
				* Math.sin(angleRad));
		double newB = (a.b() * Math.cos(angleRad) - a.a()
				* Math.sin(angleRad));
		//Return the rotated Complex
		return new Complex(newA, newB);
	}
	
	//Get current method
	//This method uses the formula:
	//E = IZ
	//To calculate I, given E and Z
	
	public static Complex get_current(Complex v,Complex z) {
		//Simply rearrange to I = Z/E
		return z.divide(v);
	}
	
	//Get voltage method
	//This method uses the formula:
	//E = IZ
	//To calculate Z, given E and I
	
	public static Complex get_voltage(Complex i,Complex z) {
		//Simply use the given formula, E = I*Z
		return i.multiply(z);
	}
	
}
