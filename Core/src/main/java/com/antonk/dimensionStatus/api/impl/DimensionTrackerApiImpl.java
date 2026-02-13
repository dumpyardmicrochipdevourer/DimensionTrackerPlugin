package com.antonk.dimensionStatus.api.impl;

import com.antonk.dimensionStatus.api.util.DimensionMapper;
import com.antonk.dimensionStatus.managers.PlayerStateManager;
import com.antonk.dimensionStatus.scoreboard.ScoreboardService;
import com.antonk.dimensionStatus.scoreboard.teams.DimensionTeam;
import com.antonk.dimensionstatus.api.Dimension;
import com.antonk.dimensionstatus.api.DimensionTrackerApi;
import org.bukkit.entity.Player;

import java.util.List;

public class DimensionTrackerApiImpl implements DimensionTrackerApi {

    private final PlayerStateManager playerManager;
    private final ScoreboardService scoreboardService;

    public DimensionTrackerApiImpl(PlayerStateManager playerManager, ScoreboardService scoreboardService) {
        this.playerManager = playerManager;
        this.scoreboardService = scoreboardService;
    }

    @Override
    public Dimension getPlayerDimension(Player player) {
        DimensionTeam dt = playerManager.getPlayerTeam(player);
        return DimensionMapper.toApi(dt);
    }

    @Override
    public Dimension getPlayerDimension(String name) {
        Player p = playerManager.getPlayerByName(name);
        return getPlayerDimension(p);
    }

    @Override
    public boolean isLocationHidden(Player player) {
        return playerManager.getPlayerTeam(player) == DimensionTeam.HIDDEN;
    }

    @Override
    public boolean isLocationHidden(String name) {
        Player p = playerManager.getPlayerByName(name);
        return isLocationHidden(p);
    }

    @Override
    public void setLocationHidden(Player player) {
        playerManager.setPlayerTeam(player, DimensionTeam.HIDDEN);
    }

    @Override
    public void setLocationHidden(String name) {
        Player p = playerManager.getPlayerByName(name);
        setLocationHidden(p);
    }

    @Override
    public List<Player> getOnlinePlayersInDimension(Dimension dimension) {
        DimensionTeam dt = DimensionMapper.toInternal(dimension);
        return scoreboardService.getPlayersInTeam(dt);
    }
}
