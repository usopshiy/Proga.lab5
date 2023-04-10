package data;

import java.util.Objects;

public class Location {
    private Integer x; //Поле не может быть null
    private Double y; //Поле не может быть null

    private Long z; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 457, Поле не может быть null

    public Location(Integer x, Double y, Long z, String name){
        this.x =x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
    public boolean validate(){
        return (x!=null && y!=null && z!=null && name!=null && name.length()<=457);
    }

    @Override
    public String toString() {
        return (this.name +"(" + x.toString() + ", " + y.toString() + ", " + z.toString() + ")");
    }
}
