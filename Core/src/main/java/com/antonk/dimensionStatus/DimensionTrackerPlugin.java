package com.antonk.dimensionStatus;

import com.antonk.dimensionStatus.api.impl.DimensionTrackerApiImpl;
import com.antonk.dimensionStatus.commands.CommandManager;
import com.antonk.dimensionStatus.listeners.PlayerChangedWorldListener;
import com.antonk.dimensionStatus.listeners.PlayerJoinedListener;
import com.antonk.dimensionStatus.managers.PlayerStateManager;
import com.antonk.dimensionStatus.scoreboard.ScoreboardService;
import com.antonk.dimensionstatus.api.DimensionTrackerApi;
import org.bukkit.plugin.java.JavaPlugin;

public final class DimensionTrackerPlugin extends JavaPlugin {

    private static DimensionTrackerApi api;
    private PlayerStateManager playerManager;

    @Override
    public void onEnable() {
        getLogger().info("DimensionStatus starting...");

        ScoreboardService scoreboardService = new ScoreboardService();
        playerManager = new PlayerStateManager(scoreboardService);
        api = new DimensionTrackerApiImpl(playerManager, scoreboardService);


        getServer().getPluginManager().registerEvents(
                new PlayerChangedWorldListener(playerManager), this);

        getServer().getPluginManager().registerEvents(
                new PlayerJoinedListener(playerManager), this);

        new CommandManager(this,playerManager).registerAll();

        getLogger().info("DimensionStatus successfully started!");
    }

    public static DimensionTrackerApi getAPI() {
        return api;
    }

    public PlayerStateManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void onDisable() {
        getLogger().info("DimensionStatus stopped!");
    }
}
