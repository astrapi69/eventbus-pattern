package io.github.astrapi69.design.pattern.eventbus.api;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The class {@link ApplicationEventBus} is a simple implementation of the {@link EventBus}
 * interface. It allows registering and unregistering subscribers, posting events to all registered
 * subscribers, and retrieving the list of current subscribers.
 *
 * @param <S>
 *            the type of subscribers that can register to the event bus
 * @param <E>
 *            the type of events that can be posted to the event bus
 */
public abstract class ApplicationEventBus<S, E> implements EventBus<S, E>
{

	private final List<S> subscribers;

	/**
	 * Instantiates a new {@code ApplicationEventBus}.
	 */
	public ApplicationEventBus()
	{
		this.subscribers = new CopyOnWriteArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void register(S subscriber)
	{
		if (subscriber != null && !subscribers.contains(subscriber))
		{
			subscribers.add(subscriber);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregister(S subscriber)
	{
		subscribers.remove(subscriber);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void post(E event)
	{
		for (S subscriber : subscribers)
		{
			onPost(subscriber, event);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<S> getSubscribers()
	{
		return subscribers;
	}

	/**
	 * Callback method for the specified event to the given subscriber
	 * 
	 * @param event
	 *            the event to post
	 * @param subscriber
	 *            the subscriber that is interested to the given event
	 */
	public abstract void onPost(S subscriber, E event);

}
