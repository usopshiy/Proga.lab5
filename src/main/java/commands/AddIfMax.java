package commands;

import collection.RouteCollectionHandler;
import exceptions.CommandException;
import exceptions.InvalidDataException;
import io.InputHandler;
import data.Route;

public class AddIfMax implements Command{
    private final InputHandler inputHandler;
    private final RouteCollectionHandler collectionHandler;

    public AddIfMax(InputHandler inputHandler, RouteCollectionHandler collectionHandler) {
        this.inputHandler = inputHandler;
        this.collectionHandler = collectionHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        Route route = inputHandler.readRoute();
        boolean isMax = true;
        for(Route routech : collectionHandler.getCollection()){
            if (route.compareTo(routech) <= 0) {
                isMax = false;
                break;
            }
        }
        if(isMax){
            collectionHandler.add(route);
        }
    }

    @Override
    public void outDescription() {
        System.out.println("add_if_max {element} - adds new element in collection if it's greater then all others");
    }
}
