package com.azoraqua.skyblock.api.event;

import com.azoraqua.skyblock.api.Island;

/**
 * The type Island create event.
 */
public final class IslandCreateEvent extends IslandEvent {

    /**
     * Instantiates a new Island create event.
     *
     * @param island the island
     */
    public IslandCreateEvent(Island island) {
        super(island);
    }
}
