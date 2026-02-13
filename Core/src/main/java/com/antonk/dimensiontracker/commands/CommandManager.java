package com.antonk.dimensiontracker.commands;

import com.antonk.dimensiontracker.DimensionTrackerPlugin;
import com.antonk.dimensiontracker.commands.impl.DimensionsCommand;
import com.antonk.dimensiontracker.managers.PlayerStateManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public class CommandManager {

    private final DimensionTrackerPlugin plugin;
    private final PlayerStateManager playerManager;

    public CommandManager(DimensionTrackerPlugin plugin, PlayerStateManager playerManager) {
        this.plugin = plugin;
        this.playerManager = playerManager;
    }

    public void registerAll() {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            DimensionsCommand dimensionsCommand = new DimensionsCommand(playerManager);
            event.registrar().register(dimensionsCommand.createCommandNode());
        });
    }
}