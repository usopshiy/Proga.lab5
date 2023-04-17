package commands;

import collection.RouteCollectionHandler;
import data.Route;
import exceptions.CommandException;
import exceptions.InvalidDataException;

public class Show implements Command{
    private final RouteCollectionHandler collectionHandler;

    public Show(RouteCollectionHandler collectionHandler) {
        this.collectionHandler = collectionHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        if(collectionHandler.getCollection().isEmpty()){
            throw new CommandException("Collection is empty! nothing to show yet");
        }
        for(Route route : collectionHandler.getCollection()){
            System.out.println(route);
        }
    }

    @Override
    public void outDescription() {
        System.out.println("show - shows all elements of collection");
    }
}
