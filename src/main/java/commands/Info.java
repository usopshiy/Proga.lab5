package commands;

import collection.RouteCollectionHandler;
import exceptions.CommandException;
import exceptions.InvalidDataException;

public class Info implements Command{
    private final RouteCollectionHandler collectionHandler;

    public Info(RouteCollectionHandler collectionHandler) {
        this.collectionHandler = collectionHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        collectionHandler.info();
    }

    @Override
    public void outDescription() {
        System.out.println("info - shows info about collection");
    }
}
