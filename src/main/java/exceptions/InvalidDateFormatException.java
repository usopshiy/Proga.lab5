package exceptions;

import utils.DateConverter;



public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException() {
        super(DateConverter.dateToString(java.time.LocalDateTime.now()) +" date format must be yyyy-MM-dd");
    }
}