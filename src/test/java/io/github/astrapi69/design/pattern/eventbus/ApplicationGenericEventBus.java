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

import com.google.common.eventbus.EventBus;

import io.github.astrapi69.design.pattern.observer.event.EventListener;
import io.github.astrapi69.design.pattern.observer.event.EventObject;
import lombok.Getter;
import lombok.NonNull;

/**
 * The class {@link ApplicationGenericEventBus} serves as the central event bus for the application
 * It provides access to various event sources, including those related to navigation and the import
 * wizard model, and it uses an instance of {@link EventBus} from the Guava library to manage and
 * dispatch events
 */
public class ApplicationGenericEventBus
{

	/** The singleton instance of the {@link ApplicationGenericEventBus} */
	@Getter
	private static final ApplicationGenericEventBus instance = new ApplicationGenericEventBus();

	/** The underlying Guava {@link EventBus} for managing events */
	@Getter
	private final EventBus applicationEventBus = new EventBus();

	/**
	 * Private constructor to enforce singleton pattern
	 */
	private ApplicationGenericEventBus()
	{
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
	public static <T> void register(@NonNull final EventListener<EventObject<T>> listener,
		@NonNull final Class<T> eventSourceTypeClass)
	{
		GenericEventBus.register(listener, eventSourceTypeClass);
	}

	/**
	 * Unregisters the given {@link EventListener} with the specified event source type class from
	 * this EventBus
	 *
	 * @param <T>
	 *            the type parameter that represents the event source
	 * @param listener
	 *            the listener to register
	 * @param eventSourceTypeClass
	 *            the class type of the event source
	 */
	@SuppressWarnings("unchecked")
	public static <T> void unregister(@NonNull final EventListener<EventObject<T>> listener,
		@NonNull final Class<T> eventSourceTypeClass)
	{
		GenericEventBus.unregister(listener, eventSourceTypeClass);
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
	public static <T> void post(final T source)
	{
		GenericEventBus.post(source);
	}

}
