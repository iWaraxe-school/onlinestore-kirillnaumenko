package com.coherentsolutions.commands;

import com.coherentsolutions.AppLogger;
import com.coherentsolutions.ThreadPoolManager;
import com.coherentsolutions.interfaces.ICommand;
import com.coherentsolutions.services.HttpServer;

public class QuitCommand implements ICommand {
    @Override
    public void execute() {
        AppLogger.getLogger().info("Closing resources...");
        HttpServer.stop();
        ThreadPoolManager.ShutdownScheduledThreadPool();
    }
}
