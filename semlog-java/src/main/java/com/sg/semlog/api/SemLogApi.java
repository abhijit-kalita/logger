package com.sg.semlog.api;

import com.sg.glfi.semlog.EventPublisher;
import com.sg.glfi.semlog.events.Event;

public class SemLogApi {
	
	public Event getTechnicalEvent(){
		
		return null;
	}
	
	
	public Event getFunctionalEvent(){
		
		return null;
	}
	
	public void publishEvent(Event event){
		EventPublisher.publish(event);
	}

}
