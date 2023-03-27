package exceptions;

import utils.DateConverter;

public class FileNotExistException extends FileException{
    public FileNotExistException(){
        super(" file doesn't exist");
    }
}
