/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
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

import lombok.Getter;
import lombok.NonNull;

import com.google.common.eventbus.EventBus;

import io.github.astrapi69.design.pattern.eventbus.eventobject.ImportWizardModel;
import io.github.astrapi69.design.pattern.observer.event.EventListener;
import io.github.astrapi69.design.pattern.observer.event.EventObject;
import io.github.astrapi69.design.pattern.observer.event.EventSource;
import io.github.astrapi69.lang.TypeArgumentsExtensions;

/**
 * The class {@link ApplicationEventBus} serves as the central event bus for the application It
 * provides access to various event sources, including those related to navigation and the import
 * wizard model, and it uses an instance of {@link EventBus} from the Guava library to manage and
 * dispatch events
 */
public class ApplicationEventBus
{

	/** The singleton instance of the {@link ApplicationEventBus} */
	@Getter
	private static final ApplicationEventBus instance = new ApplicationEventBus();

	/** The underlying Guava {@link EventBus} for managing events */
	@Getter
	private final EventBus applicationEventBus = new EventBus();

	/**
	 * Private constructor to enforce singleton pattern
	 */
	private ApplicationEventBus()
	{
	}

	@SuppressWarnings("unchecked")
	public static <T> void register(@NonNull final EventListener<EventObject<T>> listener,
		@NonNull final Class<T> eventSourceTypeClass)
	{
		GenericEventBus.register(listener, eventSourceTypeClass);
	}


	public static Class<?> getBaseClass(final Class<?> childClass)
	{
		if (childClass == null || childClass.equals(Object.class))
		{
			return childClass;
		}
		Class<?> superClass = childClass.getSuperclass();
		if (superClass != null && superClass.equals(Object.class))
		{
			return childClass;
		}
		while (superClass != null && !(superClass.getSuperclass() != null
			&& superClass.getSuperclass().equals(Object.class)))
		{
			superClass = superClass.getSuperclass();
		}
		return superClass;
	}


	/**
	 * Retrieves an event source by its key
	 *
	 * @param key
	 *            the key associated with the event source
	 * @return the event source associated with the given key, or {@code null} if none is found
	 */
	public static EventSource<?> get(final String key)
	{
		return GenericEventBus.get(key);
	}

	/**
	 * Retrieves the event source for navigation state events
	 *
	 * @return the event source associated with {@link NavigationEventState} events
	 */
	public static EventSource<EventObject<NavigationEventState>> getImportNavigationState()
	{
		return GenericEventBus.getEventSource(NavigationEventState.class);
	}

	/**
	 * Retrieves the event source for import wizard model events
	 *
	 * @return the event source associated with {@link ImportWizardModel} events
	 */
	public static EventSource<EventObject<ImportWizardModel>> getImportWizardModel()
	{
		return GenericEventBus.getEventSource(ImportWizardModel.class);
	}
}
