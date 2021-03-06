package com.azoraqua.skyblock.plugin.island;

import com.azoraqua.skyblock.api.Island;
import com.azoraqua.skyblock.api.IslandManager;
import com.azoraqua.skyblock.api.event.IslandCreateEvent;
import com.azoraqua.skyblock.api.event.IslandDeleteEvent;
import com.azoraqua.skyblock.plugin.SkyblockPlugin;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public final class IslandManagerImpl implements IslandManager {

    private static final String STORAGE_FORMAT_EXT = ".json";
    private static final int MAX_CACHED_ISLANDS = 100;

    private final SkyblockPlugin plugin;
    private final File dataFolder;
    private final Set<IslandImpl> islands = new HashSet<>(); // Only keep ${MAX_CACHED_ISLANDS} if more, store them otherwise.
    protected SerializationTask serializationTask;

    public IslandManagerImpl(SkyblockPlugin plugin, File dataFolder) {
        this.plugin = plugin;
        this.dataFolder = dataFolder;
    }

    @Override
    public void addIsland(Island island) {
        if ((islands.size() + 1) > MAX_CACHED_ISLANDS) {
            scheduleSerialization();
        }

        if (islands.add((IslandImpl) island)) {
            Bukkit.getPluginManager().callEvent(new IslandCreateEvent(island));
        }
    }

    @Override
    public void removeIsland(Island island) {
        if (islands.remove((IslandImpl) island)) {
            Bukkit.getPluginManager().callEvent(new IslandDeleteEvent(island));
        }
    }

    @Override
    public Collection<Island> getIslands() {
        return Collections.unmodifiableCollection(islands);
    }

    @Override
    public Island getIsland(UUID id) {
        return islands.stream().filter(island -> island.getId().equals(id)).findAny().orElse((IslandImpl) this.loadIsland(id));
    }

    @Override
    public Island getIsland(Player player) {
        return islands.stream().filter(island -> island.getOwner().equals(player)).findAny().orElse((IslandImpl) this.loadIsland(player));
    }

    @Override
    public Island getIsland(Location location) {
        return islands.stream().filter(island -> island.getLocation().equals(location) || island.getLocation().distance(location) <= 1).findAny().orElse(null); // May be null.
    }

    @Override
    public boolean hasIsland(UUID id) {
        return this.getIsland(id) != null;
    }

    @Override
    public boolean hasIsland(Player player) {
        return this.getIsland(player) != null;
    }

    public synchronized void scheduleSerialization() {
        if (serializationTask == null) {
            Bukkit.getScheduler().runTask(plugin, serializationTask = new SerializationTask());
        }

        if (!serializationTask.running) {
            serializationTask.running = true;
        }
    }

    private Island loadIsland(UUID id) {
        try (FileReader reader = new FileReader(new File(dataFolder, id.toString() + STORAGE_FORMAT_EXT))) {
            return SkyblockPlugin.GSON.fromJson(reader, IslandImpl.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    // Note: This manipulates the cache as well.
    private Island loadIsland(Player player) {
        final File registry = new File(dataFolder, "registry" + STORAGE_FORMAT_EXT);

        if (!registry.exists()) {
            return null;
        }

        try (FileReader registryReader = new FileReader(registry)) {
            final JsonObject o = SkyblockPlugin.GSON.fromJson(registryReader, JsonObject.class);

            if (o.has(player.getUniqueId().toString())) {
                final UUID islandId = UUID.fromString(o.get(player.getUniqueId().toString()).getAsString());
                final File islandFile = new File(dataFolder, islandId.toString() + STORAGE_FORMAT_EXT);

                if (islandFile.exists()) {
                    try (FileReader islandReader = new FileReader(islandFile)) {
                        final IslandImpl island = SkyblockPlugin.GSON.fromJson(islandReader, IslandImpl.class);

                        if (island != null && island.getOwner().equals(player)) {
                            islands.add(island);
                            return island;
                        }
                    }
                }
            }

            return null;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    // Only invoke when it's needed, per-need basis, not constantly/timed.
    private final class SerializationTask implements Runnable {

        private volatile boolean running;

        @Override
        public void run() {
            if (running) {
                final Iterator<IslandImpl> it = islands.iterator();

                while (it.hasNext()) {
                    final IslandImpl island = it.next();
                    final File islandFile = new File(dataFolder, island.getId().toString() + STORAGE_FORMAT_EXT);

                    if (!dataFolder.exists()) {
                        dataFolder.mkdirs();
                    }

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

                running = false;
            }
        }
    }
}
