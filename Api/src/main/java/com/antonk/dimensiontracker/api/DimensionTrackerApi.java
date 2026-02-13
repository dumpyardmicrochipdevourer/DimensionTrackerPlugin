package com.antonk.dimensiontracker.api;

import org.bukkit.entity.Player;

import java.util.List;

public interface DimensionTrackerApi {
    Dimension getPlayerDimension(Player player);
    Dimension getPlayerDimension(String name);
    boolean isLocationHidden(Player player);
    boolean isLocationHidden(String name);
    void setLocationHidden(Player player);
    void setLocationHidden(String name);
    List<Player> getOnlinePlayersInDimension(Dimension dimension);
}
