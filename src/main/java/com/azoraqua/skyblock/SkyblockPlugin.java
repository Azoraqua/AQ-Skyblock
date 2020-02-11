package com.azoraqua.skyblock;

import com.azoraqua.skyblock.island.IslandManager;
import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SkyblockPlugin extends JavaPlugin {

    public static final Gson GSON = new Gson();

    private final IslandManager islandManager = new IslandManager(this, new File(getDataFolder(), "islands"));

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
