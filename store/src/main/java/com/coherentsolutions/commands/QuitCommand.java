package com.coherentsolutions.commands;

import com.coherentsolutions.AppLogger;
import com.coherentsolutions.ThreadPoolManager;
import com.coherentsolutions.interfaces.ICommand;

import java.util.concurrent.ExecutorService;

public class QuitCommand implements ICommand {
    @Override
    public void execute() {
        AppLogger.getLogger().info("Closing resources...");
        ThreadPoolManager.ShutdownScheduledThreadPool();
    }
}
