package models;

public class Location {
    private Double x; //Поле не может быть null
    private long y;
    private double z;

    public Location(Double x, long y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x + " ; " + y + " ; " + z + ")";
    }
}
