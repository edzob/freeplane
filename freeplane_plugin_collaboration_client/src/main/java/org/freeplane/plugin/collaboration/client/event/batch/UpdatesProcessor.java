package org.freeplane.plugin.collaboration.client.event.batch;

public interface UpdatesProcessor {
	void onUpdates(UpdatesFinished event);
}