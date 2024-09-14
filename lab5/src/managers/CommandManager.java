package managers;

import commands.Command;
import java.util.*;
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
