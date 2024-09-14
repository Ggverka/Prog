package models;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле не может быть null

    public Person(String name, String passportID, Country nationality, Location location){
        this.name = name;
        this.passportID = passportID;
        this.nationality = nationality;
        this.location = location;
    }
    @Override
    public String toString(){
        return "        name: " + name + "\n" +
                "        passportID: " + passportID + "\n" +
                "        nationality: " + nationality + "\n" +
                "        location: " + location.toString() + "\n";
    }
}
