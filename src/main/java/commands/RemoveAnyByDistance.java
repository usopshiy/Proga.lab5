package commands;

import collection.RouteCollectionHandler;
import data.Route;
import exceptions.CommandException;
import exceptions.InvalidDataException;

public class RemoveAnyByDistance implements Command{
    private final RouteCollectionHandler collectionHandler;

    public RemoveAnyByDistance(RouteCollectionHandler collectionHandler){
        this.collectionHandler = collectionHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        Double dist = (double) 0;
        if(arg != null) {
            try {
                dist = Double.parseDouble(arg);
            } catch (IllegalArgumentException e) {
                throw new InvalidDataException("invalid argument");
            }
            if(dist <= 1){throw new InvalidDataException("distance must be greater then 1");}
        }
        for(Route route : collectionHandler.getCollection()){
            if(route.getDistance().equals(dist)){
                collectionHandler.getCollection().remove(route);
                break;
            }
        }
    }

    @Override
    public void outDescription() {
        System.out.println("remove_any_by_distance distance - removes first element with given distance");
    }
}
