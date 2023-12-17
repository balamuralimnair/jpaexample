package com.sample.jpa.example.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.jpa.example.entity.ParentId;
import com.sample.jpa.example.entity.ParentTableEntity;

public interface ParentRepository extends JpaRepository<ParentTableEntity, ParentId> {

	@Query(value = "select count(1) from manifest.parenttable a where date(a.created_tmstp)<:purgeDate and a.purge_flag=:purgeFlag", nativeQuery = true)
	public int countOfrecordsToBePurgedByDateAndFlag(Date purgeDate, boolean purgeFlag);

	@Query(value = "select count(1) from manifest.parenttable a where date(a.created_tmstp)<:purgeDate", nativeQuery = true)
	public int countOfRecordsToBePurged(Date purgeDate);

	@Query(value = "select a.* from manifest.parenttable a where date(a.created_tmstp)<:purgeDate and a.purge_flag=:purgeFlag", countQuery = "select count(1) from manifest.parenttable a where date(a.created_tmstp)<:purgeDate and a.purge_flag=:purgeFlag", nativeQuery = true)
	public Page<ParentTableEntity> recordsToBePurgedByDateAndFlag(Date purgeDate, boolean purgeFlag, Pageable pageable);

	@Query(value = "select a.* from manifest.parenttable a where date(a.created_tmstp)<:purgeDate", countQuery = "select count(1) from manifest.parenttable a where date(a.created_tmstp)<:purgeDate", nativeQuery = true)
	public Page<ParentTableEntity> recordsToBePurgedByDate(Date purgeDate, Pageable pageable);

}
