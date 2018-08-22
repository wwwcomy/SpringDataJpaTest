package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EntityWithEnum extends BasePersistedObject {

	private String entityName;
	@Column(name = "user_type")
	private int userTypeFlag;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * Without @Enumerated, the default value saved is integer, which is the order
	 * defined in enum.
	 * 
	 * @return
	 */
	public UserType getUserType() {
		return UserType.parse(userTypeFlag);
	}

	public void setUserType(UserType ut) {
		this.userTypeFlag = ut.getValue();
	}
}
