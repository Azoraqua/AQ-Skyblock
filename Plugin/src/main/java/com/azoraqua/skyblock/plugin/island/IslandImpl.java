package com.azoraqua.skyblock.plugin.island;

import com.azoraqua.skyblock.api.Island;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class IslandImpl implements Island {

    private final UUID id;
    private final Player owner;
    private final Location location; // This is the location on the grid.
    private final Location spawnLocation; // This is the spawn location relative to the grid-cell.
    private final Location chestLocation; // This is the chest location relative to the island structure.

    public IslandImpl(UUID id, Player owner, Location location, Location spawnLocation, Location chestLocation) {
        this.id = id;
        this.owner = owner;
        this.location = location;
        this.spawnLocation = spawnLocation;
        this.chestLocation = chestLocation;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    @Override
    public Location getChestLocation() {
        return chestLocation;
    }
}
