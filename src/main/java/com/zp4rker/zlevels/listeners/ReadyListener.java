package com.zp4rker.zlevels.listeners;

import com.zp4rker.zlevels.ZLevels;
import com.zp4rker.zlevels.commands.*;
import com.zp4rker.zlevels.core.db.Database;
import com.zp4rker.zlevels.core.db.StaffRating;
import com.zp4rker.zlevels.core.util.AutoRole;
import com.zp4rker.zlevels.core.util.ZLogger;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

import java.util.concurrent.Executors;

/**
 * @author ZP4RKER
 */
public class ReadyListener {

    @SubscribeEvent
    public void onReady(ReadyEvent event) {
        // Send info
        ZLogger.info("Registering comands...");
        // Register rank command
        ZLevels.handler.registerCommand(new RankCommand());
        // Register leaderboard command
        ZLevels.handler.registerCommand(new LeaderboardCommand());
        // Register rating command
        ZLevels.handler.registerCommand(new RatingCommand());
        // Register rewards command
        ZLevels.handler.registerCommand(new RewardsCommand());
        // Register inactive command
        ZLevels.handler.registerCommand(new InactiveCommand());
        // Register flush command
        ZLevels.handler.registerCommand(new FlushCommand());
        // Send info
        ZLogger.info("Successfully registered " + ZLevels.handler.getCommands().values().size() + " commands!");
        // Run asynchonously
        Executors.newSingleThreadExecutor().submit(() -> {
            // Load DB
            Database.load();
            // Start StaffRating month
            StaffRating.startMonth();
            // Send info
            ZLogger.info("ZLevels " + ZLevels.VERSION + " is ready!");
        });
    }

}