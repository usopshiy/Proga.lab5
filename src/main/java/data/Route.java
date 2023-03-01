package data;

public class Route {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле не может быть null
    private Location to; //Поле не может быть null
    private Double distance; //Поле может быть null, Значение поля должно быть больше 1

    public Long getId() {
        return id;
    }

    public boolean validate(){
        return (id > 0 && coordinates!=null && coordinates.validate() && creationDate!=null && from!=null && from.validate() && to!=null && to.validate() && distance!=null && distance>1);
    }
}
