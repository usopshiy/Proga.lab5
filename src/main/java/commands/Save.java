package commands;

import collection.RouteCollectionHandler;
import exceptions.CommandException;
import exceptions.InvalidDataException;
import file.FileHandler;


public class Save implements Command{
    private final FileHandler fileHandler;
    private final RouteCollectionHandler collectionHandler;

    public Save(FileHandler fileHandler, RouteCollectionHandler collectionHandler) {
        this.fileHandler = fileHandler;
        this.collectionHandler = collectionHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        if (!((arg == null) || arg.equals(""))) fileHandler.setPath(arg);
        if (collectionHandler.getCollection().isEmpty()) System.out.println("there is nothing to save yet");
        if (!fileHandler.write(collectionHandler.serializeCollection())) throw new CommandException("cannot save collection");
    }

    @Override
    public void outDescription() {

    }
}
