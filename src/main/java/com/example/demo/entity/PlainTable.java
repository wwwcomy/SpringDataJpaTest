package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLAIN_TABLE")
public class PlainTable {

	@Id
	private String prefix;
	private boolean wtf;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean isWtf() {
		return wtf;
	}

	public void setWtf(boolean wtf) {
		this.wtf = wtf;
	}

}
