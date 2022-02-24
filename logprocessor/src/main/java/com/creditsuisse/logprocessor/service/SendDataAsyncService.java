package com.creditsuisse.logprocessor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.creditsuisse.logprocessor.dao.EventLogProcessorDao;
import com.creditsuisse.logprocessor.dto.EventLogDTO;

@Service
public class SendDataAsyncService {
	
	private static final Logger LOGGER = LogManager.getLogger(SendDataAsyncService.class);
	
	@Autowired
	EventLogProcessorDao eventLogProcessorDao;
	
	@Async
	public void persistData(List<EventLogDTO> eventLogList) {
		LOGGER.info("Persisting Processed Event Logs in DB with id: " + eventLogList.stream().map(EventLogDTO::getId).collect(Collectors.toList()));
		List<EventLogDTO> savedEventLogList = eventLogProcessorDao.saveAll(eventLogList);
		LOGGER.info("Event Logs Objects saved with id: " + savedEventLogList.stream().map(EventLogDTO::getId).collect(Collectors.toList()));
	}
}
