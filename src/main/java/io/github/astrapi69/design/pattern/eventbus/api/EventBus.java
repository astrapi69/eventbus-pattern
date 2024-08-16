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

import java.util.Collection;

/**
 * The {@link EventBus} interface defines the contract for an event bus system that allows objects
 * to register as subscribers, unregister from receiving events, and post events to all registered
 * subscribers.
 *
 * @param <S>
 *            the type of subscribers that can register to the event bus
 * @param <E>
 *            the type of events that can be posted to the event bus
 */
public interface EventBus<S, E>
{

	/**
	 * Registers the specified subscriber object to the event bus After registration, the subscriber
	 * will receive events posted to the bus.
	 *
	 * @param subscriber
	 *            the subscriber object to register
	 */
	void register(S subscriber);

	/**
	 * Unregisters the specified subscriber object from the event bus After unregistration, the
	 * subscriber will no longer receive events posted to the bus.
	 *
	 * @param subscriber
	 *            the subscriber object to unregister
	 */
	void unregister(S subscriber);

	/**
	 * Posts the specified event to all registered subscribers Each subscriber that is interested in
	 * the event will receive it.
	 *
	 * @param event
	 *            the event to post
	 */
	void post(E event);

	/**
	 * Retrieves all currently registered subscriber objects.
	 *
	 * @return a collection of all registered subscriber objects
	 */
	Collection<S> getSubscribers();
}
