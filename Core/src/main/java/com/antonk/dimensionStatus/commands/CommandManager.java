package com.antonk.dimensionStatus.commands;

import com.antonk.dimensionStatus.DimensionTrackerPlugin;
import com.antonk.dimensionStatus.commands.impl.DimensionsCommand;
import com.antonk.dimensionStatus.managers.PlayerStateManager;
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