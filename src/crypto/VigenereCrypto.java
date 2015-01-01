/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

import java.util.Arrays;

class VigenereCrypto implements Crypto {
	
	private int[] key;
	private int divisor;

	public VigenereCrypto(int[] key, int divisor) {
		this.key = Arrays.copyOf(key, key.length);
		this.divisor = divisor;
	}

	@Override
	public int[] decrypt(int[] y) {
		int[] result = new int[y.length];
		for(int i = 0, j = 0; i < result.length; ++i, ++j){
			if(y[i] >= 0 && y[i] <= this.divisor - 1){
				result[i] = (int) DiscreteMath.mod(y[i] - this.key[(int) DiscreteMath.mod(j, this.key.length)], this.divisor);
			}
			else{
				result[i] = y[i];
			}
		}
		return result;
	}

	@Override
	public int[] encrypt(int[] x) {
		int[] result = new int[x.length];
		for(int i = 0, j = 0; i < result.length; ++i, ++j){
			if(x[i] >= 0 && x[i] <= this.divisor - 1){
				result[i] = (int) DiscreteMath.mod(x[i] + this.key[(int) DiscreteMath.mod(j, this.key.length)], this.divisor);
			}
			else{
				result[i] = x[i];
			}
		}
		return result;
	}

}
