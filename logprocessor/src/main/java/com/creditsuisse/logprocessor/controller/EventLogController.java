package com.creditsuisse.logprocessor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creditsuisse.logprocessor.service.EventLogProcessorService;

@RestController
public class EventLogController {
		
	@Autowired
	private EventLogProcessorService logProcessorService;

	
	@PostMapping("/saveEventLog")
	public ResponseEntity<String> saveEventLog(@RequestParam("filePath") String filePath) {
		boolean flag = logProcessorService.processData(filePath);
		if(flag) {
			return ResponseEntity.ok().body("Data saved successfully!!");
		} else {
			return ResponseEntity.internalServerError().body("Data processing Failed!!");
		}
	}
}
