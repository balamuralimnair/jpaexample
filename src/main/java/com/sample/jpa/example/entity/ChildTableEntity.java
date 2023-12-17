package com.sample.jpa.example.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "childtable", schema = "manifest")
public class ChildTableEntity {

	@EmbeddedId
	private ChildId childId;

	@Column(name = "child_desc")
	private String childDesc;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_tmstp")
	private Date createdTimetamp;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_tmstp")
	private Date updatedTimestamp;

	@ManyToOne
	@MapsId(value = "childId")
	@JoinColumns(value = { @JoinColumn(name = "key1_parent", referencedColumnName = "key1_parent"),
			@JoinColumn(name = "key2_parent", referencedColumnName = "key2_parent"),
			@JoinColumn(name = "key3_parent", referencedColumnName = "key3_parent") })
	private ParentTableEntity parentTableEntity;

	public ChildId getChildId() {
		return childId;
	}

	public void setChildId(ChildId childId) {
		this.childId = childId;
	}

	public String getChildDesc() {
		return childDesc;
	}

	public void setChildDesc(String childDesc) {
		this.childDesc = childDesc;
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

	public ParentTableEntity getParentTableEntity() {
		return parentTableEntity;
	}

	public void setParentTableEntity(ParentTableEntity parentTableEntity) {
		this.parentTableEntity = parentTableEntity;
	}

	@Override
	public String toString() {
		return "ChildTableEntity [childId=" + childId + ", childDesc=" + childDesc + ", createdTimetamp="
				+ createdTimetamp + ", updatedTimestamp=" + updatedTimestamp + ", parentTableEntity="
				+ parentTableEntity + "]";
	}

}
