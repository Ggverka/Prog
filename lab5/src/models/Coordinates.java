package models;

public class Coordinates {
    private int x; //Значение поля должно быть больше -717
    private Integer y; //Максимальное значение поля: 456, Поле не может быть null

    public Coordinates(int x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + " ; " + y + ")";
    }
}
