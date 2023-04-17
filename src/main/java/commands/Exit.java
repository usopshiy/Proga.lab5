package commands;

import exceptions.CommandException;
import exceptions.InvalidDataException;
import io.UserInputHandler;

public class Exit implements Command{
    private final UserInputHandler userInputHandler;

    public Exit(UserInputHandler userInputHandler){
        this.userInputHandler = userInputHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        userInputHandler.exit();
    }

    @Override
    public void outDescription() {
        System.out.println("exit - exits program");
    }
}
