package exceptions;

import java.io.IOException;

public class FileException extends IOException {
    public FileException(String msg){
        super(msg);
    }
}
