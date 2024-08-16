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
package io.github.astrapi69.design.pattern.eventbus;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import io.github.astrapi69.design.pattern.observer.event.EventListener;
import io.github.astrapi69.design.pattern.observer.event.EventObject;
import io.github.astrapi69.design.pattern.observer.event.EventSource;
import io.github.astrapi69.design.pattern.observer.event.EventSubject;
import lombok.Getter;
import lombok.NonNull;

/**
 * The {@code GenericEventBus} is a final utility class that provides a centralized event bus
 * mechanism for managing and dispatching events using {@code EventSource} objects. It maintains a
 * registry of event sources keyed by strings or class types, enabling efficient event dispatching
 * and management.
 */
public final class BaseEventBus
{
	// A map holding event sources keyed by their string representation
	private final Map<String, EventSource<?>> eventSources = new ConcurrentHashMap<>();

	/**
	 * The name of this event bus
	 */
	@Getter
	public final String name;

	/**
	 * Instantiates a new {@code BaseEventBus} object
	 */
	private BaseEventBus(String name)
	{
		this.name = name;
	}

	/**
	 * Retrieves the event source associated with the specified key
	 *
	 * @param key
	 *            the key associated with the event source
	 * @return the event source associated with the given key, or {@code null} if none is found
	 */
	public EventSource<?> get(final String key)
	{
		return eventSources.get(key);
	}

	/**
	 * Checks if the event bus contains an event source associated with the specified key
	 *
	 * @param key
	 *            the key to check
	 * @return {@code true} if the event source is present, {@code false} otherwise
	 */
	public boolean containsKey(final String key)
	{
		return eventSources.containsKey(key);
	}

	/**
	 * Checks if the event bus contains an event source associated with the specified class type
	 *
	 * @param <T>
	 *            the type of the event source
	 * @param eventSourceTypeClass
	 *            the class type of the event source to check
	 * @return {@code true} if the event source is present, {@code false} otherwise
	 */
	public <T> boolean containsKey(@NonNull final Class<T> eventSourceTypeClass)
	{
		return containsKey(eventSourceTypeClass.getSimpleName());
	}

	/**
	 * Retrieves the event source associated with the specified class type. If it does not exist, a
	 * new {@code EventSubject} is created and associated with the class type.
	 *
	 * @param <T>
	 *            the type of the event source
	 * @param eventSourceTypeClass
	 *            the class type of the event source
	 * @return the event source associated with the specified class type
	 */
	@SuppressWarnings("unchecked")
	public <T> EventSource<EventObject<T>> getEventSource(
		@NonNull final Class<T> eventSourceTypeClass)
	{
		if (!containsKey(eventSourceTypeClass))
		{
			put(eventSourceTypeClass.getSimpleName(), new EventSubject<EventObject<T>>());
		}
		return (EventSource<EventObject<T>>)get(eventSourceTypeClass.getSimpleName());
	}

	/**
	 * Registers a new {@link EventListener} with the specified event source type class to this
	 * EventBus instance
	 *
	 * @param <T>
	 *            the type parameter that represents the event source
	 * @param listener
	 *            the listener to register
	 * @param eventSourceTypeClass
	 *            the class type of the event source
	 */
	@SuppressWarnings("unchecked")
	public <T> void register(@NonNull final EventListener<EventObject<T>> listener,
		@NonNull final Class<T> eventSourceTypeClass)
	{
		EventSource<EventObject<T>> eventSource = getEventSource(eventSourceTypeClass);
		eventSource.add(listener);
	}

	/**
	 * Removes the event source associated with the specified class type
	 *
	 * @param <T>
	 *            the type of the event source
	 * @param eventSourceTypeClass
	 *            the class type of the event source to be removed
	 * @return an {@code Optional} containing the removed event source, or {@code Optional.empty()}
	 *         if none existed
	 */
	@SuppressWarnings("unchecked")
	public <T> Optional<EventSource<EventObject<T>>> remove(
		@NonNull final Class<T> eventSourceTypeClass)
	{
		if (containsKey(eventSourceTypeClass))
		{
			String classSimpleName = eventSourceTypeClass.getSimpleName();
			EventSource<EventObject<T>> removedEventSource = (EventSource<EventObject<T>>)eventSources
				.remove(classSimpleName);
			return Optional.of(removedEventSource);
		}
		return Optional.empty();
	}

	/**
	 * Retrieves the event source associated with the specified class type. If it does not exist,
	 * null will be returned
	 *
	 * @param <T>
	 *            the type of the event source
	 * @param eventSourceTypeClass
	 *            the class type of the event source
	 * @return the event source associated with the specified class type or null if it does not
	 *         exist
	 */
	@SuppressWarnings("unchecked")
	public <T> EventSource<EventObject<T>> get(@NonNull final Class<T> eventSourceTypeClass)
	{
		if (containsKey(eventSourceTypeClass))
		{
			String classSimpleName = eventSourceTypeClass.getSimpleName();
			EventSource<EventObject<T>> removedEventSource = (EventSource<EventObject<T>>)eventSources
				.get(classSimpleName);
			return removedEventSource;
		}
		return null;
	}

	/**
	 * Posts an event to the event bus. The event is dispatched to all registered listeners
	 * associated with the event's class type
	 *
	 * @param <T>
	 *            the type parameter representing the event source
	 * @param source
	 *            the source event to be posted
	 */
	@SuppressWarnings("unchecked")
	public <T> void post(@NonNull final T source)
	{
		Class<T> eventSourceTypeClass = (Class<T>)source.getClass();
		if (containsKey(eventSourceTypeClass))
		{
			get(eventSourceTypeClass).fireEvent(EventObject.of(source));
		}
	}

	/**
	 * Associates the specified event source with the specified key
	 *
	 * @param key
	 *            the key with which the event source is to be associated
	 * @param value
	 *            the event source to be associated with the key
	 * @return the previous event source associated with the key, or {@code null} if there was no
	 *         mapping for the key
	 */
	private synchronized EventSource<?> put(final String key, final EventSource<?> value)
	{
		return eventSources.put(key, value);
	}
}
