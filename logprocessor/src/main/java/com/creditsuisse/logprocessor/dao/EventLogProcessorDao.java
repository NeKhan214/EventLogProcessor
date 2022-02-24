package com.creditsuisse.logprocessor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditsuisse.logprocessor.dto.EventLogDTO;

@Repository
public interface EventLogProcessorDao extends JpaRepository<EventLogDTO, Long>   
{  
	
}
