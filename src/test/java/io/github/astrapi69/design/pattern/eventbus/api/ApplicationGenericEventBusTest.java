/**
 * The MIT License
 *
 * Copyright (C) 2022 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.design.pattern.eventbus.api;

import static org.junit.jupiter.api.Assertions.*;

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
class ApplicationGenericEventBusTest
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

}
