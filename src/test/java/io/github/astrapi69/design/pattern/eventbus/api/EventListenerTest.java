package io.github.astrapi69.design.pattern.eventbus.api;

import io.github.astrapi69.design.pattern.observer.event.EventListener;
import io.github.astrapi69.design.pattern.observer.event.EventObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventListenerTest {

    private ApplicationEventBus<EventListener<EventObject<String>>, EventObject<String>> eventBus;
    private StringEventListener listener;
    private EventObject<String> event;

    @BeforeEach
    void setUp() {
        eventBus = new ApplicationEventBus<>();
        listener = new StringEventListener();
        event = new EventObject<>("Test Event");
    }

    @Test
    void testRegisterAndPostEvent() {
        // Register the listener
        eventBus.register(listener);

        // Post an event
        eventBus.post(event);

        // Check if the event was received
        assertNotNull(listener.getReceivedEvent(), "The event was not received by the listener.");

        // Verify that the listener received the correct event
        assertEquals("Test Event", listener.getReceivedEvent().getSource(), "The event source did not match.");
    }

    @Test
    void testUnregisterListener() {
        // Register and then unregister the listener
        eventBus.register(listener);
        eventBus.unregister(listener);

        // Post an event
        eventBus.post(event);

        // Verify that the listener did not receive the event
        assertNull(listener.getReceivedEvent(), "The event should not have been received after unregistration.");
    }

    @Test
    void testRemoveEventSource() {
        // Register the listener
        eventBus.register(listener);

        // Remove the event source
        eventBus.remove(StringEventListener.class);

        // Post an event
        eventBus.post(event);

        // Verify that the listener did not receive the event
        assertNull(listener.getReceivedEvent(), "The event should not have been received after removing the event source.");
    }
}

class StringEventListener implements EventListener<EventObject<String>> {

    private EventObject<String> receivedEvent;

    @Override
    public void onEvent(EventObject<String> event) {
        this.receivedEvent = event;
    }

    public EventObject<String> getReceivedEvent() {
        return receivedEvent;
    }
}
