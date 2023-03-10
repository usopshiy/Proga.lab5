package collection;

import com.google.gson.*;
import com.google.gson.reflect.*;
import data.Route;
import json.CollectionDeserializer;
import json.DateDeserializer;
import json.LocalDateDeserializer;

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
            System.err.print("wrong json data");
        }
        return success;

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

    public LinkedHashSet<Route> getCollection() {
        return collection;
    }
}
