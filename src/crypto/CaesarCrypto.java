/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

class CaesarCrypto implements Crypto {
	
	private int key;
	private int divisor;

	protected CaesarCrypto(int key, int divisor) {
		this.key = key;
		this.divisor = divisor;
	}

	@Override
	public int[] decrypt(int[] y) {
		int[] result = new int[y.length];
		for(int i = 0; i < result.length; ++i){
			if(y[i] >= 0 && y[i] <= this.divisor - 1){
				result[i] = (int) DiscreteMath.mod((y[i] + this.divisor - this.key), this.divisor);
			}
			else{
				result[i] = y[i];
			}
		}
		return result;
	}

	@Override
	public int[] encrypt(int[] x) {
		int[] result = new int [x.length];
		for(int i = 0; i < result.length; ++i){
			if(x[i] >= 0 && x[i] <= this.divisor - 1){
				result[i] = (int) DiscreteMath.mod((x[i] + this.key), this.divisor);
			}
			else{
				result[i] = x[i];
			}
		}
		return result;
	}

}
