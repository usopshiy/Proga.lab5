package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import exceptions.InvalidDateFormatException;
public class DateConverter {
    private static final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final  DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String dateToString(Date date){
        return dateFormatter.format(date);
    }
    public static String dateToString(LocalDate date){
        return date.format(localDateFormatter);
    }

    public static LocalDate parseLocalDate(String s) throws InvalidDateFormatException{
        try{
            return LocalDate.parse(s, localDateFormatter);
        }
        catch(java.time.format.DateTimeParseException e){
            throw new InvalidDateFormatException();
        }
    }
    public static Date parseDate(String s) throws InvalidDateFormatException{
        try{
            return dateFormatter.parse(s);
        }
        catch (ParseException e){
            throw new InvalidDateFormatException();
        }
    }
}