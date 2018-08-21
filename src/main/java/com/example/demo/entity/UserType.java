package com.example.demo.entity;

public enum UserType {

	EXTERNAL(1), INTERNAL(2), INTERNAL_AND_EXTERNAL(3), MACHINE(4), MACHINE_AND_INTERNAL(6);
	private int value;

	private UserType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
