package dk.sdu.imada.rsa;

import java.math.BigInteger;

public class NumberHelpers {

	private static final BigInteger TWO = new BigInteger("2");

	/**
	 * Extended euclidean algorithm
	 * @param a
	 * @param b
	 * @return [r,s,t] where r = gcd(a,b) and sa + tb = r
	 */
	public static BigInteger[] extendedEuclideanAlgorithm(BigInteger a, BigInteger b) {
		BigInteger[] a0 = {a};
		BigInteger[] b0 = {b};
		BigInteger[] t0 = {BigInteger.valueOf(0)};
		BigInteger t = BigInteger.valueOf(1);
		BigInteger[] s0 = {BigInteger.valueOf(1)};
		BigInteger s = BigInteger.valueOf(0);
		BigInteger q = a0[0].divide(b0[0]);
		BigInteger r = a0[0].subtract(q.multiply(b0[0]));

		while (r.compareTo(BigInteger.valueOf(0))>0){
			BigInteger temp = t0[0].subtract(q.multiply(t));
			t0[0] = t;
			t = temp;
			temp = s0[0].subtract(q.multiply(s));
			s0[0] = s;
			s = temp;
			a0[0] = b0[0];
			b0[0] = r;
			q = a0[0].divide(b0[0]);
			r = a0[0].subtract(q.multiply(b0[0]));
		}
		r = b0[0];
		BigInteger[] solution = {r,s,t};

		return solution;
	}

	/**
	 * Method handling modular exponentiation efficiently
	 * @param base
	 * @param pow
	 * @param modulus
	 * @return BigDecimal with the value of base^pow mod modulus
	 */
	public static BigInteger recursiveModularExponentiation(BigInteger base, BigInteger pow, BigInteger modulus) {
		if (pow.equals(BigInteger.valueOf(0))){
			return BigInteger.valueOf(1);
		}
		if (pow.equals(BigInteger.valueOf(1))){
			return base;
		}
		BigInteger pave = pow.mod(BigInteger.valueOf(2));

		if (pave.equals(BigInteger.valueOf(0))){
			BigInteger temp = recursiveModularExponentiation(base, pow.divide(BigInteger.valueOf(2)), modulus);
			return temp.multiply(temp).mod(modulus);
		}
		else {
			BigInteger temp = recursiveModularExponentiation(base, pow.divide(BigInteger.valueOf(2)), modulus);
			return base.multiply(temp).multiply(temp).mod(modulus);
		}
	}
}
