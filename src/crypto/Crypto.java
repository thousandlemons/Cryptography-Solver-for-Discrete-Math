/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

/**
 * The {@code Crypto} interface defines the ability of any implementing class
 * to encrypt/decrypt an {@code int[]} object.
 * @author Nathaniel
 *
 */

public interface Crypto {
	
	/**
	 * Encrypt an {@code int[]} object message
	 * @param y The message to encrypt
	 * @return The encrypted cipher
	 */
	public int[] decrypt(int[] y);
	
	/**
	 * Decrypt an {@code int[]} object cipher
	 * @param x The cipher to decrypt
	 * @return The original message
	 */
	public int[] encrypt(int[] x);

}
