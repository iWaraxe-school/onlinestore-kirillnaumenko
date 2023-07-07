package com.coherentsolutions.commands;

import com.coherentsolutions.Store;
import com.coherentsolutions.interfaces.ICommand;

public class ShowProductsCommand implements ICommand {
    private Store store;
    public ShowProductsCommand(Store store){
        this.store = store;
    }

    @Override
    public void execute() {
        System.out.println(store);
    }
}
