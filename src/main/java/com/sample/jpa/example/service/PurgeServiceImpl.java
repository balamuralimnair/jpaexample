package com.sample.jpa.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sample.jpa.example.entity.ChildId;
import com.sample.jpa.example.entity.ChildTableEntity;
import com.sample.jpa.example.entity.ParentId;
import com.sample.jpa.example.entity.ParentTableEntity;
import com.sample.jpa.example.repository.ParentRepository;

@Service
public class PurgeServiceImpl implements PurgeService{

	@Autowired
	ParentRepository parentRepository;

	@Value("${db.purge.days: 7}")
	int purgeDays;

	@Value("${db.purge.page.size: 10000}")
	int purgePageSize;

	boolean purgeFlag = true;

	@Override
	@Scheduled(cron = "${cron.expression: 0 0 0/6 ? * * *}")
	public void purgeDataByDateAndFlag(Optional<Integer> inputPurgeDays, Optional<Integer> inputPurgePageSize,
			Optional<Boolean> inputPurgeFlag) {
		try {
			if (inputPurgeDays.isPresent())
				this.purgeDays = inputPurgeDays.get();
			if (inputPurgePageSize.isPresent())
				this.purgePageSize = inputPurgePageSize.get();
			if (inputPurgeFlag.isPresent())
				this.purgeFlag = inputPurgeFlag.get();
		} catch(Exception ex) {
			System.out.println("Error Occurred: " + ex.getMessage());
		}
		

		try {
			LocalDate todayDate = LocalDate.now();
			LocalDate localPurgeDate = todayDate.minusDays(purgeDays);
			Date purgeDate = java.sql.Date.valueOf(localPurgeDate);

			int purgeRecordCount = parentRepository.countOfrecordsToBePurgedByDateAndFlag(purgeDate, this.purgeFlag);

			int purgeCompletedCount = 0;

			while (purgeRecordCount > 0) {
				Pageable pageable = PageRequest.of(0, this.purgePageSize);
				Page<ParentTableEntity> deletePage = this.parentRepository.recordsToBePurgedByDateAndFlag(purgeDate,
						purgeFlag, pageable);
				if (deletePage.getContent().size() > 0) {
					this.parentRepository.deleteAll(deletePage.getContent());
					purgeCompletedCount += deletePage.getContent().size();
					purgeRecordCount = parentRepository.countOfrecordsToBePurgedByDateAndFlag(purgeDate,
							this.purgeFlag);
				}
			}
			System.out.println("Purged Records: " + purgeCompletedCount);
		} catch (Exception ex) {
			System.out.println("Encountered Exception: " + ex.getMessage());
		}

	}

	@Override
	public void purgeDataByDate(Optional<Integer> inputPurgeDays, Optional<Integer> inputPurgePageSize) {

		if (inputPurgeDays.isPresent())
			this.purgeDays = inputPurgeDays.get();
		if (inputPurgePageSize.isPresent())
			this.purgePageSize = inputPurgePageSize.get();

		try {
			LocalDate todayDate = LocalDate.now();
			LocalDate localPurgeDate = todayDate.minusDays(purgeDays);
			Date purgeDate = java.sql.Date.valueOf(localPurgeDate);

			int purgeRecordCount = parentRepository.countOfRecordsToBePurged(purgeDate);

			int purgeCompletedCount = 0;

			while (purgeRecordCount > 0) {
				Pageable pageable = PageRequest.of(0, this.purgePageSize);
				Page<ParentTableEntity> deletePage = this.parentRepository.recordsToBePurgedByDate(purgeDate, pageable);
				if (deletePage.getContent().size() > 0) {
					this.parentRepository.deleteAll(deletePage.getContent());
					purgeCompletedCount += deletePage.getContent().size();
					purgeRecordCount = parentRepository.countOfRecordsToBePurged(purgeDate);
				}
			}
			System.out.println("Purged Records: " + purgeCompletedCount);
		} catch (Exception ex) {
			System.out.println("Encountered Exception: " + ex.getMessage());
		}

	}
	
	@Override
	public void insertData() {
		for (int i= 0; i< 1000; i++) {
			ParentTableEntity parentEntity = new ParentTableEntity();
					Date createTimestamp = new java.util.Date();
					parentEntity.setParentId(new ParentId(String.valueOf(i),(long) i,String.valueOf(i))); 
					parentEntity.setPurgeFlag(i%2==0? true : false);
					parentEntity.setParentDesc("Parent Number: " + i);
					parentEntity.setCreatedTimetamp(createTimestamp);
					parentEntity.setChildTableEntity(generateChildEntity(i,createTimestamp));
					this.parentRepository.save(parentEntity);
		}
		 //Checking if save is upsert
		int i=19;
		ParentTableEntity parentEntity = new ParentTableEntity();
		Date createTimestamp = new java.util.Date();
		parentEntity.setParentId(new ParentId(String.valueOf(i),(long) i,String.valueOf(i))); 
		parentEntity.setPurgeFlag(i%2==0? true : false);
		parentEntity.setParentDesc("Parent Number: 7744");
		parentEntity.setCreatedTimetamp(createTimestamp);
		parentEntity.setChildTableEntity(generateChildEntity(i,createTimestamp));
		this.parentRepository.save(parentEntity);
		
	}
	
	public List<ChildTableEntity> generateChildEntity(int i, Date createTimestamp) {
		List<ChildTableEntity> childTableEntityArray = new ArrayList();
		for(int j=0;j<10;j++) {
			ChildTableEntity childTableEntity  = new ChildTableEntity();
			childTableEntity.setChildId(new ChildId(j,String.valueOf(i),(long) i,String.valueOf(i)));
			childTableEntity.setChildDesc("Child Number: " + j + " for Parent: " + i);
			childTableEntity.setCreatedTimetamp(createTimestamp);
			childTableEntityArray.add(childTableEntity);
		}
		return childTableEntityArray;
	}

}
