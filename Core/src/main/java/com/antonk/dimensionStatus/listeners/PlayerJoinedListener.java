package com.antonk.dimensionStatus.listeners;

import com.antonk.dimensionStatus.managers.PlayerStateManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinedListener implements Listener {

    private final PlayerStateManager playerManager;

    public PlayerJoinedListener(PlayerStateManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        playerManager.updatePlayerTeam(p);
    }
}
