package io.github.astrapi69.design.pattern.eventbus.api;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import lombok.NonNull;
import io.github.astrapi69.design.pattern.observer.event.EventListener;
import io.github.astrapi69.design.pattern.observer.event.EventObject;
import io.github.astrapi69.design.pattern.observer.event.EventSource;
import io.github.astrapi69.design.pattern.observer.event.EventSubject;

/**
 * The {@code ApplicationEventBus} is a utility class that provides a centralized event bus
 * mechanism for managing and dispatching events using {@code EventSource} objects.
 * It maintains a registry of event sources keyed by strings or class types, enabling efficient event dispatching and management.
 *
 * @param <S> the type of the subscriber
 * @param <E> the type of the event
 */
public class ApplicationEventBus<S, E> implements EventBus<S, E>
{
	// A map holding event sources keyed by their string representation
	private final Map<String, EventSource<E>> eventSources = new ConcurrentHashMap<>();

	@Override
	public void register(S subscriber)
	{
		if (subscriber != null && !containsSubscriber(subscriber))
		{
			EventSource<E> eventSource = getEventSource(subscriber.getClass());
			eventSource.add((EventListener<E>) subscriber);
			System.out.println("Registered subscriber: " + subscriber.getClass().getSimpleName());
		}
	}

	@Override
	public void unregister(S subscriber)
	{
		if (subscriber != null && containsSubscriber(subscriber))
		{
			EventSource<E> eventSource = getEventSource(subscriber.getClass());
			eventSource.remove((EventListener<E>) subscriber);
			System.out.println("Unregistered subscriber: " + subscriber.getClass().getSimpleName());
		}
	}

	@Override
	public void post(E event)
	{
		String eventTypeName = event.getClass().getSimpleName();

		eventSources.forEach((key, eventSource) -> {
			if (key.equals(eventTypeName)) {
				System.out.println("Posting event: " + event + " to source: " + eventSource);
				eventSource.fireEvent(event);
			}
		});

		System.out.println("No event source found for: " + eventTypeName);
	}

	private boolean containsSubscriber(S subscriber)
	{
		return eventSources.containsKey(subscriber.getClass().getSimpleName());
	}

	private boolean containsEventSource(Class<?> eventSourceTypeClass)
	{
		return eventSources.containsKey(eventSourceTypeClass.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	private <T> EventSource<E> getEventSource(Class<T> eventSourceTypeClass)
	{
		String key = eventSourceTypeClass.getSimpleName();
		return (EventSource<E>) eventSources.computeIfAbsent(key, k -> new EventSubject<>());
	}

	public <T> Optional<EventSource<E>> remove(@NonNull final Class<T> eventSourceTypeClass)
	{
		if (containsEventSource(eventSourceTypeClass))
		{
			return Optional.ofNullable(eventSources.remove(eventSourceTypeClass.getSimpleName()));
		}
		return Optional.empty();
	}
}
