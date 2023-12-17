package com.sample.jpa.example.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "parenttable", schema = "manifest")
public class ParentTableEntity {

	@EmbeddedId
	private ParentId parentId;

	@Column(name = "purge_flag")
	private boolean purgeFlag;

	@Column(name = "parent_desc")
	private String parentDesc;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parentTableEntity", fetch = FetchType.EAGER)
	private List<ChildTableEntity> childTableEntity;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_tmstp")
	private Date createdTimetamp;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_tmstp")
	private Date updatedTimestamp;

	public ParentId getParentId() {
		return parentId;
	}

	public void setParentId(ParentId parentId) {
		this.parentId = parentId;
	}

	public boolean isPurgeFlag() {
		return purgeFlag;
	}

	public void setPurgeFlag(boolean purgeFlag) {
		this.purgeFlag = purgeFlag;
	}

	public String getParentDesc() {
		return parentDesc;
	}

	public void setParentDesc(String parentDesc) {
		this.parentDesc = parentDesc;
	}

	public List<ChildTableEntity> getChildTableEntity() {
		return childTableEntity;
	}

	public void setChildTableEntity(List<ChildTableEntity> childTableEntity) {
		this.childTableEntity = childTableEntity;
	}

	public Date getCreatedTimetamp() {
		return createdTimetamp;
	}

	public void setCreatedTimetamp(Date createdTimetamp) {
		this.createdTimetamp = createdTimetamp;
	}

	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	@Override
	public String toString() {
		return "ParentTableEntity [parentId=" + parentId + ", purgeFlag=" + purgeFlag + ", parentDesc=" + parentDesc
				+ ", childTableEntity=" + childTableEntity + ", createdTimetamp=" + createdTimetamp
				+ ", updatedTimestamp=" + updatedTimestamp + "]";
	}

}
