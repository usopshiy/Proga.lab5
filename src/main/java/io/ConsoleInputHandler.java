package io;

import java.util.Scanner;

public class ConsoleInputHandler extends InputHandler {
    public ConsoleInputHandler(){
        super(new Scanner(System.in));
        getScanner().useDelimiter("\n");
    }

    @Override
    public String readName(){
        return new Request<>("enter name", super::readName).getRespond();
    }

}
