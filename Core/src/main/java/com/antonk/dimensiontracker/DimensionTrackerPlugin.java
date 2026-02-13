package com.antonk.dimensiontracker;

import com.antonk.dimensiontracker.api.impl.DimensionTrackerApiImpl;
import com.antonk.dimensiontracker.commands.CommandManager;
import com.antonk.dimensiontracker.listeners.PlayerChangedWorldListener;
import com.antonk.dimensiontracker.listeners.PlayerJoinedListener;
import com.antonk.dimensiontracker.managers.PlayerStateManager;
import com.antonk.dimensiontracker.scoreboard.ScoreboardService;
import com.antonk.dimensiontracker.api.DimensionTrackerApi;
import org.bukkit.plugin.java.JavaPlugin;

public final class DimensionTrackerPlugin extends JavaPlugin {

    private static DimensionTrackerApi api;
    private PlayerStateManager playerManager;

    @Override
    public void onEnable() {
        getLogger().info("DimensionTracker starting...");

        ScoreboardService scoreboardService = new ScoreboardService();
        playerManager = new PlayerStateManager(scoreboardService);
        api = new DimensionTrackerApiImpl(playerManager, scoreboardService);


        getServer().getPluginManager().registerEvents(
                new PlayerChangedWorldListener(playerManager), this);

        getServer().getPluginManager().registerEvents(
                new PlayerJoinedListener(playerManager), this);

        new CommandManager(this,playerManager).registerAll();

        getLogger().info("DimensionTracker successfully started!");
    }

    public static DimensionTrackerApi getAPI() {
        return api;
    }

    public PlayerStateManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void onDisable() {
        getLogger().info("DimensionTracker stopped!");
    }
}
