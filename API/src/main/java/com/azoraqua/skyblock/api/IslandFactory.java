package com.azoraqua.skyblock.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Factory for {@link Island Island} objects.
 *
 * @implNote None of these methods create actual data-parts, it's only for construction of the object.
 */
public interface IslandFactory {

    /**
     * Create island island.
     *
     * @param id            the id
     * @param owner         the owner
     * @param location      the location
     * @param spawnLocation the spawn location
     * @param chestLocation the chest location
     * @return the island
     */
    Island createIsland(UUID id, Player owner, Location location, Location spawnLocation, Location chestLocation);

    /**
     * Create island island.
     *
     * @param id            the id
     * @param owner         the owner
     * @param location      the location
     * @param spawnLocation the spawn location
     * @return the island
     */
    Island createIsland(UUID id, Player owner, Location location, Location spawnLocation);

    /**
     * Create island island.
     *
     * @param id       the id
     * @param owner    the owner
     * @param location the location
     * @return the island
     */
    Island createIsland(UUID id, Player owner, Location location);

    /**
     * Create island island.
     *
     * @param id    the id
     * @param owner the owner
     * @return the island
     */
    Island createIsland(UUID id, Player owner);

    /**
     * Create island island.
     *
     * @param owner the owner
     * @return the island
     */
    Island createIsland(Player owner);
}
