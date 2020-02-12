package com.azoraqua.skyblock.plugin;

import com.azoraqua.skyblock.plugin.command.CommandHandler;
import com.azoraqua.skyblock.plugin.command.CreateIslandCommand;
import com.azoraqua.skyblock.plugin.command.DeleteIslandCommand;
import com.azoraqua.skyblock.plugin.command.InfoCommand;
import com.azoraqua.skyblock.plugin.island.IslandFactoryImpl;
import com.azoraqua.skyblock.plugin.island.IslandManagerImpl;
import com.google.gson.Gson;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class SkyblockPlugin extends JavaPlugin {

    public static final Gson GSON = new Gson();

    private CommandHandler commandHandler;
    private final IslandManagerImpl islandManager = new IslandManagerImpl(this, new File(getDataFolder(), "islands"));
    private final IslandFactoryImpl islandFactory = new IslandFactoryImpl(this);

    @Override
    public void onEnable() {
        // Perhaps register some commands or listeners?
        // Perhaps load some data, or not needed?

        final PluginCommand base = Objects.requireNonNull(super.getCommand("skyblock"));
        base.setExecutor(commandHandler);
        base.setTabCompleter(commandHandler);

        commandHandler = new CommandHandler(this, base.getName());
        commandHandler.registerSubCommand(new CreateIslandCommand("create", "Create an island.", new String[]{"c"}, "/{base} create", "{base}.command.create;{base}.*", false, 0));
        commandHandler.registerSubCommand(new DeleteIslandCommand("delete", "Delete an island.", new String[]{"d"}, "/{base} delete", "{base}.command.delete;{base}.*", false, 0));
        commandHandler.registerSubCommand(new InfoCommand("info", "Gather information about an island.", new String[]{"i"}, "/{base} info", "{base}.command.info", false, 0));
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

    public IslandFactoryImpl getIslandFactory() {
        return islandFactory;
    }
}
