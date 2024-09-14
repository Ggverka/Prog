package commands;

import managers.CollectionManager;
import util.Console;

import java.time.ZonedDateTime;

public class Info extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public Info(Console console, CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "' не подразумевает аргументов");
            return false;
        }

        ZonedDateTime lastInitTime = collectionManager.getLastInitTime();
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        ZonedDateTime lastSaveTime = collectionManager.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        console.println("Сведения о коллекции:");
        console.println(" Тип: " + collectionManager.collectionType());
        console.println(" Количество элементов: " + collectionManager.collectionSize());
        console.println(" Дата последнего сохранения: " + lastSaveTimeString);
        console.println(" Дата последней инициализации: " + lastInitTimeString);
        return true;
    }
}
