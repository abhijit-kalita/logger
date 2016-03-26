package com.sg.glfi.semlog.events.sink;

import org.junit.Before;
import org.junit.Test;

import com.sg.glfi.semlog.events.Event;
import com.sg.glfi.semlog.events.FunctionalEvent;
import com.sg.glfi.semlog.events.TechnicalEvent;
import com.sg.glfi.semlog.events.sink.RxEventBus;

import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import static org.junit.Assert.*;

public class RxEventBusTest {
	private RxEventBus bus;
	private Event handledEvent;

	@Before
	public void setUp() {
		bus = new RxEventBus();
		handledEvent = null;
	}


//	@Test
//	public void publish_unknown_event() {
//		Event event = new UnhandledEvent();
//		bus.post(event, new Action1<Event>() {
//			@Override
//			public void call(Event event) {
//				handledEvent = event;
//			}
//		});
//		assertEquals(handledEvent, event);
//	}

//	@Test
//	public void post_unhandled_with_unsubscribe() {
//		Event event = new UnhandledEvent();
//		Subscription subscription = bus.subscribe(UnhandledEvent.class,
//				new Action1<UnhandledEvent>() {
//					@Override
//					public void call(UnhandledEvent event) {
//						fail();
//					}
//				});
//		subscription.unsubscribe();
//		bus.post(event, new Action1<Event>() {
//			@Override
//			public void call(Event event) {
//				handledEvent = event;
//			}
//		});
//		assertEquals(handledEvent, event);
//	}

	
//	
//	
//	@Test
//	public void post_unhandled_with_other_event() {
//		Event event = new UnhandledEvent();
//		Subscription subscription = bus.subscribe(FunctionalEvent.class,
//				new Action1<FunctionalEvent>() {
//					@Override
//					public void call(FunctionalEvent event) {
//						fail();
//					}
//				});
//		bus.post(event, new Action1<Event>() {
//			@Override
//			public void call(Event event) {
//				handledEvent = event;
//			}
//		});
//		assertEquals(handledEvent, event);
//	}
//
//	
//	
//	@Test
//	public void publishTechnicalEvent() {
//		Event event = new TechnicalEvent();
//		Subscription subscription = bus.subscribe(TechnicalEvent.class,
//				new Action1<TechnicalEvent>() {
//					@Override
//					public void call(TechnicalEvent event) {
//						handledEvent = event;
//					}
//				});
//		bus.post(event);
//		subscription.unsubscribe();
//		assertEquals(handledEvent, event);
//	}
//	
//	@Test
//	public void publishFunctionalEvent() {
//		Event event = new FunctionalEvent();
//		Subscription subscription = bus.subscribe(FunctionalEvent.class,
//				new Action1<FunctionalEvent>() {
//					@Override
//					public void call(FunctionalEvent event) {
//						handledEvent = event;
//					}
//				});
//		bus.post(event);
//		subscription.unsubscribe();
//		assertEquals(handledEvent, event);
//	}
//
//	
//	
//	@Test
//	public void publishEventTwice() {
//		Event event = new FunctionalEvent();
//		Action1<FunctionalEvent> handler = new Action1<FunctionalEvent>() {
//			@Override
//			public void call(FunctionalEvent event) {
//			}
//		};
//		Subscription subscription = new CompositeSubscription(bus.subscribe(
//				FunctionalEvent.class, handler), bus.subscribe(
//				FunctionalEvent.class, handler));
//		bus.post(event);
//		subscription.unsubscribe();
//		//assertEquals(2, event.handledCount);
//	}

	
	
	@Test
	public void publishEventInThread() throws InterruptedException {
		final Thread mainThread = Thread.currentThread();
		final Object lock = new Object();
		Subscription subscription = bus.subscribe(FunctionalEvent.class,
				new Action1<FunctionalEvent>() {
					@Override
					public void call(FunctionalEvent event) {
						assertNotEquals(mainThread, Thread.currentThread());
						handledEvent = event;
						synchronized (lock) {
							lock.notifyAll();
						}
					}
				}, Schedulers.newThread());
		bus.post(new FunctionalEvent());
		synchronized (lock) {
			lock.wait(1000);
		}
		subscription.unsubscribe();
		assertNotNull(handledEvent);
	}

	static class UnhandledEvent implements Event {

		@Override
		public void printtest() {
			// TODO Auto-generated method stub

		}
	}
}
