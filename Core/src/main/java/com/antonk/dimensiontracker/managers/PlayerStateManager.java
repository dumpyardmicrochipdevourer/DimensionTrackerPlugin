package com.antonk.dimensiontracker.managers;

import com.antonk.dimensiontracker.scoreboard.ScoreboardService;
import com.antonk.dimensiontracker.scoreboard.teams.DimensionTeam;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerStateManager {
    private final ScoreboardService scoreboardService;

    public PlayerStateManager(ScoreboardService scoreboardService) {
        this.scoreboardService = scoreboardService;
    }

    public void updatePlayerTeam(Player p) {
        if (isHidden(p)) {
            return;
        }
        scoreboardService.removeFromAllTeams(p);
        DimensionTeam team = getDimensionTeam(p.getWorld());
        setPlayerTeam(p, team);
    }

    public void resetPlayerTeam(Player p) {
        scoreboardService.removeFromAllTeams(p);
        DimensionTeam team = getDimensionTeam(p.getWorld());
        setPlayerTeam(p, team);
    }

    public DimensionTeam getPlayerTeam(Player player) {
        return scoreboardService.getPlayerTeam(player);
    }

    public void setPlayerTeam(Player player, DimensionTeam team) {
        scoreboardService.removeFromAllTeams(player);
        scoreboardService.addToTeam(player, team);
    }


    public boolean isHidden(Player p) {
        return scoreboardService.getPlayerTeam(p) == DimensionTeam.HIDDEN;
    }

    public void setHidden(Player p) {
        scoreboardService.removeFromAllTeams(p);
        scoreboardService.addToTeam(p, DimensionTeam.HIDDEN);
    }

    public Player getPlayerByName(String name) {
        return Bukkit.getPlayerExact(name);
    }

    private DimensionTeam getDimensionTeam(World world) {
        World.Environment env = world.getEnvironment();
        return switch (env) {
            case NORMAL -> DimensionTeam.NORMAL;
            case NETHER -> DimensionTeam.NETHER;
            case THE_END -> DimensionTeam.END;
            case CUSTOM -> DimensionTeam.CUSTOM;
        };
    }
}
