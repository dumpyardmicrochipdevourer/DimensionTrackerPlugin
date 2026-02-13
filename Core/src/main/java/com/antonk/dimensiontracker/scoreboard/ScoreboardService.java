package com.antonk.dimensiontracker.scoreboard;

import com.antonk.dimensiontracker.scoreboard.teams.DimensionTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardService {
    private final Scoreboard board;

    public ScoreboardService() {
        board = Bukkit.getScoreboardManager().getMainScoreboard();
    }

    public void addToTeam(Player p, DimensionTeam dt) {
        dt.getOrCreate(board).addEntry(p.getName());
    }

    public void removeFromAllTeams(Player p) {
        for (Team team : board.getTeams()) {
            team.removeEntry(p.getName());
        }
    }

    public DimensionTeam getPlayerTeam(Player p) {
        for (Team team : board.getTeams()) {
            if (team.hasEntry(p.getName())) {
                return DimensionTeam.fromBukkitTeam(team);
            }
        }
        return null;
    }

    public List<Player> getPlayersInTeam(DimensionTeam dt) {
        Team t = dt.getOrCreate(board);
        return Bukkit.getOnlinePlayers().stream()
                .filter(p -> t.hasEntry(p.getName()))
                .collect(Collectors.toList());
    }
}
