package com.azoraqua.skyblock.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface Island {

    UUID getId();

    Player getOwner();

    Location getLocation();

    Location getSpawnLocation();

    Location getChestLocation();
}
