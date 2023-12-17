package com.sample.jpa.example.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ParentId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key1_parent;
	private Long key2_parent;
	private String key3_parent;

	public ParentId() {
		super();
	}

	public ParentId(String key1_parent, Long key2_parent, String key3_parent) {
		super();
		this.key1_parent = key1_parent;
		this.key2_parent = key2_parent;
		this.key3_parent = key3_parent;
	}

	public String getKey1_parent() {
		return key1_parent;
	}

	public void setKey1_parent(String key1_parent) {
		this.key1_parent = key1_parent;
	}

	public Long getKey2_parent() {
		return key2_parent;
	}

	public void setKey2_parent(Long key2_parent) {
		this.key2_parent = key2_parent;
	}

	public String getKey3_parent() {
		return key3_parent;
	}

	public void setKey3_parent(String key3_parent) {
		this.key3_parent = key3_parent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key1_parent, key2_parent, key3_parent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParentId other = (ParentId) obj;
		return Objects.equals(key1_parent, other.key1_parent) && Objects.equals(key2_parent, other.key2_parent)
				&& Objects.equals(key3_parent, other.key3_parent);
	}

}
