package commands;

import collection.RouteCollectionHandler;
import exceptions.CommandException;
import exceptions.InvalidDataException;

public class GroupCountingByFrom implements Command{
    private final RouteCollectionHandler collectionHandler;

    public GroupCountingByFrom(RouteCollectionHandler collectionHandler) {
        this.collectionHandler = collectionHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        if (collectionHandler.getCollection().isEmpty()) {
            throw new CommandException("collection is empty!");
        }
        collectionHandler.groupCountingByFrom();
    }

    @Override
    public void outDescription() {
        System.out.println("group_counting_by_from - groups elements in collection by \"from\" field and shows it");
    }
}
