package io;

import collection.RouteCollectionHandler;
import commands.Add;
import commands.Clear;
import commands.Command;
import commands.Exit;
import file.FileHandler;
import io.*;
import exceptions.*;

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
        addCommand("clear", new Clear(collectionHandler));
        addCommand("exit", new Exit(this));
        addCommand("add", new Add(this.collectionHandler, currentHandler));
    }

    public void addCommand(String key, Command command){
        map.put(key, command);
    }

    public void setIsRunning(boolean bool){
        isRunning = bool;
    }
    public  void consoleMode(){
        currentHandler = consoleInputHandler;
        isRunning = true;
        while(isRunning){
            System.out.print("enter command: "); //особенности джавы - эта строка вылазит раньше кетча, позже пофикшу
            try{
                CommandWrapper pair = currentHandler.readCommand();
                if(!map.containsKey(pair.getCommand())){
                    throw new InvalidDataException("this command doesn't exist");
                }
                map.get(pair.getCommand()).execute(pair.getArg());
            }
            catch(InvalidDataException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
