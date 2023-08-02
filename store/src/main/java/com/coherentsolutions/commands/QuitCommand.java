package com.coherentsolutions.commands;

import com.coherentsolutions.ThreadPoolManager;
import com.coherentsolutions.interfaces.ICommand;

import java.util.concurrent.ExecutorService;

public class QuitCommand implements ICommand {
    @Override
    public void execute() {
        ThreadPoolManager.ShutdownScheduledThreadPool();
    }
}
