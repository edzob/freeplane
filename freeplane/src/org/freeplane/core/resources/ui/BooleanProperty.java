/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitry Polivaev
 *
 *  This file is modified by Dimitry Polivaev in 2008.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.core.resources.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import org.freeplane.core.util.TextUtil;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class BooleanProperty extends PropertyBean implements IPropertyControl {
	// TODO ARCH rladstaetter 28.02.2009 move JCheckbox out of here
	JCheckBox mCheckBox = new JCheckBox();

	/**
	 */
	public BooleanProperty(final String name) {
		super(name);
		mCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(final ItemEvent pE) {
				firePropertyChangeEvent();
			}
		});
	}

	@Override
	public String getValue() {
		return mCheckBox.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
	}

	public void layout(final DefaultFormBuilder builder) {
		final JLabel label = builder.append(TextUtil.getOptionalText(getLabel()), mCheckBox);
		label.setToolTipText(TextUtil.getOptionalText(getDescription()));
	}

	public void setEnabled(final boolean pEnabled) {
		mCheckBox.setEnabled(pEnabled);
	}

	@Override
	public void setValue(final String value) {
		final boolean booleanValue = Boolean.parseBoolean(value);
		setValue(booleanValue);
	}

	public void setValue(final boolean booleanValue) {
		mCheckBox.setSelected(booleanValue);
	}

	public boolean getBooleanValue() {
		return mCheckBox.isSelected();
	}
}
