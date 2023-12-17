package com.sample.jpa.example.service;

import java.util.Optional;

public interface PurgeService {
	
	public void insertData();
	
	public void purgeDataByDate(Optional<Integer> inputPurgeDays, Optional<Integer> inputPurgePageSize);
	
	public void purgeDataByDateAndFlag(Optional<Integer> inputPurgeDays, Optional<Integer> inputPurgePageSize,
			Optional<Boolean> inputPurgeFlag);

}
