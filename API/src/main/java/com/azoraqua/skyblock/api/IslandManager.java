package com.azoraqua.skyblock.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IslandManager {

    void addIsland(Island island);

    void removeIsland(Island island);

    Island getIsland(UUID id);

    Island getIsland(Player player);

    Island getIsland(Location location);

    boolean hasIsland(UUID id);

    boolean hasIsland(Player player);
}
