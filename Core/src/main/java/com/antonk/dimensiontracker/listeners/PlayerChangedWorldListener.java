package com.antonk.dimensiontracker.listeners;

import com.antonk.dimensiontracker.managers.PlayerStateManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorldListener implements Listener {

    private final PlayerStateManager playerManager;

    public PlayerChangedWorldListener(PlayerStateManager playerManager) {
        this.playerManager = playerManager;
    }
    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player p = event.getPlayer();
        playerManager.updatePlayerTeam(p);
    }
}
