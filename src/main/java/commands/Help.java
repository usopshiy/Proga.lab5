package commands;

import exceptions.CommandException;
import exceptions.InvalidDataException;

import java.util.HashMap;

public class Help implements Command{
    private final HashMap<String, Command> map;

    public Help(HashMap<String, Command> map) {
        this.map = map;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        for(String key : map.keySet()){
            map.get(key).getDescription();
        }
    }

    @Override
    public void getDescription() {
        System.out.println("help - shows available commands and their description");
    }
}
