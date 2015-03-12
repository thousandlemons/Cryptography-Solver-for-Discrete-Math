package demo;

import java.util.Arrays;
import java.util.Map;

import crypto.Crypto;
import crypto.CryptoFactory;
import crypto.Encoding;

public class CryptoDemo {

	public static void affine(){
		Crypto affine = CryptoFactory.affine(5, 7, 26);
		System.out.println(ecd.encrypt("apple", affine));
		System.out.println(ecd.decrypt("heekb", affine));
	}
	
	public static void asciiEncoding(){
		Encoding e = Encoding.ASCII;
		Map<Character, Integer> charIntMap = e.getCharIntMap();
		for(Character c : charIntMap.keySet()){
			System.out.println(c + " : " + charIntMap.get(c));
		}
	}
	
	public static void caesar(){
		Crypto caesar = CryptoFactory.caesar(5, 26);
		System.out.println(ecd.encrypt("apple", caesar));
		System.out.println(ecd.decrypt("fuuqj", caesar));
	}
	
	public static void defaultEncoding(){
		Encoding e = Encoding.DEFAULT;
		Map<Character, Integer> charIntMap = e.getCharIntMap();
		for(Character c : charIntMap.keySet()){
			System.out.println(c + " : " + charIntMap.get(c));
		}
	}
	
	public static void rsa(){
		Crypto rsa = CryptoFactory.rsa(7, 13, 5);
		System.out.println(Encoding.ASCII.encrypt("apple", rsa));
	}
	
	public static void vigenere(){
		Crypto vigenere = CryptoFactory.vigenere(ecd.decode("key"), 26);
		System.out.println(ecd.encrypt("iloveapple", vigenere));
		System.out.println(ecd.decrypt("spmfiyztjo", vigenere));
	}
	
	public static void oneTimePad(){
		Crypto otp1 = CryptoFactory.oneTimePad(ecd.decode("onetimepad"), 26);
		Crypto otp2 = CryptoFactory.oneTimePad(ecd.decode("onetimepad"), 26);
		System.out.println(ecd.encrypt("apple", otp1));
		System.out.println(ecd.decrypt("octem", otp2));
	}
	
	public static final Encoding ecd = Encoding.DEFAULT;
	
	public static void main(String[] args) {
		Crypto caesar = CryptoFactory.caesar(5, 26); //key and divisor respectively
		String cipher = Encoding.DEFAULT.encrypt("programmingisfun", caesar);
		System.out.println(cipher);
	}

	private CryptoDemo(){
		
	}
}
