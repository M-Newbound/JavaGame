package com.util;

/**
 * 
 * Represents a pair of values, A and B.
 *
 * @param <A> The first value
 * @param <B> The second value
 */
class TuplePair <A, B>
{
	private A datapointA;
	private B datapointB;
	
	/**
	 * sets up a tuple pair 
	 * @param a the value for A
	 * @param b the value for B
	 */
	public TuplePair(A a, B b)
	{
		datapointA = a;
		datapointB = b;
	}
	
	/**
	 * retrieves value of A
	 * @return value of A
	 */
	public A getA()
	{
		return datapointA;
	}
	
	/**
	 * retrieves value of B
	 * @return value of B
	 */
	public B getB()
	{
		return datapointB;
	}
}