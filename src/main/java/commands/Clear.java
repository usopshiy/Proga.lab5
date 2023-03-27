package commands;

import collection.RouteCollectionHandler;
import exceptions.CommandException;
import exceptions.InvalidDataException;

public class Clear implements Command {
    private final RouteCollectionHandler collectionHandler;

    public  Clear(RouteCollectionHandler collectionHandler){
        this.collectionHandler = collectionHandler;
    }
    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        collectionHandler.clear();
    }

    @Override
    public void getDescription(){
        System.out.println("clear - removes all routes from collection");
    }
}
