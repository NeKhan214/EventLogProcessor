package logprocessor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.creditsuisse.logprocessor.service.EventLogProcessorService;
import com.creditsuisse.logprocessor.service.SendDataAsyncService;

class EventLogProcessorServiceTest {
	
	private EventLogProcessorService eventLogProcessorService;
	
	void setUp() {
		eventLogProcessorService = new EventLogProcessorService(new SendDataAsyncService());
	}
	

	@Test
	void testProcessDataNegative() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			eventLogProcessorService.processData("");
		});
		assertTrue(exception.getClass().equals(NullPointerException.class));
	}
}
