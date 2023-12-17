package com.sample.jpa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.jpa.example.entity.ChildId;
import com.sample.jpa.example.entity.ChildTableEntity;

public interface ChildRepository extends JpaRepository<ChildTableEntity,ChildId> {
	

}
