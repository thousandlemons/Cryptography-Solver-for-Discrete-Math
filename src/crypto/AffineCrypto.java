/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

class AffineCrypto implements Crypto {
	
	private int a;
	private int b;
	private int aModInv;
	private int divisor;

	protected AffineCrypto(int a, int b, int divisor) {
		this.a = a;
		this.b = b;
		this.divisor = divisor;
		this.aModInv = (int) DiscreteMath.modInv(a, this.divisor);
	}

	@Override
	public int[] decrypt(int[] y) {
		int[] result = new int[y.length];
		for(int i = 0; i < y.length; ++i){
			result[i] = (int) DiscreteMath.mod(this.aModInv * (y[i] - b), this.divisor);
		}
		return result;
	}

	@Override
	public int[] encrypt(int[] x) {
		int[] result = new int[x.length];
		for(int i = 0; i < x.length; ++i){
			result[i] = (int) DiscreteMath.mod(this.a * x[i] + b, this.divisor);
		}
		return result;
	}

}
