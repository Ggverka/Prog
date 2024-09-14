package models;
import util.Element;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MusicBand extends Element implements Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private int singlesCount; //Значение поля должно быть больше 0
    private MusicGenre genre; //Поле может быть null
    private Person frontMan; //Поле может быть null

    public MusicBand(int id, String name, Coordinates coordinates, java.time.ZonedDateTime creationDate, Long numberOfParticipants, int singlesCount, MusicGenre genre, Person frontMan) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public MusicBand(int id, String name, Coordinates coordinates, Long numberOfParticipants, int singlesCount, MusicGenre genre, Person frontMan) {
        this(id, name, coordinates, ZonedDateTime.now(), numberOfParticipants, singlesCount, genre, frontMan);
    }

    @Override
    public String toString() {
        return "MusicBand №: " + id + "\n" +
                "    name: " + name + "\n" +
                "    creationDate: " + creationDate.format(DateTimeFormatter.ISO_DATE_TIME) + "\n" +
                "    coordinates: " + coordinates.toString()+ "\n" +
                "    numberOfParticipants: " + numberOfParticipants + "\n" +
                "    singlesCount: " + singlesCount + "\n" +
                "    genre: " + genre + "\n" +
                "    frontMan: \n" + frontMan.toString() + "\n";
    }

    @Override
    public int compareTo(Element element) {
        return (int)(this.id - element.getId());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSinglesCount() {
        return singlesCount;
    }

    public MusicGenre getMusicGenre() {return genre;}

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand that = (MusicBand) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDate, coordinates, numberOfParticipants, singlesCount, genre, frontMan);
    }
}
