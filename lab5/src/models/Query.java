package models;
import util.Console;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Query {
    public static class QueryBreak extends Exception {}

    public static MusicBand queryMusicBand(Console console, int id) throws QueryBreak {
        String name;
        Long numberOfParticipants = null;
        int singlesCount;
        try {
            while (true) {
                console.print("name: ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new QueryBreak();
                if (!name.isEmpty()) break;
                else {
                    console.printError("Поле \"name\" не может быть пустым.");
                }
            }

            while (true) {
                console.print("numberOfParticipants: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")) {
                    try {
                        numberOfParticipants = Long.parseLong(line);
                        if (numberOfParticipants > 0) break;
                        else console.printError("Значение поля \"numberOfParticipants\" Должно быть больше 0.");
                    } catch (NumberFormatException e) {
                        console.printError("Введите ЧИСЛО.");
                    }
                }
                else break;
            }
            while (true) {
                console.print("singlesCount: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")){
                    try {
                        singlesCount = Integer.parseInt(line);
                        if (singlesCount > 0) break;
                        else console.printError("Значение поля \"singlesCount\" Должно быть больше 0.");
                    }
                    catch(NumberFormatException e) {
                        console.printError("Введите ЧИСЛО.");
                    }
                }
                else console.printError("Значение поля \"singlesCount\" не может быть null.");
            }
            var coordinates = queryCoordinates(console);
            var musicgenre = queryMusicGenre(console);
            var frontMan = queryPerson(console);
            return new MusicBand(id, name, coordinates, numberOfParticipants, singlesCount, musicgenre, frontMan);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Coordinates queryCoordinates(Console console) throws QueryBreak {
        try {
            int x;
            while (true) {
                console.print("coordinates.x (>-717): ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")) {
                    try {
                        x = Integer.parseInt(line);
                        if (x > -717) break;
                        else console.printError("Координата x должна быть больше -717.");
                    }catch(NumberFormatException e) {
                        console.printError("Введите ЧИСЛО.");
                    }
                }
                else console.printError("Значение поля \"coordinates.x\" не может быть null.");
            }
            Integer y;
            while (true) {
                console.print("coordinates.y (<= 456): ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")) {
                    try {
                        y = Integer.parseInt(line);
                        if (y <= 456) break;
                        else console.printError("Координата y должна быть меньше или равна 456.");
                    }catch(NumberFormatException e) {
                        console.printError("Введите ЧИСЛО.");
                    }
                }
                else console.printError("Значение поля \"coordinates.y\" не может быть null.");
            }
            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
    public static Location queryLocation(Console console) throws QueryBreak {
        try {
            Double x;
            while (true) {
                console.print("location.x: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")) {
                    try { x = Double.parseDouble(line);
                        break; }
                    catch(NumberFormatException e) {
                        console.printError("Введите ЧИСЛО.");
                    }
                }
                else console.printError("Значение поля \"location.x\" не может быть null.");
            }
            long y;
            while (true) {
                console.print("location.y: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")) {
                    try { y = Long.parseLong(line); break; }catch(NumberFormatException e) {
                        console.printError("Введите ЧИСЛО.");
                    }
                }
                else console.printError("Значение поля \"location.y\" не может быть null.");
            }
            double z;
            while (true) {
                console.print("location.z: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")) {
                    try { z = Double.parseDouble(line); break; }catch(NumberFormatException e) {
                        console.printError("Введите ЧИСЛО.");}
                }
                else console.printError("Значение поля \"location.z\" не может быть null.");
            }
            return new Location(x, y, z);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static MusicGenre queryMusicGenre(Console console) throws QueryBreak {
        try {
            MusicGenre musicgenre;
            while (true) {
                console.print("MusicGenre " + Arrays.toString(MusicGenre.values()) + ": ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")) {
                    try {
                        musicgenre = MusicGenre.valueOf(line.toUpperCase());
                        break;
                    } catch (IllegalArgumentException e) {
                        console.printError("Некорректное значение. Введите одно из: " + Arrays.toString(MusicGenre.values()));
                    }
                }
                else {
                    musicgenre = null;
                    break;
                }
            }
            return musicgenre;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Country queryCountry(Console console) throws QueryBreak {
        try {
            Country nationality;
            while (true) {
                console.print("Nationality " + Arrays.toString(Country.values()) + ": ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new QueryBreak();
                if (!line.equals("")) {
                    try {
                        nationality = Country.valueOf(line.toUpperCase());
                        break;
                    } catch (IllegalArgumentException e) {
                        console.printError("Некорректное значение. Введите одно из: " + Arrays.toString(Country.values()));
                    }
                }
                else {
                    nationality = null;
                    break;
                }
            }
            return nationality;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
    public static Person queryPerson(Console console) throws QueryBreak {
        String passportID;
        String name;
        try {
            while (true) {
                console.print("name: ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new QueryBreak();
                if (!name.isEmpty()) break;
                else {
                    console.printError("Поле \"name\" не может быть пустым.");
                }
            }
            console.print("passportID: ");
            passportID = console.readln().trim();
            if (passportID.equals("exit")) throw new QueryBreak();
            else if (passportID.equals("")) passportID = null;
            var location = queryLocation(console);
            var nationality = queryCountry(console);
            return new Person(name, passportID, nationality, location);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
}
