package commands;

import managers.CollectionManager;
import models.MusicBand;
import models.Query;
import models.Query.QueryBreak;
import util.Console;

public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "' не подразумевает наличие аргументов");
            return false;
        }
        try {
            console.println("* Создание нового элемента:");
            MusicBand Band = Query.queryMusicBand(console, collectionManager.getFreeId());
            collectionManager.add(Band);
            console.println("Элемент успешно добавлен!");
            return true;
        } catch (RuntimeException ignored) {} catch (QueryBreak e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
