package commands;

import Exceptions.CollectionIsEmptyException;
import managers.CollectionManager;
import models.MusicBand;
import models.Query;
import util.Console;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.PriorityQueue;

public class RemoveLower extends Command{

    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveLower(Console console, CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
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
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            MusicBand Band = Query.queryMusicBand(console, 1);

            int i = 0;
            for (var r: collectionManager.getCollection()){
                if (r.getSinglesCount() < Band.getSinglesCount()){
                    collectionManager.remove(r.getId());
                    i ++;
                }
            }
            if ((i % 10 == 1) && (i % 100 != 11))
                System.out.println(i + "объект удален");
            else System.out.println(i + "объекта(ов) удалено");
            return true;
        }catch (Query.QueryBreak e){
            throw new RuntimeException (e);
        }catch (RuntimeException e){
            console.printError("Прерывание ввода данных");
        }catch (CollectionIsEmptyException e){
            console.printError("Коллекция пуста!");
        }
        return false;
}}
