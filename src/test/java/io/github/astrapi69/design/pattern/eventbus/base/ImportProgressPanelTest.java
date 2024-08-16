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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.design.pattern.eventbus.eventobject.ImportWizardModel;

/**
 * The class {@link ImportProgressPanelTest} provides unit tests for the {@link ImportProgressPanel}
 * class It verifies the functionality of event handling and state synchronization between
 * {@link ImportProgressPanel} and {@link ImportWizardPanel}
 */
public class ImportProgressPanelTest
{

	/**
	 * Test method for the interaction between {@link ImportProgressPanel} and
	 * {@link ImportWizardPanel} through the {@link ApplicationBaseEventBus} It validates that
	 * events are correctly fired, received, and processed, ensuring that the model state is
	 * synchronized across the panels
	 */
	@Test
	public void testApplicationEventBus()
	{
		ImportProgressPanel importProgressPanel = new ImportProgressPanel();
		ImportWizardModel importWizardModel = importProgressPanel.getImportWizardModel();
		assertNull(importWizardModel);

		ImportWizardPanel importWizardPanel = new ImportWizardPanel();
		importWizardPanel.fireNewEvent();
		importWizardModel = importProgressPanel.getImportWizardModel();
		assertNotNull(importWizardModel);

		ImportWizardModel model = importWizardPanel.getModel();
		assertEquals(importWizardModel, model);

		importWizardPanel.setModel(ImportWizardModel.builder().bundleAppName("blabla").build());
		model = importWizardPanel.getModel();
		assertNotEquals(importWizardModel, model);

		importWizardPanel.fireNewEvent();
		importWizardModel = importProgressPanel.getImportWizardModel();
		assertEquals(importWizardModel, model);
	}
}
