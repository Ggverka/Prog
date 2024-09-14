package commands;
import managers.CommandManager;
import util.Console;
public class Help extends Command{
    private final Console console;
    private final CommandManager commandManager;

    public Help(Console console, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.console = console;
        this.commandManager = commandManager;
    }

    @Override
    public boolean execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование команды '" + getName() + "' не подразумевает аргументов");
            return false;
        }
        commandManager.getCommands().values().forEach(command -> console.printTable(command.getName(), command.getDescription()));
        return true;
    }
}
