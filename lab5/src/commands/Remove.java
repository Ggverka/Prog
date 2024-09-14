package commands;
import Exceptions.CollectionIsEmptyException;
import Exceptions.NotFoundException;
import managers.CollectionManager;
import models.MusicBand;
import util.Console;

public class Remove extends Command {
    private final Console console;
    private final CollectionManager collectionManager;
    public Remove(Console console, CollectionManager collectionManager) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] arguments) {
        if (arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "' подразумевает наличие аргумента id");
            return false;
        }
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            var id = Integer.parseInt(arguments[1]);
            MusicBand Band = collectionManager.byId(id);
            if (Band == null) throw new NotFoundException();

            collectionManager.remove(id);
            console.println("Продукт успешно удален.");
            return true;

        } catch (CollectionIsEmptyException exception) {
            console.printError("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            console.printError("ID должен быть числом!");
        } catch (NotFoundException exception) {
            console.printError("LabWork с таким ID в коллекции нет!");
        }
        return false;
    }
}

