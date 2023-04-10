package commands;

import exceptions.CommandException;
import exceptions.InvalidDataException;
import io.UserInputHandler;

public class ExecuteScript implements Command{

    private UserInputHandler userInputHandler;

    public ExecuteScript(UserInputHandler userInputHandler) {
        this.userInputHandler = userInputHandler;
    }

    @Override
    public void execute(String arg) throws CommandException, InvalidDataException {
        if (arg.equals("")){ throw new InvalidDataException("missing Argument");}
        userInputHandler.executeScript(arg);
    }

    @Override
    public void outDescription() {

    }
}
