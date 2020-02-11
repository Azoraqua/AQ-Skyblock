package com.azoraqua.skyblock.api.event;

import com.azoraqua.skyblock.api.Island;

/**
 * The type Island delete event.
 */
public final class IslandDeleteEvent extends IslandEvent {

    /**
     * Instantiates a new Island delete event.
     *
     * @param island the island
     */
    public IslandDeleteEvent(Island island) {
        super(island);
    }
}
