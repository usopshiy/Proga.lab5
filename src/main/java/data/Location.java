package data;

public class Location {
    private Long x; //Поле не может быть null
    private Double y; //Поле не может быть null
    private int z;
    private String name; //Длина строки не должна быть больше 457, Поле не может быть null

    public boolean validate(){
        return (x!=null && y!=null && name!=null && name.length()<=457);
    }
}
