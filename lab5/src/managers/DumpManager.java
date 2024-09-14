package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;

import models.MusicBand;
import util.Console;
import util.DateTimeAdapter;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.ArrayDeque;

public class DumpManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(ZonedDateTime.class, new DateTimeAdapter())
            .create();

    private final String fileName;
    private final Console console;

    public DumpManager(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Записывает коллекцию на файл, переводя в формат .json
     * @param collection коллекция, хранящаяся в менеджере коллекции
     */
    public void writeCollection(ArrayDeque<MusicBand> collection) {
        try (PrintWriter collectionPrintWriter = new PrintWriter(new File(fileName))) {
            collectionPrintWriter.println(gson.toJson(collection));
            console.println("Коллекция успешна сохранена в файл!");
        } catch (IOException exception) {
            console.printError("Файл не может быть открыт!");
        }
    }

    /**
     * Считывает коллекцию с файла формата .json
     * @return Коллекция, считанная с файла
     */
    public ArrayDeque<MusicBand> readCollection() {
        if (fileName != null && !fileName.isEmpty()) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName))) {
                var collectionType = new TypeToken<ArrayDeque<MusicBand>>() {}.getType();
                var reader = new BufferedReader(inputStreamReader);

                var jsonString = new StringBuilder();

                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.length() == 0) {
                    jsonString = new StringBuilder("[]");
                }

                ArrayDeque<MusicBand> collection = gson.fromJson(jsonString.toString(),
                        collectionType);

                console.println("Коллекция успешна загружена из файла!");
                return collection;

            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                console.printError("Загрузочный файл пуст!");
            } catch (JsonParseException exception) {
                console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException | IOException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new ArrayDeque<>();
    }
}
