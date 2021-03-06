/*******************************************************************************
 * Copyright (c) 2017, 2019 Lablicate GmbH.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * Dr. Philip Wenig - initial API and implementation
 * Frank Buloup - Internationalization
 *******************************************************************************/
package org.eclipse.swtchart.export.menu.bitmap;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtchart.export.core.AbstractSeriesExportHandler;
import org.eclipse.swtchart.export.core.ISeriesExportConverter;
import org.eclipse.swtchart.export.images.ImageSupplier;
import org.eclipse.swtchart.extensions.core.ScrollableChart;

public class JPGExportHandler extends AbstractSeriesExportHandler implements ISeriesExportConverter {

	private static final String FILE_EXTENSION = "*.jpg"; //$NON-NLS-1$
	public static final String NAME = Messages.getString(Messages.IMAGE) + FILE_EXTENSION + ")"; //$NON-NLS-1$
	private static final String TITLE = Messages.getString(Messages.SAVE_AS_IMAGE);

	@Override
	public String getName() {

		return NAME;
	}

	@Override
	public void execute(Shell shell, ScrollableChart scrollableChart) {

		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		fileDialog.setOverwrite(true);
		fileDialog.setText(NAME);
		fileDialog.setFilterExtensions(new String[]{"*.jpeg", "*.jpg"}); //$NON-NLS-1$ //$NON-NLS-2$
		//
		String fileName = fileDialog.open();
		if(fileName != null) {
			/*
			 * Select the format.
			 */
			ImageSupplier imageSupplier = new ImageSupplier();
			ImageData imageData = imageSupplier.getImageData(scrollableChart.getBaseChart());
			imageSupplier.saveImage(imageData, fileName, SWT.IMAGE_JPEG);
			MessageDialog.openInformation(shell, TITLE, MESSAGE_OK);
		}
	}
}
