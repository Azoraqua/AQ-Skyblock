package com.azoraqua.skyblock.plugin;

import com.azoraqua.skyblock.plugin.command.CommandHandler;
import com.azoraqua.skyblock.plugin.island.IslandManagerImpl;
import com.google.gson.Gson;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class SkyblockPlugin extends JavaPlugin {

    public static final Gson GSON = new Gson();

    private final CommandHandler commandHandler = new CommandHandler(this);
    private final IslandManagerImpl islandManager = new IslandManagerImpl(this, new File(getDataFolder(), "islands"));

    @Override
    public void onEnable() {
        // Perhaps register some commands or listeners?
        // Perhaps load some data, or not needed?

        final PluginCommand base = Objects.requireNonNull(super.getCommand("skyblock"));
        base.setExecutor(commandHandler);
        base.setTabCompleter(commandHandler);
    }

    @Override
    public void onDisable() {
        // Perhaps de-register commands or listeners?
        // Perhaps save some data, or not needed?

        if (!islandManager.getIslands().isEmpty()) {
            islandManager.scheduleSerialization();
        }
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public IslandManagerImpl getIslandManager() {
        return islandManager;
    }
}
