package io;

import file.FileHandler;

import java.util.Scanner;

public class FileInputHandler extends InputHandler {
    public FileInputHandler(String path){
        super(new Scanner(new FileHandler(path).read()));
        getScanner().useDelimiter("\n");
    }
}
