package commands;

import collection.RouteCollectionHandler;
import data.Route;
import exceptions.CommandException;
import exceptions.InvalidDataException;

public class PrintAscending implements Command{
    private final RouteCollectionHandler collectionHandler;

    public PrintAscending(RouteCollectionHandler collectionHandler) {
        this.collectionHandler = collectionHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        if(collectionHandler.getCollection().isEmpty()){
            throw new CommandException("Collection is empty! nothing to show yet");
        }
        collectionHandler.sort();
        for(Route route : collectionHandler.getCollection()){
            System.out.println(route);
        }
    }
    @Override
    public void outDescription() {
        System.out.println("print_ascending - shows collection elements in increasing order");
    }
}
