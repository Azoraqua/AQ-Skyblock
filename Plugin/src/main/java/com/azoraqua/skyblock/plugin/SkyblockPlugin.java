package com.azoraqua.skyblock.plugin;

import com.azoraqua.skyblock.plugin.island.IslandManagerImpl;
import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SkyblockPlugin extends JavaPlugin {

    public static final Gson GSON = new Gson();
    private final IslandManagerImpl islandManager = new IslandManagerImpl(this, new File(getDataFolder(), "islands"));

    @Override
    public void onEnable() {
        // Perhaps register some commands or listeners?
        // Perhaps load some data, or not needed?
    }

    @Override
    public void onDisable() {
        // Perhaps de-register commands or listeners?
        // Perhaps save some data, or not needed?
    }
}