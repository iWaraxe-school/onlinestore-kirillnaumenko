package com.coherentsolutions.commands;

import com.coherentsolutions.interfaces.ICommand;

public class QuitCommand implements ICommand {
    @Override
    public void execute() {
        System.exit(0);
    }
}
