package io;

import collection.RouteCollectionHandler;
import commands.*;
import file.FileHandler;
import io.*;
import exceptions.*;
import utils.ConsoleColors;
import utils.DateConverter;

import java.util.HashMap;

public class UserInputHandler {
    private HashMap<String, Command> map;
    private RouteCollectionHandler collectionHandler;
    private InputHandler currentHandler;

    private FileInputHandler fileInputHandler = new FileInputHandler();
    private ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private FileHandler fileHandler;
    private boolean isRunning = false;

    public UserInputHandler(RouteCollectionHandler collectionHandler, FileHandler fileHandler){
        this.collectionHandler = collectionHandler;
        this.fileHandler = fileHandler;
        this.currentHandler = consoleInputHandler;

        map = new HashMap<String, Command>();
        addCommand("help", new Help(this.map));
        addCommand("add", new Add(this.collectionHandler, currentHandler));
        addCommand("update_by_id", new UpdateByID(this.collectionHandler, this.currentHandler));
        addCommand("remove_by_id", new RemoveByID(this.collectionHandler));
        addCommand("clear", new Clear(collectionHandler));
        addCommand("save", new Save(fileHandler, collectionHandler));
        addCommand("exit", new Exit(this));
        addCommand("add_if_max", new AddIfMax(this.currentHandler, this.collectionHandler));
    }

    public void addCommand(String key, Command command){
        map.put(key, command);
    }

    public void runCommand(String key, String arg) {
        try {
            if (!map.containsKey(key)) {
                throw new InvalidDataException(key + ": this command doesn't exist");
            }
            map.get(key).execute(arg);
        } catch (InvalidDataException | CommandException e) {
            ExceptionWrapper.outException(e.getMessage());
        }
    }

    public void setIsRunning(boolean bool){
        isRunning = bool;
    }

    public  void consoleMode(){
        currentHandler = consoleInputHandler;
        isRunning = true;
        while(isRunning){
            System.out.print("enter command: ");
            CommandWrapper pair = currentHandler.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
            }
        }
}
