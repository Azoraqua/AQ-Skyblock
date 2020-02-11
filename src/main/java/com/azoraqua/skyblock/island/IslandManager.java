package com.azoraqua.skyblock.island;

import com.azoraqua.skyblock.SkyblockPlugin;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class IslandManager {

    private static final String STORAGE_FORMAT_EXT = ".json";
    private static final int MAX_CACHED_ISLANDS = 100;

    private final SkyblockPlugin plugin;
    private final File dataFolder;
    private final Set<Island> islands = new HashSet<>(); // Only keep ${MAX_CACHED_ISLANDS} if more, store them otherwise.

    public IslandManager(SkyblockPlugin plugin, File dataFolder) {
        this.plugin = plugin;
        this.dataFolder = dataFolder;
    }

    public void addIsland(Island island) {
        if ((islands.size() + 1) > MAX_CACHED_ISLANDS) {
            // Schedule serialization task.
        }
    }

    // Only invoke when it's needed, per-need basis, not constantly/timed.
    private final class SerializationTask implements Runnable {

        @Override
        public void run() {
            final Iterator<Island> it = islands.iterator();

            while (it.hasNext()) {
                final Island island = it.next();
                final File islandFile = new File(dataFolder, island.getId().toString() + STORAGE_FORMAT_EXT);

                if (!islandFile.exists()) {
                    try {
                        islandFile.createNewFile();
                    } catch (IOException e) {
                        Bukkit.getLogger().warning(String.format("Could not create island-datafile %s, due to: %s", island.getId(), e.getMessage()));
                    }
                }

                try (FileWriter writer = new FileWriter(islandFile)) {
                    writer.write(SkyblockPlugin.GSON.toJson(island));
                    it.remove(); // Remove it from the cache, only if successful. If failed try again next time.

                    Bukkit.getLogger().info(String.format("Serialized island %s.", island.getId()));
                } catch (IOException e) {
                    Bukkit.getLogger().warning(String.format("Could not serialize island %s, due to: %s", island.getId(), e.getMessage()));
                }
            }
        }
    }
}
