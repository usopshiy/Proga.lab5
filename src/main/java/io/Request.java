package io;

import exceptions.InvalidDataException;

public class Request<T> {
    private T respond;

    public Request(String msg, Askable<T> askable){
        while(true){
            try{
                System.out.print(msg + " ");
                respond = askable.ask();
                break;

            }catch (InvalidDataException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public T getRespond(){
        return respond;
    }
}
