package com.example.demo.entity;

import javax.persistence.Entity;

@Entity
public class EntityWithEnum extends BasePersistedObject {

	private String entityName;
	private UserType ut;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public UserType getUt() {
		return ut;
	}

	public void setUt(UserType ut) {
		this.ut = ut;
	}
}
