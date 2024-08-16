package io.github.astrapi69.design.pattern.eventbus.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Unit test for {@link ApplicationEventBus} class.
 *
 * This test class demonstrates how to use the {@link ApplicationEventBus} to register subscribers,
 * post events, and verify that subscribers receive the correct events. <br>
 * Explanation:
 *
 * Test Setup: TestEventBus: A concrete implementation of ApplicationEventBus for testing.
 * TestSubscriber: A subscriber class that collects received events. TestEvent: A simple event class
 * with a message.
 *
 * Test Cases: testRegisterAndPostEvent: Tests that subscribers receive events after registration.
 * testUnregisterSubscriber: Verifies that unsubscribed subscribers do not receive events.
 * testNoSubscribers: Ensures that posting an event with no subscribers doesn't cause issues.
 *
 * Assertions: Verifies that subscribers receive the correct events and that the events are posted
 * in the correct order.
 *
 * This test class demonstrates how the ApplicationEventBus can be used, ensuring that events are
 * properly dispatched to subscribers and that subscribers can be registered and unregistered as
 * expected.
 */
class ApplicationEventBusTest
{

	private TestEventBus eventBus;
	private TestSubscriber subscriber1;
	private TestSubscriber subscriber2;
	private TestEvent event;

	/**
	 * Sets up the test environment before each test method execution.
	 *
	 * This method initializes the event bus, subscribers, and a test event.
	 */
	@BeforeEach
	void setUp()
	{
		eventBus = new TestEventBus();
		subscriber1 = new TestSubscriber();
		subscriber2 = new TestSubscriber();
		event = new TestEvent("Test Event");
	}

	/**
	 * Test method for registering subscribers and posting an event.
	 *
	 * This test verifies that when an event is posted, all registered subscribers receive the
	 * event.
	 */
	@Test
	void testRegisterAndPostEvent()
	{
		// Register subscribers
		eventBus.register(subscriber1);
		eventBus.register(subscriber2);

		// Post an event
		eventBus.post(event);

		// Check if the subscribers received the event
		assertEquals(1, subscriber1.getReceivedEvents().size());
		assertEquals(event, subscriber1.getReceivedEvents().get(0));

		assertEquals(1, subscriber2.getReceivedEvents().size());
		assertEquals(event, subscriber2.getReceivedEvents().get(0));
	}

	/**
	 * Test method for unregistering a subscriber.
	 *
	 * This test verifies that a subscriber does not receive events after being unregistered.
	 */
	@Test
	void testUnregisterSubscriber()
	{
		// Register and then unregister a subscriber
		eventBus.register(subscriber1);
		eventBus.unregister(subscriber1);

		// Post an event
		eventBus.post(event);

		// Verify that the unregistered subscriber did not receive the event
		assertTrue(subscriber1.getReceivedEvents().isEmpty());
	}

	/**
	 * Test method for posting an event with no subscribers.
	 *
	 * This test verifies that posting an event without any subscribers does not cause any errors or
	 * exceptions.
	 */
	@Test
	void testNoSubscribers()
	{
		// Post an event without any subscribers
		eventBus.post(event);

		// Ensure that no exceptions are thrown and nothing happens
		assertTrue(eventBus.getSubscribers().isEmpty());
	}

	/**
	 * Concrete implementation of {@link ApplicationEventBus} for testing purposes.
	 */
	private static class TestEventBus extends ApplicationEventBus<TestSubscriber, TestEvent>
	{

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onPost(TestSubscriber subscriber, TestEvent event)
		{
			subscriber.receive(event);
		}

		/**
		 * Retrieves the current list of subscribers.
		 *
		 * @return a list of subscribers
		 */
		public List<TestSubscriber> getSubscribers()
		{
			return new ArrayList<>(super.getSubscribers());
		}
	}

	/**
	 * A simple subscriber class used for testing.
	 *
	 * This class collects events that it receives for verification.
	 */
	private static class TestSubscriber
	{
		private final List<TestEvent> receivedEvents = new ArrayList<>();

		/**
		 * Receives an event and adds it to the list of received events.
		 *
		 * @param event
		 *            the event to receive
		 */
		public void receive(TestEvent event)
		{
			receivedEvents.add(event);
		}

		/**
		 * Retrieves the list of received events.
		 *
		 * @return the list of received events
		 */
		public List<TestEvent> getReceivedEvents()
		{
			return receivedEvents;
		}
	}

	/**
	 * A simple event class used for testing.
	 *
	 * This class represents an event with a message.
	 */
	private static class TestEvent
	{
		private final String message;

		/**
		 * Instantiates a new TestEvent with the given message.
		 *
		 * @param message
		 *            the message of the event
		 */
		public TestEvent(String message)
		{
			this.message = message;
		}

		/**
		 * Gets the message of the event.
		 *
		 * @return the message of the event
		 */
		public String getMessage()
		{
			return message;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object o)
		{
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			TestEvent testEvent = (TestEvent)o;
			return message.equals(testEvent.message);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode()
		{
			return message.hashCode();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString()
		{
			return "TestEvent{" + "message='" + message + '\'' + '}';
		}
	}
}
