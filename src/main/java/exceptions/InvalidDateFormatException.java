package exceptions;

public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException() {
        super("date format must be yyyy-MM-dd");
    }
}