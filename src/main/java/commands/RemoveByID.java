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
        if(arg==null){
            throw new CommandException("missing id");
        }
        UUID id;
        try{
            if (arg.length() != 36){
                throw new IllegalArgumentException();
            }
            id = UUID.fromString(arg);
        }
        catch(IllegalArgumentException e){
            throw new InvalidDataException("invalid id format");
        }
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
