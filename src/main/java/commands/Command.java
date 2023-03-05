package commands;
import exceptions.*;

@FunctionalInterface

public interface Command {
     void execute(String arg) throws CommandException, InvalidDataException;
}
