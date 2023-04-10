package file;

import exceptions.*;
import utils.DateConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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

    public void create(File file) throws FileException{
        try{
            file.createNewFile();
        } catch (IOException e){
            throw new FileException("cannot create file");
        }
    }

    public String read(){
        StringBuilder str = new StringBuilder();
        try {
            if (path==null) throw new EmptyPathException();
            File file = new File(path);
            if (!file.exists()) throw new FileNotExistException();
            if (!file.canRead()) throw new FileWrongPermissionException(" Can't read file");
            Scanner scanner = new Scanner(file).useDelimiter("\n");
            while(scanner.hasNextLine()){
                str.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
        } catch(FileException e){System.err.println(DateConverter.dateToString(java.time.LocalDateTime.now()) +e.getMessage());}
        catch (IOException e){System.err.println(DateConverter.dateToString(java.time.LocalDateTime.now()) +" Can't access file");}
        return str.toString();
    }

    public boolean write(String str){
        try{
            if(path == null) throw new EmptyPathException();
            File file = new File(path);

            if(!file.exists()){
                System.out.println("file " + path + " doesn't exist, trying to create file");
                create(file);
            }
            if(!file.canWrite()){
                throw new FileWrongPermissionException("cannot write in that file");
            }
            java.io.FileWriter writer = new FileWriter(file);
            writer.write(str);
            writer.close();
        }
        catch(FileException e){
            System.err.println(DateConverter.dateToString(LocalDateTime.now()) + ' ' + e);
            return false;
        }
        catch(IOException e){
            System.err.println(DateConverter.dateToString(LocalDateTime.now()) + " cannot access file");
            return false;
        }
        return true;
    }
}
