/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

/**
 * This is a static utilities class that provides basic operations in discrete math.
 * This class cannot be instantiated.
 * @author Nathaniel
 *
 */

public class DiscreteMath {
	
	private static void checkPositive(long l){
		if(l <= 0){
			throw new RuntimeException("Illegal value " + l + ". It must be positive.");
		}
	}
/*	
	private static void checkNonNegative(long l){
		if(l < 0){
			throw new RuntimeException("Illegal value " + l + ". It must be non-negative.");
		}
	}
*/	
	private static long[] extendEARecursive(long a, long b){
		long[] ans = new long[3];
        long q;

        if (b == 0)  {
            ans[0] = a;
            ans[1] = 1;
            ans[2] = 0;
        }
        else
            {
               q = a/b;
               ans = extendEARecursive (b, a % b);
               long temp = ans[1] - ans[2]*q;
               ans[1] = ans[2];
               ans[2] = temp;
            }

        return ans;
	}
	
	
	private static long[] extendedEA(long a, long b){
		checkPositive(a);
		checkPositive(b);
        return extendEARecursive(a, b);
	}
	
	private static long gcdRecursive(long smaller, long larger){
		if(smaller == 0){
			return larger;
		}
		else{
			return gcdRecursive(larger % smaller, smaller);
		}
	}
	
	/**
	 * Get the greatest common divisor of two positive integers
	 * @param a A positive integer
	 * @param b Another positive integer
	 * @return The gcd of a and b
	 */
	public static long gcd(long a, long b){
		checkPositive(a);
		checkPositive(b);
		if(a == b){
			return a;
		}
		else if(a > b){
			long temp = a;
			a = b;
			b = temp;
		}
		return gcdRecursive(a, b);
	}
	
	/**
	 * Perform a mod b
	 * @param a An integer, which could be negative, zero, or positive
	 * @param b A positive integer
	 * @return a mod b
	 */
	public static long mod(long a, long b){
		checkPositive(b);
		while(a < 0){
			a += b;
		}
		return a % b;
	}
	
	/**
	 * Calculate modulus exponentiation by iterative approach
	 * @param base The base
	 * @param expo The exponent
	 * @param divisor The divisor
	 * @return base^expo (mod divisor)
	 */
	public static long modExpoIterative(long base, long expo, long divisor){
		checkPositive(base);
		checkPositive(expo);
		checkPositive(divisor);
		String binStr = Long.toBinaryString(expo);
		long result = 1;
		for(int i = 0; i < binStr.length(); ++i){
			if(binStr.charAt(i) == '1'){
				result = (result * result) % divisor;
				result = (result * base) % divisor;
			}
			else{
				result = (result * result) % divisor;
			}
		}
		return result;
	}
	
	/**
	 * Calculate modulus exponentiation by recursive approach
	 * @param base The base
	 * @param expo The exponent
	 * @param divisor The divisor
	 * @return base^expo (mod divisor)
	 */
	public static long modExpoRecursive(long base, long expo, long divisor){
		checkPositive(base);
		checkPositive(expo);
		checkPositive(divisor);
		return modExpoRecursiveHelper(base, expo, divisor);
	}
	
	private static long modExpoRecursiveHelper(long base, long expo, long divisor){
		if(expo == 1){
			return base % divisor;
		}
		else if(expo % 2 == 0){
			long temp = modExpoRecursiveHelper(base, expo / 2, divisor);
			return (temp * temp) % divisor;
		}
		else{
			long temp = modExpoRecursiveHelper(base, expo - 1, divisor);
			return (base * temp) % divisor;
		}
	}
	/**
	 * Calculate the modulus inverse
	 * @param k A positive integer that is coprime to n
	 * @param n A positive integer
	 * @return k^(-1) mod n
	 */
	public static long modInv(long k, long n){
		if(gcd(n, k) != 1){
			throw new RuntimeException("Modulus inverse does not exist because gcd(a,b) != 1");
		}
		return mod(extendedEA(n, k)[2], n);
	}
	
	/**
	 * Calculate power efficiently by iterative approach
	 * @param base The base
	 * @param expo The exponent
	 * @return base^expo
	 */
	public static long powerIterative(long base, long expo){
		checkPositive(base);
		checkPositive(expo);
		String binStr = Long.toBinaryString(expo);
		long result = 1;
		for(int i = 0; i < binStr.length(); ++i){
			if(binStr.charAt(i) == '1'){
				result *= result;
				result *= base;
			}
			else{
				result *= result;
			}
		}
		return result;
	}
	
	/**
	 * Calculate power efficiently by recursive approach
	 * @param base The base
	 * @param expo The exponent
	 * @return base^expo
	 */
	public static long powerRecursive(long base, long expo){
		checkPositive(base);
		checkPositive(expo);
		return powerRecursiveHelper(base, expo);
	}
	
	private static long powerRecursiveHelper(long base, long expo){
		if(expo == 1){
			return base;
		}
		else if(expo % 2 == 0){
			long temp = powerRecursiveHelper(base, expo / 2);
			return temp * temp;
		}
		else{
			long temp = powerRecursiveHelper(base, expo - 1);
			return base * temp;
		}
	}
	
	private DiscreteMath(){
		
	}
	
}
