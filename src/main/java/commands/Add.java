package commands;

import collection.RouteCollectionHandler;
import exceptions.CommandException;
import exceptions.InvalidDataException;
import io.InputHandler;
import json.CollectionDeserializer;

public class Add implements  Command {
    private final RouteCollectionHandler collectionHandler;
    private final InputHandler inputHandler;

    public Add(RouteCollectionHandler routeCollectionHandler, InputHandler inputHandler){
        this.collectionHandler = routeCollectionHandler;
        this.inputHandler = inputHandler;
    }
    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        collectionHandler.add(inputHandler.readRoute());
    }

    @Override
    public void getDescription() {
        System.out.println("add {element} - adds new route in collection");
    }
}
