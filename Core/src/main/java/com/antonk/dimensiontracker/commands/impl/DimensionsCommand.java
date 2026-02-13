package com.antonk.dimensiontracker.commands.impl;

import com.antonk.dimensiontracker.managers.PlayerStateManager;
import com.antonk.dimensiontracker.scoreboard.teams.DimensionTeam;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

import static io.papermc.paper.command.brigadier.Commands.literal;

public class DimensionsCommand {

    private final PlayerStateManager playerManager;

    public DimensionsCommand(PlayerStateManager playerManager) {
        this.playerManager = playerManager;
    }

    public LiteralCommandNode<CommandSourceStack> createCommandNode() {
        LiteralArgumentBuilder<CommandSourceStack> root = literal("dimensions")
                .then(literal("show")
                        .executes(ctx -> {
                            CommandSourceStack source = ctx.getSource();
                            if (source.getExecutor() instanceof Player player) {
                                playerManager.resetPlayerTeam(player);
                                player.sendMessage("Показаны измерения!");
                            } else {
                                source.getSender().sendPlainMessage("Эту команду может выполнить только игрок!");
                            }
                            return 1;
                        }))
                .then(literal("hide")
                        .executes(ctx -> {
                            CommandSourceStack source = ctx.getSource();
                            if (source.getExecutor() instanceof Player player) {
                                playerManager.setPlayerTeam(player, DimensionTeam.HIDDEN);
                                player.sendMessage("Измерения скрыты!");
                            } else {
                                source.getSender().sendPlainMessage("Эту команду может выполнить только игрок!");
                            }
                            return 1;
                        }));

        return root.build();
    }
}