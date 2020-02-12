package com.azoraqua.skyblock.plugin.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public final class InfoCommand extends SubCommand {

    public InfoCommand(String name, String description, String[] aliases, String usage, String permission, boolean onlyPlayer, int minimumArgs) {
        super(name, description, aliases, usage, permission, onlyPlayer, minimumArgs);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

    @Override
    public List<String> suggest(CommandSender sender, String[] args) {
        return null;
    }
}
