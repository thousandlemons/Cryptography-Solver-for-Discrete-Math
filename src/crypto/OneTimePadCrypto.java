/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

public class OneTimePadCrypto implements Crypto {
	
	private int[] pad;
	private int divisor;
	private boolean used;
	
	public OneTimePadCrypto(int[] pad, int divisor) {
		this.pad = pad;
		this.divisor = divisor;
	}
	
	private void checkConditions(int[] a){
		if(used){
			throw new RuntimeException("This one time pad has been used");
		}
		if(a.length > this.pad.length){
			throw new RuntimeException("This one time pad is not long enough");
		}
	}

	@Override
	public int[] decrypt(int[] y) {
		this.checkConditions(y);
		int[] result = new int[y.length];
		for(int i = 0; i < y.length; ++i){
			result[i] = (int) DiscreteMath.mod(y[i] - this.pad[i], this.divisor);
		}
		this.used = true;
		return result;
	}

	@Override
	public int[] encrypt(int[] x) {
		this.checkConditions(x);
		int[] result = new int[x.length];
		for(int i = 0; i < x.length; ++i){
			result[i] = (int) DiscreteMath.mod(this.pad[i] + x[i], this.divisor);
		}
		this.used = true;
		return result;
	}

}
