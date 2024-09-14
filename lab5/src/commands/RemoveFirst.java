package commands;

import Exceptions.CollectionIsEmptyException;
import managers.CollectionManager;
import util.Console;

public class RemoveFirst extends Command {
    private final Console console;
    private final CollectionManager collectionManager;
    public RemoveFirst(Console console, CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "' не подразумевает аргументов");
            return false;
        }
        try {
            if (collectionManager.getCollection().isEmpty()) throw new CollectionIsEmptyException();
            else {
            collectionManager.remove(collectionManager.getFirst().getId());
            console.println("Первый элемент коллекции удален!");
            }
        }catch (CollectionIsEmptyException e){
            console.printError("Коллекция пуста!");
        }
        return true;
    }
}

