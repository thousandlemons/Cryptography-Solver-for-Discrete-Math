/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package crypto;

class RSACrypto implements Crypto{
	
	private long n;
	private long e;
	private long d;

	protected RSACrypto(long p, long q, long e) {
		this.n = p * q;
		this.e = e;
		this.d = DiscreteMath.modInv(e, (p-1) * (q-1));
	}

	@Override
	public int[] decrypt(int[] y) {
		int[] result = new int[y.length];
		for(int i = 0; i < y.length; ++i){
			result[i] = (int) DiscreteMath.modExpoRecursive(y[i], this.d, this.n);
		}
		return result;
	}

	@Override
	public int[] encrypt(int[] x) {
		int[] result = new int[x.length];
		for(int i = 0; i < x.length; ++i){
			result[i] = (int) DiscreteMath.modExpoRecursive(x[i], this.e, this.n);
		}
		return result;
	}

}
