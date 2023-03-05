package data;

public class Coordinates {
    private Float x; //Максимальное значение поля: 162, Поле не может быть null
    private int y; //Максимальное значение поля: 57

    public Coordinates(Float x, int y){
        this.x = x;
        this.y = y;
    }
    public  boolean validate(){
        return (x!=null && x<=162 && y<=57);
    }
}
