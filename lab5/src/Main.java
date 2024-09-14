import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DumpManager;
import models.*;
import util.App;
import util.StandardConsole;
import util.Interrogator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Query.QueryBreak {
        Interrogator.setUserScanner(new Scanner(System.in));
        var console = new StandardConsole();
        System.setProperty("LAB", "/home/studs/s409171/Prog/lab5");
        String File = System.getProperty("LAB") + "/test.json";

        DumpManager dumpManager = new DumpManager(File, console);
        CollectionManager collectionManager = new CollectionManager(dumpManager);

        CommandManager commandManager = new CommandManager() {{
            register("add_if_min",new AddIfMin(console, collectionManager));
            register("clear",new Clear(console, collectionManager));
            register("execute_script",new ExecuteScript(console));
            register("exit",new Exit(console));
            register("help",new Help(console, this));
            register("info",new Info(console, collectionManager));
            register("remove_by_id",new Remove(console, collectionManager));
            register("remove_first",new RemoveFirst(console, collectionManager));
            register("remove_lower",new RemoveLower(console, collectionManager));
            register("count_by_genre",new CountBy(console, collectionManager));
            register("save",new Save(console, collectionManager));
            register("show",new Show(console, collectionManager));
            register("update",new UpdateID(console, collectionManager));
            register("filter_by_singles_count",new FilterBy(console, collectionManager));
            register("add",new Add(console, collectionManager));
            register("print_field_descending_number_of_participants",new PrintFieldDescendingNumberOfParticipants(console, collectionManager));
        }};

        new App(console, commandManager).interactiveMode();
    }
}