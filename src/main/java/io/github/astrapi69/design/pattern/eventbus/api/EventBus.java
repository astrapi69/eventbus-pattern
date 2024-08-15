package io.github.astrapi69.design.pattern.eventbus.api;

/**
 * The {@link EventBus} interface defines the contract for an event bus system
 * that allows objects to register as subscribers, unregister from receiving events,
 * and post events to all registered subscribers.
 *
 * @param <S> the type of subscribers that can register to the event bus
 * @param <E> the type of events that can be posted to the event bus
 */
public interface EventBus<S, E> {

	/**
	 * Registers the specified subscriber object to the event bus
	 * After registration, the subscriber will receive events posted to the bus.
	 *
	 * @param subscriber the subscriber object to register
	 */
	void register(S subscriber);

	/**
	 * Unregisters the specified subscriber object from the event bus
	 * After unregistration, the subscriber will no longer receive events posted to the bus.
	 *
	 * @param subscriber the subscriber object to unregister
	 */
	void unregister(S subscriber);

	/**
	 * Posts the specified event to all registered subscribers
	 * Each subscriber that is interested in the event will receive it.
	 *
	 * @param event the event to post
	 */
	void post(E event);

}
