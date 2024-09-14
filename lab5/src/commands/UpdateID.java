package commands;

import Exceptions.*;
import managers.CollectionManager;
import models.MusicBand;
import models.Query;
import util.Console;

public class UpdateID extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public UpdateID(Console console, CollectionManager collectionManager) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
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

            console.println("* Введите данные обновленного элемента':");

            var newMusicBand = Query.queryMusicBand(console, id);
            collectionManager.update(newMusicBand);

            console.println("Продукт успешно обновлен.");
            return true;

        } catch (CollectionIsEmptyException exception) {
            console.printError("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            console.printError("id должен быть числом!");
        } catch (NotFoundException exception) {
            console.printError("MusicBand с таким id в коллекции отсутствует");
        } catch (Query.QueryBreak e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

