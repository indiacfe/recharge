/**
 * 
 */
package com.cfeindia.b2bserviceapp.converter;

/**
 * @author C.H.I.R.A.G
 *
 */
public interface EntityToDTOConverter<T1, T2> {
	T2 convert(T1 object);
}
