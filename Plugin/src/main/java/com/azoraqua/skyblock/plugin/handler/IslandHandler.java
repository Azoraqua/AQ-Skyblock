package com.azoraqua.skyblock.plugin.handler;

import com.azoraqua.skyblock.api.event.IslandCreateEvent;
import com.azoraqua.skyblock.api.event.IslandDeleteEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class IslandHandler implements Listener {

    @EventHandler
    public void on(IslandCreateEvent e) {
        e.getIsland().getOwner().sendMessage(ChatColor.YELLOW + "Your island is successfully created! You can teleport to it by using the command /skyblock teleport.");
    }

    @EventHandler
    public void on(IslandDeleteEvent e) {
        e.getIsland().getOwner().sendMessage(ChatColor.YELLOW + "Your island is successfully deleted.");
    }
}
