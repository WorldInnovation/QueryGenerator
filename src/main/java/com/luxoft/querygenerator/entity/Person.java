package com.luxoft.querygenerator.entity;

import com.luxoft.querygenerator.domain.Column;
import com.luxoft.querygenerator.domain.Entity;
import com.luxoft.querygenerator.domain.Id;
import com.luxoft.querygenerator.domain.Salary;


// ORM
@Entity(table = "persons")
public class Person
{
	@Column
	@Id
	private int id;
	@Column(name = "person_name")
	private String name;
	@Column
	@Salary
	private double salary;

}
