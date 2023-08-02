package com.coherentsolutions;

import com.coherentsolutions.commands.*;

import java.util.Scanner;

public class StoreInteraction {
    private StoreInteraction(){}
    public static void LaunchStore(Store store){
        var quitFlag = true;
        var commandPool = new CommandPool();
        commandPool.addCommand("show", new ShowProductsCommand(store));
        commandPool.addCommand("sort", new SortCommand(store));
        commandPool.addCommand("top", new TopCommand(store));
        commandPool.addCommand("order", new OrderCommand(store));
        commandPool.addCommand("quit", new QuitCommand());

        System.out.println("Enter 'quit' for exit");
        System.out.println("Please enter your command:");

        var scanner = new Scanner(System.in);
        while (quitFlag) {
            var input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")){
                quitFlag = false;
                scanner.close();
                commandPool.executeCommand(input);
            }else{
                commandPool.executeCommand(input);
            }
        }
    }
}
