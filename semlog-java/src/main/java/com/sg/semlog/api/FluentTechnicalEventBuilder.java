package com.sg.semlog.api;

import com.sg.glfi.semlog.events.Event;
import com.sg.glfi.semlog.events.TechnicalEvent;
import com.sg.semlog.api.exceptions.EventBuilderException;

public class FluentTechnicalEventBuilder {
	private final Event event;
	
	   private FluentTechnicalEventBuilder() { 
		   event = new TechnicalEvent();
	   }
	   public static FluentTechnicalEventBuilder createEvent() {
	       return new FluentTechnicalEventBuilder();
	   }
	   public FluentTechnicalEventBuilder inIndustry(String industry) {
	      //fireItem.setIndustry(industry);
	      return this;
	   }
	   // other chained methods follow a similar style returning "this" ...
	   public Event build() throws EventBuilderException {
	      validate();
	      return event;
	   }
	   private void validate() throws EventBuilderException {
	     // do all validations on the fire item itself and throw an exception if something fails
	   }
}
