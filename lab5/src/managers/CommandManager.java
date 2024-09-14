package managers;

import commands.Command;
import java.util.*;
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Добавляет новую команду в словарь (список) команд менеджера команд
     * @param commandName Название команды
     * @param command Объект класса Comand
     */
    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Возвращает словарь (список) команд менеджера команд
     * @return Словарь (список) команд
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
}
