package exceptions;

import utils.DateConverter;

public class EmptyPathException extends FileException{
    public EmptyPathException(){
        super(" empty path");
    }
}
