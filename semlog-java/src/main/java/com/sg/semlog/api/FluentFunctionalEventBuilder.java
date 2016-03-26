package com.sg.semlog.api;

import com.sg.glfi.semlog.events.Event;
import com.sg.glfi.semlog.events.FunctionalEvent;
import com.sg.glfi.semlog.events.TechnicalEvent;
import com.sg.semlog.api.exceptions.EventBuilderException;

public class FluentFunctionalEventBuilder {
	private final Event event;
	
	   private FluentFunctionalEventBuilder() { 
		   event = new FunctionalEvent();
	   }
	   public static FluentFunctionalEventBuilder createEvent() {
	       return new FluentFunctionalEventBuilder();
	   }
	   public FluentFunctionalEventBuilder inIndustry(String industry) {
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
