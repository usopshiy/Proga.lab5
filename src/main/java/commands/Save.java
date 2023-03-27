package commands;

import collection.RouteCollectionHandler;
import data.Route;
import exceptions.CommandException;
import exceptions.InvalidDataException;
import file.FileHandler;

import java.io.File;
import java.util.Collection;

public class Save implements Command{
    private FileHandler fileHandler;
    private RouteCollectionHandler collectionHandler;

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
    public void getDescription() {

    }
}
