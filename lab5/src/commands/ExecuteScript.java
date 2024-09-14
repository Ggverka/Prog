package commands;

import util.Console;

public class ExecuteScript extends Command {
    private final Console console;

    public ExecuteScript(Console console) {
        super("execute_script file_name", "исполнить скрипт из указанного файла");
        this.console = console;
    }

    @Override
    public boolean execute(String[] arguments) {
        console.println("Скрипт '" + arguments[1] + "' исполняется ...");
        return true;
    }
}
