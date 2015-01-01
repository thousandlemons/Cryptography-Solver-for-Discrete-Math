/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

/**
 * The {@code CryptoFactory} class is a static factory to generate built-in 
 * {@code Crypto} objects, including Caesar, Affine, Vigenere, RSA, and One Time Pad.
 * @author Nathaniel
 *
 */

public class CryptoFactory {

	/**
	 * Get a built-in {@code Crypto} for Affine Cipher
	 * @param a One of the parameters that define the Affine Cipher
	 * @param b One of the parameters that define the Affine Cipher
	 * @param divisor The "n" in "x mod n", used in the modulus operation
	 * @return A {@code Crypto} object
	 */
	public static Crypto affine(int a, int b, int divisor){
		return new AffineCrypto(a, b, divisor);
	}
	
	/**
	 * Get a built-in {@code Crypto} for Caesar Cipher
	 * @param key The key that defines the Caesar Cipher
	 * @param divisor The "n" in "x mod n", used in the modulus operation
	 * @return A {@code Crypto} object
	 */
	public static Crypto caesar(int key, int divisor){
		return new CaesarCrypto(key, divisor);
	}
	
	/**
	 * Get a built-in {@code Crypto} for Vigenere Cipher
	 * @param key The key that defines the Vigenere Cipher
	 * @param divisor The "n" in "x mod n", used in the modulus operation
	 * @return A {@code Crypto} object
	 */
	public static Crypto vigenere(int[] key, int divisor){
		return new VigenereCrypto(key, divisor);
	}
	
	/**
	 * Get a built-in {@code Crypto} for RSA cryptosystems
	 * @param p A large prime
	 * @param q Another large prime
	 * @param e The public key
	 * @return A {@code Crypto} object
	 */
	public static Crypto rsa(long p, long q, long e){
		return new RSACrypto(p, q, e);
	}
	
	/**
	 * Get a built-in {@code Crypto} for One Time Pad Cipher.
	 * This object can only be used for once. Attempt to use a used object
	 * will throws a {@code RuntimeException}.
	 * @param pad The One Time Pad used to perform the encryption/decryption
	 * @param divisor The "n" in "x mod n", used in the modulus operation
	 * @return A {@code Crypto} object
	 */
	public static Crypto oneTimePad(int[] pad, int divisor){
		return new OneTimePadCrypto(pad, divisor);
	}
	
	private CryptoFactory() {
		
	}

}
