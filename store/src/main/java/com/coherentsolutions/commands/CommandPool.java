package com.coherentsolutions.commands;

import com.coherentsolutions.interfaces.ICommand;

import java.util.HashMap;
import java.util.Map;

public class CommandPool {
    private Map<String, ICommand> commandMap;

    public CommandPool() {
        commandMap = new HashMap<>();
    }

    public void addCommand(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String commandName) {
        ICommand command = commandMap.get(commandName);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("I don't know this command yet");
        }
    }
}
