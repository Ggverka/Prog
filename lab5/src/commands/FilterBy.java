package commands;

import managers.CollectionManager;
import models.MusicBand;
import util.Console;

import java.util.ArrayDeque;

public class FilterBy extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterBy(Console console, CollectionManager collectionManager) {
        super("filter_by_singles_count singlesCount", "вывести элементы, значение поля singlesCount которых равно заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String[] arguments) {
        if (arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "' подразумевает наличие аргумента singlesCount");
            return false;
        }
        try {
            ArrayDeque<MusicBand> musicBands = new ArrayDeque<MusicBand>();
            for (var r : collectionManager.getCollection()) {
                if (r.getSinglesCount() == Integer.parseInt(arguments[1])){
                    musicBands.add(r);
                }
            }
            if (musicBands.isEmpty()) {
                console.println("Групп с таким singlesCount не обнаружено.");
            } else {
                for (var m: musicBands){
                    console.println(m);
                }
            }
            return true;
        } catch (NullPointerException ignored){
        }
        return false;
    }
}
