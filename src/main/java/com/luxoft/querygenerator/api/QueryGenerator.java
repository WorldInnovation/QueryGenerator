package com.luxoft.querygenerator.api;

public interface QueryGenerator
{
	//
	String findAll(Class<?> clazz);

	String findById(Class<?> clazz);

	String insert(Class<?> clazz);

	String remove(Class<?> clazz);

	String update(Class<?> clazz);
}
