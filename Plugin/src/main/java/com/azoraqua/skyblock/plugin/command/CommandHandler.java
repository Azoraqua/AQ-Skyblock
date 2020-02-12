package com.azoraqua.skyblock.plugin.command;

import com.azoraqua.skyblock.plugin.SkyblockPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public final class CommandHandler implements CommandExecutor, TabCompleter {

    private final SkyblockPlugin plugin;
    private final Set<SubCommand> subCommands = new LinkedHashSet<>();

    public CommandHandler(SkyblockPlugin plugin) {
        this.plugin = plugin;
    }

    public Collection<SubCommand> getSubCommands() {
        return Collections.unmodifiableCollection(subCommands);
    }

    public Optional<SubCommand> getSubCommand(String name) {
        return subCommands.stream().filter(c -> c.getName().equalsIgnoreCase(name) || Arrays.stream(c.getAliases()).anyMatch(s -> s.equalsIgnoreCase(name))).findAny();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(subCommands.stream().map(c -> c.toHelpMessage(sender)).toArray(String[]::new));
            return true;
        }

        final Optional<SubCommand> optTarget = this.getSubCommand(args[0]);

        if (!optTarget.isPresent()) {
            sender.sendMessage(ChatColor.RED + "The specified command does not exists.");
            return true;
        }

        final SubCommand target = optTarget.get();

        if (target.getPermission() != null && !sender.hasPermission(target.getPermission())) {
            sender.sendMessage(ChatColor.RED + "You are not authorized to execute this command.");
            return true;
        }

        if (target.isOnlyPlayer() && !(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to execute this command.");
            return true;
        }

        final String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        if (subArgs.length < target.getMinimumArguments()) {
            if (target.getUsage() != null) {
                sender.sendMessage(ChatColor.RED + "Incorrect syntax. The correct usage is: " + target.getUsage());
            } else {
                sender.sendMessage(ChatColor.RED + "Incorrect syntax. The minimum amount of arguments is " + target.getMinimumArguments() + ".");
            }

            return true;
        }

        target.execute(sender, subArgs);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
