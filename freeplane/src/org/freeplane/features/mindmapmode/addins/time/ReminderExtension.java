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
package org.freeplane.features.mindmapmode.addins.time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.freeplane.core.extension.IExtension;
import org.freeplane.core.util.SysUtil;
import org.freeplane.features.common.map.IMapChangeListener;
import org.freeplane.features.common.map.MapChangeEvent;
import org.freeplane.features.common.map.NodeModel;

/**
 * @author Dimitry Polivaev 30.11.2008
 */
class ReminderExtension implements IExtension, IMapChangeListener {
	/**
	 */
	public static ReminderExtension getExtension(final NodeModel node) {
		return (ReminderExtension) node.getExtension(ReminderExtension.class);
	}

	private final NodeModel node;
	private long remindUserAt = 0;
	private Timer timer;
	private final ReminderHook reminderHook;

	public ReminderExtension(final ReminderHook reminderHook, final NodeModel node) {
		this.node = node;
		this.reminderHook = reminderHook;
	}

	public NodeModel getNode() {
		return node;
	}

	long getRemindUserAt() {
		return remindUserAt;
	}

	void setRemindUserAt(final long remindUserAt) {
		this.remindUserAt = remindUserAt;
	}

	public void scheduleTimer(final TimerTask task, final Date date) {
		if (timer == null) {
			timer = SysUtil.createTimer(getClass().getSimpleName());
		}
		timer.schedule(task, date);
	}

	public void deactivateTimer() {
		if (timer == null) {
			return;
		}
		timer.cancel();
		timer = null;
	}

	private void displayStateIcon(final NodeModel parent, final ClockState state) {
		if (!isAncestorNode(parent)) {
			return;
		}
		reminderHook.displayState(this, state, parent, true);
	}

	private boolean isAncestorNode(final NodeModel parent) {
		for (NodeModel n = node; n != null; n = n.getParentNode()) {
			if (n.equals(parent)) {
				return true;
			}
		}
		return false;
	}

	public void onNodeInserted(final NodeModel parent, final NodeModel child, final int newIndex) {
		displayStateIcon(parent, ClockState.CLOCK_VISIBLE);
	}

	public void onNodeMoved(final NodeModel oldParent, final int oldIndex, final NodeModel newParent,
	                        final NodeModel child, final int newIndex) {
		displayStateIcon(newParent, ClockState.CLOCK_VISIBLE);
	}

	public void onPreNodeDelete(final NodeModel oldParent, final NodeModel selectedNode, final int index) {
		displayStateIcon(oldParent, ClockState.REMOVE_CLOCK);
	}

	public void onPreNodeMoved(final NodeModel oldParent, final int oldIndex, final NodeModel newParent,
	                           final NodeModel child, final int newIndex) {
		displayStateIcon(oldParent, ClockState.REMOVE_CLOCK);
	}

	public void mapChanged(final MapChangeEvent event) {
	}

	public void onNodeDeleted(final NodeModel parent, final NodeModel child, final int index) {
	}
}
