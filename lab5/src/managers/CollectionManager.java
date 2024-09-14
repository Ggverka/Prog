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

    public ZonedDateTime getLastInitTime() {
        return lastInitTime;
    }

    public ZonedDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public ArrayDeque<MusicBand> getCollection() {
        return collection;
    }

    public MusicBand byId(int id) { return Band.get(id); }

    public int getFreeId() {
        while (byId(currentId) != null){
            currentId++;
        };
        return currentId;
    }

    public MusicBand getFirst() {
        if (collection.isEmpty()) return null;
        return collection.peek();
    }

    public void add(MusicBand a) {
        Band.put(a.getId(), a);
        collection.add(a);
        sort();
    }

    public void update(MusicBand a) {
        collection.remove(byId(a.getId()));
        Band.put(a.getId(), a);
        collection.add(a);
        sort();
    }

    public void remove(Integer id) {
        var a = byId(id);
        Band.remove(a.getId());
        collection.remove(a);
        sort();
    }

    public void sort() {
        List<MusicBand> sortedList = new ArrayList<>(collection);
        Collections.sort(sortedList);
        collection.clear();
        collection.addAll(sortedList);
    }

    public void init() {
        collection.addAll(dumpManager.readCollection());
        lastInitTime = ZonedDateTime.now();
        sort();
    }

    public int minSinglesCount() {
        return this.getCollection().stream()
                .map(MusicBand::getSinglesCount)
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
    }

    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = ZonedDateTime.now();
    }

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

    public String collectionType() {
        return collection.getClass().getName();
    }

    public int collectionSize() {
        return collection.size();
    }
}