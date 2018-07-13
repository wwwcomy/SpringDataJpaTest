package com.example.demo.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author wwwcomy
 * @date 2018/07/12
 */
@Entity
public class VersionedEntity {

	private VersionedEntityPk pk;
	private String name;
	private String description;

	@EmbeddedId
	@JsonProperty(access = Access.WRITE_ONLY)
	public VersionedEntityPk getPk() {
		return pk;
	}

	public void setPk(VersionedEntityPk pk) {
		this.pk = pk;
	}

	@Transient
	public Long getId() {
		if (pk == null) {
			return null;
		}
		return pk.getId();
	}

	@Transient
	public Double getVersion() {
		if (pk == null) {
			return null;
		}
		return pk.getVersion();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
