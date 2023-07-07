package com.coherentsolutions.commands;

import com.coherentsolutions.interfaces.ICommand;

public class DefaultCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("I don't know this command yet");
    }
}
