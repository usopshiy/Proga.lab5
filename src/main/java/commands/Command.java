package commands;
import exceptions.*;

public interface Command {
     void execute(String arg) throws CommandException, InvalidDataException;
     void outDescription();
}
