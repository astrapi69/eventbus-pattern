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
package io.github.astrapi69.design.pattern.eventbus.eventobject;

import lombok.Getter;
import io.github.astrapi69.design.pattern.eventbus.ApplicationEventBus;
import io.github.astrapi69.design.pattern.observer.event.EventListener;
import io.github.astrapi69.design.pattern.observer.event.EventObject;

/**
 * The {@code ImportProgressPanel} class is responsible for handling events related to the
 * {@code ImportWizardModel}. It listens for events on the {@code ApplicationEventBus} and updates
 * its internal state when an event occurs.
 */
public class ImportProgressPanel implements EventListener<EventObject<ImportWizardModel>>
{

	/** The model that this panel is monitoring */
	@Getter
	ImportWizardModel importWizardModel;

	/**
	 * Constructs a new {@code ImportProgressPanel} and registers it as a listener for
	 * {@code ImportWizardModel} events on the {@code ApplicationEventBus}
	 */
	ImportProgressPanel()
	{
		// Register as listener...
		ApplicationEventBus.register(this, ImportWizardModel.class);
	}

	/**
	 * Handles the event when an {@code ImportWizardModel} event is fired. This method updates the
	 * internal {@code importWizardModel} with the event's source.
	 *
	 * @param event
	 *            the event object containing the updated {@code ImportWizardModel}
	 */
	@Override
	public void onEvent(EventObject<ImportWizardModel> event)
	{
		this.importWizardModel = event.getSource();
	}
}
