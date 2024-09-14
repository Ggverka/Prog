package managers;

import models.MusicBand;

import java.time.ZonedDateTime;
import java.util.*;

public class CollectionManager {
    private int currentId = 1;
    private final Map<Integer, MusicBand> Band;
    private final ArrayDeque<MusicBand> collection = new ArrayDeque<>();
    private ZonedDateTime lastInitTime;
    private ZonedDateTime lastSaveTime;
    private final DumpManager dumpManager;

    public CollectionManager(DumpManager dumpManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;
        Band = new HashMap<>();
        init();
    }

    /**
     * Возвращает время последней инициализации
     * @return Время последней инициализации
     */
    public ZonedDateTime getLastInitTime() {
        return lastInitTime;
    }
    /**
     * Возвращает время последнего сохранения
     * @return Время последнего сохранения
     */
    public ZonedDateTime getLastSaveTime() {
        return lastSaveTime;
    }
    /**
     * Возвращает коллекцию
     * @return Коллекция типа ArrayDeque
     */
    public ArrayDeque<MusicBand> getCollection() {
        return collection;
    }

    /**
     * Возвращает элемент по id
     * @param id Уникальный идентификатор
     * @return Объект класса MusicBand c заданным id
     */
    public MusicBand byId(int id) { return Band.get(id); }

    /**
     * Возвращает id, который еще не занят
     * @return id, с которым нет объекта класса MusicBand
     */
    public int getFreeId() {
        while (byId(currentId) != null){
            currentId++;
        };
        return currentId;
    }

    /**
     * Возвращает первый элемент коллекции
     * @return первый элемент коллекции
     */
    public MusicBand getFirst() {
        if (collection.isEmpty()) return null;
        return collection.peek();
    }

    /**
     * Добавляет элемент в коллекцию
     * @param a Объект класса MusicBand (элемент) , который хотим добавить
     */
    public void add(MusicBand a) {
        Band.put(a.getId(), a);
        collection.add(a);
        sort();
    }

    /**
     * Заменяет элемент на новый элемент с таким же id
     * @param a Объект класса MusicBand (элемент) , которым хотим заменить
     */
    public void update(MusicBand a) {
        collection.remove(byId(a.getId()));
        Band.put(a.getId(), a);
        collection.add(a);
        sort();
    }

    /**
     * Удаляет элемент по его id
     * @param id Уникальный идентификатор элемента, который хотим удалить
     */
    public void remove(Integer id) {
        var a = byId(id);
        Band.remove(a.getId());
        collection.remove(a);
        sort();
    }

    /**
     * Обновление коллекции
     */
    public void sort() {
        List<MusicBand> sortedList = new ArrayList<>(collection);
        Collections.sort(sortedList);
        collection.clear();
        collection.addAll(sortedList);
    }

    /**
     * Инициаализация объекта
     */
    public void init() {
        collection.addAll(dumpManager.readCollection());
        lastInitTime = ZonedDateTime.now();
        sort();
    }

    /**
     * Возвращает минимальное из полей singleCount всех элементов коллекции
     * @return Минимальное из полей singleCount всех элементов коллекции
     */
    public int minSinglesCount() {
        return this.getCollection().stream()
                .map(MusicBand::getSinglesCount)
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = ZonedDateTime.now();
    }

    /**
     * Очищает коллекцию
     */
    public void clearCollection() {
        collection.clear();
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (var band : collection) {
            info.append(band).append("\n");
        }
        return info.toString().trim();
    }

    /**
     * Возвращает тип коллекции
     * @return Тип коллекции (ArrayDeque, PriorityQueue и т. п.)
     */
    public String collectionType() {
        return collection.getClass().getName();
    }

    /**
     * Возвращает количество элементов в коллекции
     * @return Количество элементов
     */
    public int collectionSize() {
        return collection.size();
    }
}