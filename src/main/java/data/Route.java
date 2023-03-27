package data;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class Route implements Comparable<Route>{
    private UUID id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле не может быть null
    private Location to; //Поле не может быть null
    private Double distance; //Поле может быть null, Значение поля должно быть больше 1

    public Route(String name, Coordinates coord, Location from, Location to, Double distance){
        this.id = UUID.randomUUID();
        this.name = name;
        this.coordinates = coord;
        this.creationDate = Date.from(java.time.LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }
    public boolean validate(){
        return (coordinates!=null && coordinates.validate() && creationDate!=null && from!=null && from.validate() && to!=null && to.validate() && distance!=null && distance>1);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(Route route) {
        if(this.distance > route.distance) return 1;
        if(this.distance.equals(route.distance)) return 0;
        else return  -1;
    }
}
