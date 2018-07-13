package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class VersionedEntityPk implements Serializable {
	private static final long serialVersionUID = 6354232374624420251L;
	private Long id;
	private Double version;

	public VersionedEntityPk() {
	}

	public VersionedEntityPk(Long id2, double version2) {
		this.id = id2;
		this.version = version2;
	}

	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getVersion() {
		return version;
	}

	public void setVersion(Double version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "VersionedEntityPk [id=" + id + ", version=" + version + "]";
	}

}
