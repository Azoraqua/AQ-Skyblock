package com.azoraqua.skyblock.api.event;

import com.azoraqua.skyblock.api.Island;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * The type Island event.
 */
abstract class IslandEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Island island;

    /**
     * Instantiates a new Island event.
     *
     * @param island the island
     */
    public IslandEvent(Island island) {
        this.island = island;
    }

    /**
     * Gets island.
     *
     * @return the island
     */
    public Island getIsland() {
        return island;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
