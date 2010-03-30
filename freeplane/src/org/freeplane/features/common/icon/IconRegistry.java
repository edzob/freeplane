/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Dimitry Polivaev
 *
 *  This file author is Dimitry Polivaev
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
package org.freeplane.features.common.icon;

import java.util.ListIterator;

import javax.swing.ListModel;

import org.freeplane.core.extension.IExtension;
import org.freeplane.core.util.collection.SortedComboBoxModel;
import org.freeplane.features.common.map.MapController;
import org.freeplane.features.common.map.MapModel;
import org.freeplane.features.common.map.NodeModel;

/**
 * @author Dimitry Polivaev
 * 
 * maintains a set of icons which currently are or have been
 * used on the map during the last editing session. This information is
 * used in IconConditionController calling IconRegistry.getIcons() to
 * prepare values available in Filter Editor Dialog / find dialog when
 * filter on icons is selected
 * 
 * 03.01.2009
 */
public class IconRegistry implements IExtension {
	final private SortedComboBoxModel mapIcons;

	public IconRegistry(final MapController mapController, final MapModel map) {
		super();
		mapIcons = new SortedComboBoxModel();
		registryNodeIcons(mapController, map.getRootNode());
	}

	public void addIcon(final UIIcon icon) {
		mapIcons.add(icon);
	}

	public ListModel getIconsAsListModel() {
		return mapIcons;
	}

	private void registryNodeIcons(final MapController mapController, final NodeModel node) {
		for (final MindIcon icon : node.getIcons()) {
			addIcon(icon);
		}
		final ListIterator<NodeModel> iterator = mapController.childrenUnfolded(node);
		while (iterator.hasNext()) {
			final NodeModel next = iterator.next();
			registryNodeIcons(mapController, next);
		}
	}

	public void addIcons(final MapModel map) {
		final IconRegistry newRegistry = map.getIconRegistry();
		final SortedComboBoxModel newMapIcons = newRegistry.mapIcons;
		for (final Object uiIcon : newMapIcons) {
			mapIcons.add(uiIcon);
		}
	}
}
