package file;

import exceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    private  String path;
    public FileHandler(String pth){
        this.path = pth;
    }

    public FileHandler(){
        this.path = null;
    }
    public  void setPath(String pth){
        this.path = pth;
    }

    public String read(){
        String str = "";
        try {
            if (path==null) throw new EmptyPathException();
            File file = new File(path);
            if (!file.exists()) throw new FileNotExistException();
            if (!file.canRead()) throw new FileWrongPermissionException("Can't read file");
            Scanner scanner = new Scanner(file).useDelimiter("\n");
            while(scanner.hasNextLine()){
                str += scanner.nextLine();
            }
            scanner.close();
        } catch(FileException e){System.err.println(e.getMessage());}
        catch (IOException e){System.err.println("Can't access file");}
        return str;
    }
}
