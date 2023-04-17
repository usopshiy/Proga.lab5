package collection;

import com.google.gson.*;
import com.google.gson.reflect.*;
import commands.ExceptionWrapper;
import data.Location;
import data.Route;
import exceptions.InvalidDateFormatException;
import json.*;
import utils.DateConverter;

import java.util.*;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteCollectionHandler {
    private LinkedHashSet<Route> collection;
    private final HashSet<UUID> uniqueIds;
    private java.time.LocalDateTime initDate;

    public RouteCollectionHandler(){
        collection = new LinkedHashSet<>();
        uniqueIds = new HashSet<>();
        initDate = java.time.LocalDateTime.now();
    }
    public void deserializeCollection(String json){
        try {
            if (json == null || json.equals("")){
                collection =  new LinkedHashSet<>();
            } else {
                Pattern pattern = Pattern.compile("\"inittime\": \".{23}\",");
                Matcher matcher = pattern.matcher(json);
                if(matcher.find()){
                    initDate = DateConverter.parseLocalDateTime(json
                            .substring(matcher.start(), matcher.end())
                            .substring(13, 36));
                }
                pattern = Pattern.compile("\"collection\": ");
                matcher = pattern.matcher(json);
                if(matcher.find()){
                    json = json.trim().substring(matcher.end(), json.length() - 2);
                }
                Type collectionType = new TypeToken<LinkedHashSet<Route>>(){}.getType();
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateDeserializer())
                        .registerTypeAdapter(collectionType, new CollectionDeserializer(uniqueIds))
                        .registerTypeAdapter(java.time.LocalDate.class, new LocalDateDeserializer())
                .create();
                collection = gson.fromJson(json, collectionType);
            }
        } catch (JsonParseException | InvalidDateFormatException e){
            ExceptionWrapper.outException(e.getMessage());
        }

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
        json = "{\"inittime\": \"" + DateConverter.dateToString(initDate) +
                "\",\n\t\"collection\": " + json + "\n}";
        return json;
    }

    public void clear(){
        collection.clear();
        uniqueIds.clear();
    }

    public void sort(){
        ArrayList<Route> list = new ArrayList<>(collection);
        list.sort(new RouteComparator());
        collection = new LinkedHashSet<>(list);
    }

    public void add(Route route){
        uniqueIds.add(route.getId());
        collection.add(route);
        System.out.print("added element: ");
        System.out.println(route);
    }

    public boolean checkID(UUID id){
        for(Route route : collection){
            if(route.getId().equals(id)){
                return false;
            }
        }
        return true;
    }

    public void removeByID(UUID id){
        for(Route route : collection){
            if(route.getId().equals(id)){
                collection.remove(route);
                uniqueIds.remove(id);
                System.out.println("successfully removed route with id: " + id);
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

    public void groupCountingByFrom(){
        HashMap<Location, AtomicInteger> map = new HashMap<>();
        for (Route route : collection){
            Location from = route.getFrom();
            if (map.containsKey(from)){
                map.get(from).incrementAndGet();
            } else{
                map.put(from, new AtomicInteger(1));
            }
        }
        for (Map.Entry<Location, AtomicInteger> pair : map.entrySet()) {
            Location location = pair.getKey();
            int quantity = map.get(location).intValue();
            System.out.println(location + ": " + quantity);
        }
    }

    public void info(){
        System.out.println("Collection type: LinkedHashSet of Routes\n Creation date: " + DateConverter.dateToString(initDate) + "\nElements in collection: " + collection.size());
    }
    public LinkedHashSet<Route> getCollection() {
        return collection;
    }
}
