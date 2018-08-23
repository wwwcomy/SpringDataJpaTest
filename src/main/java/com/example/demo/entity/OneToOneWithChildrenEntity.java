package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OneToOneWithChildrenEntity extends BasePersistedObject {

	@Column(length = 200, name = "NAME")
	@NotEmpty
	private String name;
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	private Set<OneToOneWithChildrenEntity> children;
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "LOGIC_GROUP_ID")
	private LogicGroup logicGroup;
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private OneToOneWithChildrenEntity parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<OneToOneWithChildrenEntity> getChildren() {
		return children;
	}

	public void setChildren(Set<OneToOneWithChildrenEntity> children) {
		this.children = children;
	}

	public LogicGroup getLogicGroup() {
		return logicGroup;
	}

	public void setLogicGroup(LogicGroup logicGroup) {
		this.logicGroup = logicGroup;
	}

	@JsonIgnore
	public OneToOneWithChildrenEntity getParent() {
		return parent;
	}

	public void setParent(OneToOneWithChildrenEntity parent) {
		this.parent = parent;
	}

}
