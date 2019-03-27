package com.foo.whoswhoofthecompany.model;

public class Employee {

	private Integer id;
	private String name;
	private Integer managerId;

	public Employee(Integer id, String name, Integer managerId) {
		this.id = id;
		this.name = name;
		this.managerId = managerId;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getManagerId() {
		return managerId;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
