package com.azoraqua.skyblock.plugin.island;

import com.azoraqua.skyblock.api.Island;
import com.azoraqua.skyblock.api.IslandFactory;
import com.azoraqua.skyblock.plugin.SkyblockPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

// Note: This class/these methods are only for construction of the object, no storage manipulation here.
public final class IslandFactoryImpl implements IslandFactory {

    private final SkyblockPlugin plugin;

    public IslandFactoryImpl(SkyblockPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Island createIsland(UUID id, Player owner, Location location, Location spawnLocation, Location chestLocation) {
        return new IslandImpl(id, owner, location, spawnLocation, chestLocation);
    }

    @Override
    public Island createIsland(UUID id, Player owner, Location location, Location spawnLocation) {
        return this.createIsland(id, owner, location, spawnLocation.add(0, 1, 0), spawnLocation);
    }

    @Override
    public Island createIsland(UUID id, Player owner, Location location) {
        return this.createIsland(id, owner, location, location.add(0, 1, 0)); // TODO: Retrieve 'spawnpoint', relative to island.
    }

    @Override
    public Island createIsland(UUID id, Player owner) {
        return this.createIsland(id, owner, null); // TODO: Retrieve 'next available location'.
    }

    @Override
    public Island createIsland(Player owner) {
        return this.createIsland(UUID.randomUUID(), owner);
    }
}
