package io;

import collection.RouteCollectionHandler;
import commands.*;
import file.FileHandler;
import exceptions.*;

import java.util.HashMap;
import java.util.Stack;

public class UserInputHandler {
    private HashMap<String, Command> map;
    private RouteCollectionHandler collectionHandler;
    private InputHandler currentHandler;
    private FileHandler fileHandler;
    private String currentScriptFilePath = "";
    private boolean isRunning = false;

    private static Stack<String> scriptStack = new Stack<>();

    public UserInputHandler(RouteCollectionHandler collectionHandler, FileHandler fileHandler, InputHandler inputHandler){
        this.collectionHandler = collectionHandler;
        this.fileHandler = fileHandler;
        this.currentHandler = inputHandler;

        map = new HashMap<>();
        addCommand("help", new Help(this.map));
        addCommand("add", new Add(this.collectionHandler, currentHandler));
        addCommand("update_by_id", new UpdateByID(this.collectionHandler, this.currentHandler));
        addCommand("remove_by_id", new RemoveByID(this.collectionHandler));
        addCommand("clear", new Clear(collectionHandler));
        addCommand("save", new Save(fileHandler, collectionHandler));
        addCommand("exit", new Exit(this));
        addCommand("add_if_max", new AddIfMax(this.currentHandler, this.collectionHandler));
        addCommand("add_if_min", new AddIfMin(this.currentHandler, this.collectionHandler));
        addCommand("remove_greater", new RemoveGreater(this.currentHandler, this.collectionHandler));
        addCommand("remove_any_by_distance", new RemoveAnyByDistance(this.collectionHandler));
        addCommand("group_counting_by_from", new GroupCountingByFrom(this.collectionHandler));
        addCommand("execute_script", new ExecuteScript(this));
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

    public void exit(){
        isRunning = false;
    }

    public void executeScript(String arg) throws RecursiveScriptExecption {
        if (scriptStack.contains(currentScriptFilePath)) throw new RecursiveScriptExecption();
        scriptStack.push(currentScriptFilePath);
        UserInputHandler process = new UserInputHandler(collectionHandler, fileHandler, new FileInputHandler(arg));
        process.scriptMode(arg);
        scriptStack.pop();
        System.out.println("successfully executed script " + arg);
    }

    public  void consoleMode(){
        isRunning = true;
        while(isRunning){
            System.out.print("enter command: ");
            CommandWrapper pair = currentHandler.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
            }
        }

        public void scriptMode(String path){
        currentScriptFilePath = path;
        isRunning = true;
        while(isRunning && currentHandler.getScanner().hasNextLine()){
            CommandWrapper pair = currentHandler.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
        }
}
