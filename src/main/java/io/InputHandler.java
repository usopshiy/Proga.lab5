package io;

import data.Coordinates;
import data.Location;
import data.Route;
import exceptions.InvalidDataException;

import java.util.Scanner;

public abstract class InputHandler {
    private Scanner scanner;

    public InputHandler(Scanner sc){
        this.scanner = sc;
        this.scanner.useDelimiter("\n");
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String readName() throws InvalidDataException {
        String s = scanner.nextLine().trim();
        if (s.equals("")){
            throw new InvalidDataException("route name cannot be empty");
        }
        return s;
    }

    public Coordinates readCoordinates() throws InvalidDataException{
        Float x = readCoordX();
        int y = readCordY();
        return new Coordinates(x, y);
    }

    public Location readLocation() throws InvalidDataException{
        Long x = readLocX();
        Double y = readLocY();
        int z = readLocZ();
        String name = readLocName();
        return new Location(x, y, z, name);
    }

    public Float readCoordX() throws InvalidDataException{
        Float x;
        try{
            x = Float.parseFloat(scanner.nextLine().trim());}
        catch(NumberFormatException e){
            throw new InvalidDataException("invalid coordinate format");
        }
        if (x > 162){
            throw new InvalidDataException("must be less than 162");
        }
        return x;
    }

    public int readCordY() throws InvalidDataException{
        int y;
        try{
            y = Integer.parseInt(scanner.nextLine().trim());
        }
        catch(NumberFormatException e){
            throw new InvalidDataException("invalid coordinate format");
        }
        if (y > 57){
            throw new InvalidDataException("must be less than 57");
        }
        return y;
    }

    public Long readLocX() throws InvalidDataException{
        Long x;
        try{
            x = Long.parseLong(scanner.nextLine().trim());
        }
        catch(NumberFormatException e){
            throw new InvalidDataException("invalid coordinate format");
        }
        return x;
    }

    public Double readLocY() throws InvalidDataException{
        Double y;
        try{
            y = Double.parseDouble(scanner.nextLine().trim());
        }
        catch(NumberFormatException e){
            throw new InvalidDataException("invalid coordinate format");
        }
        return y;
    }

    public int readLocZ() throws InvalidDataException{
        int z;
        try{
            z = Integer.parseInt(scanner.nextLine().trim());
        }
        catch(NumberFormatException e){
            throw new InvalidDataException("invalid coordinate format");
        }
        return z;
    }

    public String readLocName() throws InvalidDataException{
        String n = scanner.nextLine().trim();
        if (n.length() > 457){
            throw new InvalidDataException("Location name cannot be longer than 457");
        }
        return n;
    }

    public Double readDistance() throws InvalidDataException{
        Double dist;
        try{
            dist = Double.parseDouble(scanner.nextLine().trim());
        }
        catch(NumberFormatException e){
            throw new InvalidDataException("invalid coordinate format");
        }
        if (dist <= 1){
            throw new InvalidDataException("distance must be greater than 1");
        }
        return dist;
    }
    public Route readRoute() throws InvalidDataException{
        String name = readName();
        Coordinates cords = readCoordinates();
        Location from = readLocation();
        Location to = readLocation();
        Double distance = readDistance();
        Route route = new Route(name, cords, from, to, distance);
        return null;
    }
}