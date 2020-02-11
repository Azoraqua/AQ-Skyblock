package com.azoraqua.skyblock.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Represents an Island.
 */
public interface Island {

    /**
     * Gets id.
     *
     * @return the id
     */
    UUID getId();

    /**
     * Gets owner.
     *
     * @return the owner
     */
    Player getOwner();

    /**
     * Gets location.
     *
     * @return the location
     */
    Location getLocation();

    /**
     * Gets spawn location.
     *
     * @return the spawn location
     */
    Location getSpawnLocation();

    /**
     * Gets chest location.
     *
     * @return the chest location
     */
    Location getChestLocation();
}
