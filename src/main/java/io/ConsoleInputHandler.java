package io;

import data.Coordinates;
import data.Location;
import data.Route;

import java.util.Scanner;

public class ConsoleInputHandler extends InputHandler {
    public ConsoleInputHandler(){
        super(new Scanner(System.in));
        getScanner().useDelimiter("\n");
    }

    @Override
    public String readName(){
        return new Request<>("name:", super::readName).getRespond();
    }

    @Override
    public Coordinates readCoordinates(){
        return new Request<>("enter coordinates data:", super::readCoordinates).getRespond();
    }

    @Override
    public Location readLocation(){
        return  new Request<>("enter location data:", super::readLocation).getRespond();
    }

    @Override
    public Float readCordX(){
        return new Request<>("x coordinate:", super::readCordX).getRespond();
    }

    @Override
    public int readCordY(){
        return new Request<>("y coordinate:", super::readCordY).getRespond();
    }

    @Override
    public  Long readLocX(){
        return new Request<>("x coordinate:", super::readLocX).getRespond();
    }

    @Override
    public  Double readLocY(){
        return new Request<>("y coordinate:", super::readLocY).getRespond();
    }

    @Override
    public  int readLocZ(){
        return  new Request<>("z coordinate:", super::readLocZ).getRespond();
    }

    @Override
    public  String readLocName(){
        return new Request<>("name:", super::readLocName).getRespond();
    }

    @Override
    public Double readDistance(){
        return new Request<>("enter distance:", super::readDistance).getRespond();
    }

    @Override
    public Route readRoute(){
        return new Request<>("enter Route data:", super::readRoute).getRespond();
    }
}
