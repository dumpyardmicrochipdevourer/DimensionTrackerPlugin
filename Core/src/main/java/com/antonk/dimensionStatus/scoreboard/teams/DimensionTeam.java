package com.antonk.dimensionStatus.scoreboard.teams;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.scoreboard.*;

public enum DimensionTeam {
    NORMAL("ds_normal", NamedTextColor.GREEN),
    NETHER("ds_nether", NamedTextColor.RED),
    END("ds_end", NamedTextColor.LIGHT_PURPLE),
    CUSTOM("ds_custom",NamedTextColor.WHITE),
    HIDDEN("ds_hidden", NamedTextColor.GRAY);

    private String teamName;
    private NamedTextColor color;

    DimensionTeam(String name, NamedTextColor color) {
        this.teamName = name;
        this.color = color;
    }

    public Team getOrCreate(Scoreboard board) {
        Team team = board.getTeam(teamName);
        if (team == null) {
            team = board.registerNewTeam(teamName);
            team.prefix(Component.empty());
            team.suffix(Component.text(" ●", color));
        }
        return team;
    }

    public static DimensionTeam fromBukkitTeam(Team team) {
        String name = team.getName();
        for (DimensionTeam dt : values()) {
            if (dt.getTeamName().equals(name)) {
                return dt;
            }
        }
        return null;
    }

    public String getTeamName() {
        return teamName;
    }

    public NamedTextColor getColor() {
        return color;
    }
}
