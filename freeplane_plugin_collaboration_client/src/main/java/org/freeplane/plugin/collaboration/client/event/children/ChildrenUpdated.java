package org.freeplane.plugin.collaboration.client.event.children;

import java.util.List;
import java.util.Optional;

import org.freeplane.features.map.NodeModel;
import org.freeplane.plugin.collaboration.client.event.NodeUpdated;
import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableChildrenUpdated.class)
@JsonDeserialize(as = ImmutableChildrenUpdated.class)
public interface ChildrenUpdated extends NodeUpdated{
	public enum Side{
		LEFT, RIGHT;

		public static Side of(NodeModel child) {
			return child.isLeft() ? LEFT : RIGHT;
		}

		public static Optional<Side> of(String string) {
			try {
				return Optional.of(Side.valueOf(string));
			} catch (IllegalArgumentException e) {
				return Optional.empty();
			}
		}
	}

	static ImmutableChildrenUpdated.Builder builder() {
		return ImmutableChildrenUpdated.builder();
	}
	
	List<String> content();
}