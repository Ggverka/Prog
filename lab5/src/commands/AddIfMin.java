package commands;


import managers.CollectionManager;
import models.MusicBand;
import models.Query;
import util.Console;

public class AddIfMin extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public AddIfMin(Console console, CollectionManager collectionManager) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если значение его поля singlesCount меньше, чем наименьшее значение поля singlesCount среди элементов этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] arguments){
        if (!arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "' не подразумевает аргументов");
            return false;
        }
        try {
            var minSinglesCount = collectionManager.minSinglesCount();
            console.println("* Создание нового элемента: (add_if_min) (>" + minSinglesCount + ") :");
            MusicBand Band = Query.queryMusicBand(console, collectionManager.getFreeId());
            if (Band.getSinglesCount() < minSinglesCount) {
                collectionManager.add(Band);
                console.println("Элемент успешно добавлен!");
            } else {
                console.println("Элемент не добавлен, цена не минимальное (" + Band.getSinglesCount() + " > " + minSinglesCount +")");
            }
            return true;
        } catch (RuntimeException ignored) {} catch (Query.QueryBreak e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
