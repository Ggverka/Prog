package commands;

import managers.CollectionManager;
import models.MusicGenre;
import util.Console;

import java.util.Arrays;

public class CountBy extends Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public CountBy(Console console, CollectionManager collectionManager) {
        super("count_by_genre genre", "вывести количество элементов, значение поля genre которых равно заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] arguments) {
        if (arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "' подразумевает наличие аргумента genre");
            return false;
        }
        try {
            int count = 0;
            MusicGenre musicgenre = MusicGenre.valueOf(arguments[1].toUpperCase());
            for (var c: collectionManager.getCollection()){
                if (c.getMusicGenre().equals(musicgenre)){
                    count += 1;
                }
            }
            console.println("Количество элементов с полем musicGenre = " + musicgenre + " : " + count);
            return true;
        } catch (IllegalArgumentException e) {
            console.printError("Некорректное значение. Введите одно из: " + Arrays.toString(MusicGenre.values()));
        }
        return false;
    }
}
