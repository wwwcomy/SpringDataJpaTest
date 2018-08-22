package com.example.demo.entity;

public enum UserType {

	NONE(0), EXTERNAL(1), INTERNAL(2), INTERNAL_AND_EXTERNAL(3), MACHINE_AND_EXTERNAL(5), MACHINE_AND_INTERNAL(
			6), MACHINE(4), MACHINE_AND_INTERNAL_AND_EXTERNAL(7);
	private int value;

	public static UserType parse(int id) {
		UserType right = NONE; // Default
		for (UserType item : UserType.values()) {
			if (item.getValue() == id) {
				return item;
			}
		}
		return right;
	}

	private UserType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
