package commands;

import Exceptions.CollectionIsEmptyException;
import managers.CollectionManager;
import models.MusicBand;
import util.Console;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;

public class PrintFieldDescendingNumberOfParticipants extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintFieldDescendingNumberOfParticipants(Console console, CollectionManager collectionManager) {
        super("print_field_descending_number_of_participants", "вывести значения поля numberOfParticipants всех элементов в порядке убывания");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "'не подразумевает наличие аргументов");
            return false;
        }
        try {
            ArrayDeque<MusicBand> musicBands = collectionManager.getCollection();
            ArrayList<Long> numberOfParticipants = new ArrayList<>();

            if (musicBands.isEmpty()) throw new CollectionIsEmptyException();
            else {
                for (var r: musicBands){
                    if (r.getNumberOfParticipants() != null){
                        numberOfParticipants.add(r.getNumberOfParticipants());
                    }
                }
                numberOfParticipants.sort(Comparator.reverseOrder());
                console.println(numberOfParticipants.toString());
            }
            return true;
        } catch (NullPointerException ignored){
        } catch (CollectionIsEmptyException e){
            console.printError("Коллекция пуста!");
        }
        return false;
    }
}

