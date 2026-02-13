package com.antonk.dimensionStatus.api.util;

import com.antonk.dimensionStatus.scoreboard.teams.DimensionTeam;
import com.antonk.dimensionstatus.api.Dimension;

public class DimensionMapper {
    public static Dimension toApi(DimensionTeam internal) {
        return switch (internal) {
            case NORMAL -> Dimension.OVERWORLD;
            case NETHER -> Dimension.NETHER;
            case END -> Dimension.END;
            case CUSTOM -> Dimension.CUSTOM;
            case HIDDEN -> Dimension.HIDDEN;
        };
    }

    public static DimensionTeam toInternal(Dimension api) {
        return switch (api) {
            case OVERWORLD -> DimensionTeam.NORMAL;
            case NETHER -> DimensionTeam.NETHER;
            case END -> DimensionTeam.END;
            case CUSTOM -> DimensionTeam.CUSTOM;
            case HIDDEN -> DimensionTeam.HIDDEN;
        };
    }
}
