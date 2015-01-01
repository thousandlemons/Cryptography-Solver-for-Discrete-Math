/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The {@code Encoding} class represents a one-to-one mapping between
 * characters and integers, so that a character string can be further
 * encrypted/decrypted by {@code Crypto} objects
 * @author Nathaniel
 *
 */
public final class Encoding {
	
	private HashMap<Character, Integer> charIntMap = new HashMap<Character, Integer>();

	private HashMap<Integer, Character> intCharMap = new HashMap<Integer, Character>();

	/**
	 * The default case-insensitive mapping, as a/A=0, b/B=1, ..., z/Z=25
	 */
	public static final Encoding DEFAULT;
	
	static{
		HashMap<Character, Integer> defMap = new HashMap<Character, Integer>();
		for(int i = 97; i < 97 + 26; ++i){
			defMap.put((char)i, i - 97);
		}
		DEFAULT = new Encoding(defMap, true);
	}
	
	/**
	 * The ASCII encoding (e.g. a=97)
	 */
	public static final Encoding ASCII;

	static{
		HashMap<Character, Integer> asciiMap = new HashMap<Character, Integer>();
		for(int i = 0; i < 128; ++i){
			asciiMap.put((char)i, i);
		}
		ASCII = new Encoding(asciiMap);
	}
	
	/**
	 * The default constructor. Error checking is performed before an object
	 * is created. If the the {@code Map} is not strictly one-to-one, 
	 * a {@code RuntimeException} will be thrown
	 * @param mapping A strictly one-to-one mapping between characters and integers
	 */
	public Encoding(Map<Character, Integer> mapping) {
		this.setup(mapping, false);
	}
	
	/**
	 * An extension to the default construction with case-insensitive ability. 
	 * Error checking is performed before an object is created. 
	 * If the {@code Map} is not strictly one-to-one, a {@code RuntimeException}
	 * will be thrown
	 * @param mapping A strictly one-to-one mapping between characters and integers
	 * @param ignoreCase {@code True} if allow lower/upper case characters to be mapped to the same integer
	 */
	public Encoding(Map<Character, Integer> mapping, boolean ignoreCase){
		this.setup(mapping, ignoreCase);
	}
	
	/**
	 * Check if a character is defined in the {@code Encoding} object
	 * @param c The character to check
	 * @return True if such a character is defined; false if not
	 */
	public boolean hasRegistered(char c){
		return this.charIntMap.containsKey(c);
	}
	
	/**
	 * Check if an integer is defined in the {@code Encoding} object
	 * @param i The integer to check
	 * @return True if such an integer is defined; false if not
	 */
	public boolean hasRegistered(int i){
		return this.intCharMap.containsKey(i);
	}
	
	private void checkUnregistered(char c){
		if(!this.charIntMap.containsKey(c)){
			throw new RuntimeException("Unregistered char: \"" + c + "\"");
		}
	}
	
	private void checkUnregistered(int i){
		if(!this.intCharMap.containsKey(i)){
			throw new RuntimeException("Unregistered integer: \"" + i + "\"");
		}
	}
	
	/**
	 * Decode a character
	 * @param c The character to decode
	 * @return The corresponding integer
	 */
	public int decode(char c){
		this.checkUnregistered(c);
		return this.charIntMap.get(c);
	}
	
	/**
	 * Decode character string
	 * @param s The {@code String} to decode
	 * @return The {@code int[]} corresponding to each of the characters in the {@code String}
	 */
	public int[] decode(String s){
		char[] array = s.toCharArray();
		int[] result = new int[array.length];
		for(int i = 0; i < array.length; ++i){
			this.checkUnregistered(array[i]);
			result[i] = this.charIntMap.get(array[i]);
		}
		return result;
	}
	
	/**
	 * Decrypt a {@code String} cipher using a {@code Crypto} object
	 * @param s The {@code String} to decrypt
	 * @param c The {@code Crypto} object used
	 * @return The original message
	 */
	public String decrypt(String s, Crypto c){
		return this.encode(c.decrypt(this.decode(s)));
	}
	
	/**
	 * Encode an integer
	 * @param i The integer to encode
	 * @return The corresponding character
	 */
	public char encode(int i){
		this.checkUnregistered(i);
		return this.intCharMap.get(i);
	}
	
	/**
	 * Encode an array of integers into a {@code String}
	 * @param array The array to encode
	 * @return The result {@code String}
	 */
	public String encode(int[] array){
		StringBuilder sb = new StringBuilder(array.length);
		for(int i : array){
			this.checkUnregistered(i);
			sb.append(this.intCharMap.get(i));
		}
		return sb.toString();
	}
	
	/**
	 * Encrypt a {@code String} using a {@code Crypto} object
	 * @param s The {@code String} to encrypt
	 * @param c The {@code Crypto} object used
	 * @return The encrypted cipher
	 */
	public String encrypt(String s, Crypto c){
		return this.encode(c.encrypt(this.decode(s)));
	}
	
	/**
	 * Two {@code Encoding} objects equal to each other if and only if they represent
	 * logically the same encoding scheme between characters and integers
	 */
	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}
		else if(!(o instanceof Encoding)){
			return false;
		}
		Encoding e = (Encoding)o;
		return e.charIntMap.equals(this.charIntMap) && e.intCharMap.equals(this.intCharMap);
	}
	
	/**
	 * Get a {@code Map} view of the characters and integers defined in this {@code Encoding}. 
	 * Modifications to the returned {@code Map} will not affect the data in this object.
	 * @return The {@code Map} view
	 */
	public Map<Character, Integer> getCharIntMap(){
		return new HashMap<Character, Integer>(this.charIntMap);
	}
	
	/**
	 * Get a {@code Set} view of the characters defined in this {@code Encoding}. 
	 * Modifications to the returned {@code Set} will not affect the data in this object.
	 * @return The {@code Set} view
	 */
	public Set<Character> getCharSet(){
		return new HashSet<Character>(this.charIntMap.keySet());
	}
	
	/**
	 * Get a {@code Map} view of the integers and characters defined in this {@code Encoding}. 
	 * Modifications to the returned {@code Map} will not affect the data in this object.
	 * @return The {@code Map} view
	 */
	public Map<Integer, Character> getIntCharMap(){
		return new HashMap<Integer, Character>(this.intCharMap);
	}
	
	/**
	 * Two {@code Encoding} objects that equal to each other will get the same hash code.
	 */
	@Override
	public int hashCode(){
		int h = 17;
		h = 31 * h + this.charIntMap.hashCode();
		h = 31 * h + this.intCharMap.hashCode();
		return h;
	}
	
	private void setup(Map<Character, Integer> mapping, boolean ignoreCase){
		for(Character c : mapping.keySet()){
			int i = mapping.get(c);
			if(this.intCharMap.containsKey(i)){
				throw new RuntimeException("Same integer \"" + i + "\" mapped to different characters");
			}
			boolean isLower = Character.isLowerCase(c);
			this.charIntMap.put(c, i);
			this.intCharMap.put(i, c);
			if(ignoreCase){
				char otherCase = isLower? Character.toUpperCase(c) : Character.toLowerCase(c);
				this.charIntMap.put(otherCase, i);
			}
		}
	}
}
