package collection;

import com.google.gson.*;
import com.google.gson.reflect.*;
import data.Route;
import json.CollectionDeserializer;
import json.DateDeserializer;
import json.LocalDateDeserializer;

import java.util.Date;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class RouteCollectionHandler {
    private LinkedHashSet<Route> collection;
    private HashSet<Long> uniqueIds;
    private java.time.LocalDateTime initDate;

    public RouteCollectionHandler(){
        collection = new LinkedHashSet<Route>();
        uniqueIds = new HashSet<Long>();
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
}
