package data;

public class Coordinates {
    private float x; //Максимальное значение поля: 162
    private Integer y; //Максимальное значение поля: 57, Поле не может быть null

    public Coordinates(Float x, int y){
        this.x = x;
        this.y = y;
    }
    public  boolean validate(){
        return (y!=null && x<=162 && y<=57);
    }
}
