package commands;

import collection.RouteCollectionHandler;
import exceptions.CommandException;
import exceptions.InvalidDataException;

import java.util.UUID;

public class RemoveByID implements Command{
    private final RouteCollectionHandler collectionHandler;

    public RemoveByID(RouteCollectionHandler handler){
        this.collectionHandler = handler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        if (!collectionHandler.validateID(arg)){
            throw new InvalidDataException("Invalid id");
        }
        UUID id = UUID.fromString(arg); //Exception-save cause checks in validate
        if (collectionHandler.getCollection().isEmpty()) {
            throw new CommandException("collection is empty!");
        }
        if(collectionHandler.checkID(id)){
            throw new CommandException("id is not present in collection!");
        }
        collectionHandler.removeByID(id);
    }

    @Override
    public void outDescription() {
        System.out.println("remove_by_id id - removes route with given id from collection");
    }
}
