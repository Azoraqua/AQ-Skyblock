package com.azoraqua.skyblock.plugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {

    private final String name;
    private final String description;
    private final String[] aliases;
    private final String usage;
    private final String permission;
    private final boolean onlyPlayer;
    private final int minimumArgs;

    public SubCommand(String name, String description, String[] aliases, String usage, String permission, boolean onlyPlayer, int minimumArgs) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
        this.usage = usage;
        this.permission = permission;
        this.onlyPlayer = onlyPlayer;
        this.minimumArgs = minimumArgs;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String getUsage() {
        return usage;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isOnlyPlayer() {
        return onlyPlayer;
    }

    public int getMinimumArguments() {
        return minimumArgs;
    }

    public String toHelpMessage(CommandSender sender) {
        final StringBuilder sb = new StringBuilder();

        sb.append((permission == null || sender.hasPermission(permission) ? ChatColor.GREEN : ChatColor.RED));
        sb.append(name);

        if (aliases != null && aliases.length > 0) {
            sb.append(ChatColor.AQUA);
            sb.append(String.join(ChatColor.GRAY + ", " + ChatColor.AQUA, aliases));
        }

        if (description != null && !description.isEmpty()) {
            sb.append(":");
            sb.append(ChatColor.WHITE);
            sb.append(description);
        }

        return sb.toString().trim();
    }

    public abstract void execute(CommandSender sender, String[] args);

    public abstract List<String> suggest(CommandSender sender, String[] args);
}
