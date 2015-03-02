package com.cfeindia.b2bserviceapp.converter;

public interface DtoToEntityConverter<T1,T2> 
{
	T2 convert(T1 object);
}
