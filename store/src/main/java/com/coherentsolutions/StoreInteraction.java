package com.coherentsolutions;

import com.coherentsolutions.commands.*;

import java.util.Scanner;

public class StoreInteraction {

    private StoreInteraction(){}
    public static void LaunchStore(Store store){
        var quitFlag = true;
        var showCommand = new ShowProductsCommand(store);
        var sortCommand = new SortCommand(store);
        var topCommand = new TopCommand(store);
        var orderCommand = new OrderCommand(store);
        var quitCommand = new QuitCommand();
        var defaultCommand = new DefaultCommand();
        System.out.println("Enter 'quit' for exit");
        System.out.println("Please enter your command:");
        var input = new Scanner(System.in);
        while (quitFlag) {
            var line = input.nextLine();
            switch (line) {
                case "show": {
                    showCommand.execute();
                    break;
                }
                case "sort": {
                    sortCommand.execute();
                    break;
                }
                case "top": {
                    topCommand.execute();
                    break;
                }

                case "order": {
                    orderCommand.execute();
                    break;
                }
                case "quit": {
                    quitFlag = false;
                    input.close();
                    quitCommand.execute();
                    break;
                }
                default: {
                    defaultCommand.execute();
                    break;
                }
            }
        }
    }
}
