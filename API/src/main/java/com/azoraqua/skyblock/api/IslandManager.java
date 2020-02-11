package com.azoraqua.skyblock.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

/**
 * The manager of the {@link Island island}'s.
 */
public interface IslandManager {

    /**
     * Add island.
     *
     * @param island the island
     */
    void addIsland(Island island);

    /**
     * Remove island.
     *
     * @param island the island
     */
    void removeIsland(Island island);

    /**
     * Gets islands.
     *
     * @return the islands
     */
    Collection<Island> getIslands();

    /**
     * Gets island.
     *
     * @param id the id
     * @return the island
     */
    Island getIsland(UUID id);

    /**
     * Gets island.
     *
     * @param player the player
     * @return the island
     */
    Island getIsland(Player player);

    /**
     * Gets island.
     *
     * @param location the location
     * @return the island
     */
    Island getIsland(Location location);

    /**
     * Has island boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean hasIsland(UUID id);

    /**
     * Has island boolean.
     *
     * @param player the player
     * @return the boolean
     */
    boolean hasIsland(Player player);
}
