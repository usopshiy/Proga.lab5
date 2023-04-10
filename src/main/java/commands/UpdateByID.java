package commands;

import collection.RouteCollectionHandler;
import data.Route;
import exceptions.CommandException;
import exceptions.InvalidDataException;
import io.InputHandler;

import java.util.UUID;

public class UpdateByID implements Command{
    private  final RouteCollectionHandler collectionHandler;
    private final InputHandler inputHandler;

    public UpdateByID(RouteCollectionHandler collectionHandler, InputHandler inputHandler) {
        this.collectionHandler = collectionHandler;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        if(arg==null){
            throw new CommandException("missing id");
        }
        UUID id;
        try{
            id = UUID.fromString(arg);
        }
        catch(IllegalArgumentException e){
            throw new InvalidDataException("invalid id format");
        }
        if (collectionHandler.getCollection().isEmpty()) {
            throw new CommandException("collection is empty!");
        }
        if(!collectionHandler.checkID(id)){
            throw new CommandException("id is not present in collection!");
        }
        Route route = inputHandler.readRoute();
        route.setId(id);
        collectionHandler.updateByID(id, route);
    }

    @Override
    public void outDescription() {
        System.out.println("update_by_id id {element} - updates route with given id");
    }
}
