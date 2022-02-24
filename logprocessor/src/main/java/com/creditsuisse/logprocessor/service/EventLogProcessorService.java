package com.creditsuisse.logprocessor.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.creditsuisse.logprocessor.dto.EventLogDTO;
import com.creditsuisse.logprocessor.model.EventLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventLogProcessorService {
	
	private Map<String, EventLog> eventLogMap = new LinkedHashMap<String, EventLog>();
	private List<EventLogDTO> eventLogList = new LinkedList<EventLogDTO>();
	private SendDataAsyncService dataAsync;
	private static final Logger LOGGER = LogManager.getLogger(EventLogProcessorService.class);
	
	@Value("${batch.size:10}")
	private Integer batchSize;
	
	@Autowired
	public EventLogProcessorService(SendDataAsyncService dataAsync) {
		this.dataAsync = dataAsync;
	}
	
	public boolean processData(String filePath) {
		Stream<String> logStream = null;
		try {
			LOGGER.info("LogFile to be processed: "+ filePath);
			logStream = Files.lines(Paths.get(filePath));
			ObjectMapper mapper = new ObjectMapper();
			LOGGER.info("EventLogFile Data Processing started..");
			logStream.forEach(log -> {
				try {
					EventLog newEventLog = mapper.readValue(log, EventLog.class);
					EventLog prevEventLog = eventLogMap.get(newEventLog.getId());
					if(eventLogList.size() > batchSize) {
						dataAsync.persistData(new ArrayList<EventLogDTO>(eventLogList));
						eventLogList.clear();
					}
					if(prevEventLog != null && !prevEventLog.getState().equals(newEventLog.getState())) {
						long duration = Math.abs(newEventLog.getTimestamp().getTime() - 
								prevEventLog.getTimestamp().getTime());
						boolean flag = duration > 4 ? Boolean.TRUE : Boolean.FALSE;
						eventLogMap.remove(prevEventLog.getId());
						eventLogList.add(new EventLogDTO(newEventLog.getId(),
								duration, flag, newEventLog.getType(), newEventLog.getHost()));
					} else {
						eventLogMap.put(newEventLog.getId(), newEventLog);
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			});
			if(!eventLogList.isEmpty()) {
				dataAsync.persistData(eventLogList);
			}
		} catch (Exception exception) {
			LOGGER.error("EventLog File Data Processing Failed: " + exception.getMessage());
			return false;
		} finally {
			logStream.close();
		}
		return true;
	}
	
}
