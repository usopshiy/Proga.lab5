package collection;

import com.google.gson.*;
import com.google.gson.reflect.*;
import commands.ExceptionWrapper;
import data.Route;
import json.*;
import utils.ConsoleColors;
import utils.DateConverter;

import java.util.*;
import java.lang.reflect.Type;

public class RouteCollectionHandler {
    private LinkedHashSet<Route> collection;
    private HashSet<UUID> uniqueIds;
    private java.time.LocalDateTime initDate;

    public RouteCollectionHandler(){
        collection = new LinkedHashSet<Route>();
        uniqueIds = new HashSet<UUID>();
        initDate = java.time.LocalDateTime.now();
    }
    public boolean deserializeCollection(String json){
        boolean success = true;
        try {
            if (json == null || json.equals("")){
                collection =  new LinkedHashSet<Route>();
            } else {
                Type collectionType = new TypeToken<LinkedHashSet<Route>>(){}.getType();
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateDeserializer())
                        .registerTypeAdapter(collectionType, new CollectionDeserializer(uniqueIds))
                        .registerTypeAdapter(java.time.LocalDate.class, new LocalDateDeserializer())
                .create();
                collection = gson.fromJson(json.trim(), collectionType);
            }
        } catch (JsonParseException e){
            success = false;
            ExceptionWrapper.outException(e.getMessage());
        }
        return success;

    }

    public String serializeCollection(){
        if(collection == null || collection.isEmpty()){
            return "";
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(java.time.LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(collection);
        return json;
    }

    public void clear(){
        collection.clear();
        uniqueIds.clear();
    }

    public void add(Route route){
        uniqueIds.add(route.getId());
        collection.add(route);
        System.out.print("added element: ");
        System.out.println(route.toString());
    }

    public boolean checkID(UUID id){
        for(Route route : collection){
            if(route.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void removeByID(UUID id){
        for(Route route : collection){
            if(route.getId().equals(id)){
                collection.remove(route);
                uniqueIds.remove(id);
                System.out.println("successfully removed route with id: "+ id);
            }
        }
    }

    public void updateByID(UUID id, Route route){
        for(Route cRoute : collection){
            if(cRoute.getId().equals(id)){
                collection.remove(cRoute);
                collection.add(route);
                System.out.println("successfully updated route with id: " + id);
            }
        }
    }
    public LinkedHashSet<Route> getCollection() {
        return collection;
    }
}
