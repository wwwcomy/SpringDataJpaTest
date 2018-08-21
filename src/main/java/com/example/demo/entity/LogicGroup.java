package com.example.demo.entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "logic_group")
public class LogicGroup {

	@Id
	@GeneratedValue
	@Column(name = "GROUP_ID", unique = true)
	private long groupId;

	@Column(name = "GROUP_NAME")
	private String groupName;

	@Column(name = "PARENT_ID")
	private Long parentId;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "BRIEF_DESCRIPTION")
	private String briefDescription;

	@Column(name = "DISPLAY_GROUP_NAME")
	private String displayGroupName;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "GROUP_PARAMS", joinColumns = @JoinColumn(name = "GROUP_ID"))
	@MapKeyColumn(name = "PARAM_KEY")
	@Column(name = "PARAM_VALUE")
	private Map<String, String> groupParams = new HashMap<String, String>();

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		System.out.println("Check groupName:" + groupName);
		this.groupName = groupName;
	}

	@JsonSetter(value = "groupName")
	public void setGroupNameOnly(String groupName) {
		System.out.println("setGroupNameOnly" + groupName);
		this.groupName = groupName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public String getDisplayGroupName() {
		return displayGroupName;
	}

	public void setDisplayGroupName(String displayGroupName) {
		this.displayGroupName = displayGroupName;
	}

	public Map<String, String> getGroupParams() {
		return groupParams;
	}

	public void setGroupParams(Map<String, String> groupParams) {
		this.groupParams = groupParams;
	}

	public void update(LogicGroup logicGroup) {
		this.displayGroupName = logicGroup.displayGroupName;
		this.groupName = logicGroup.groupName;
		updateParams(logicGroup.getGroupParams());
	}

	private void updateParams(Map<String, String> groupParams2) {
		if (groupParams2 == null) {
			return;
		} else {
			Set<Map.Entry<String, String>> entrySet = groupParams2.entrySet();
			Iterator<Map.Entry<String, String>> i = entrySet.iterator();
			while (i.hasNext()) {
				Map.Entry<String, String> entry = i.next();
				if (StringUtils.isEmpty(entry.getValue())) {
					groupParams.remove(entry.getKey());
				} else {
					groupParams.put(entry.getKey(), entry.getValue());
				}
			}
		}

	}
}