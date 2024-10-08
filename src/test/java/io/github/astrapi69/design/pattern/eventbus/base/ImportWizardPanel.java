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
package io.github.astrapi69.design.pattern.eventbus.base;

import io.github.astrapi69.design.pattern.eventbus.eventobject.ImportWizardModel;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link ImportWizardPanel} represents a panel in the import wizard It holds an instance
 * of {@link ImportWizardModel} and can fire events related to the model
 */
public class ImportWizardPanel
{
	/** The model associated with this panel */
	@Getter
	@Setter
	ImportWizardModel model;

	/**
	 * Constructs a new {@code ImportWizardPanel} with a default model The default model is
	 * initialized with a bundle application name "foobar"
	 */
	ImportWizardPanel()
	{
		this.model = ImportWizardModel.builder().bundleAppName("foobar").build();
	}

	/**
	 * Fires a new event to the {@link ApplicationBaseEventBus} with the current model The event is
	 * created using the current state of the {@code ImportWizardModel}
	 */
	public void fireNewEvent()
	{
		ApplicationBaseEventBus.post(this.model);
	}
}
