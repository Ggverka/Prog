package util;
public interface Console {
    void print(Object obj);
    void println(Object obj);
    String readln();
    void printError(Object obj);
    void printTable(Object obj1, Object obj2);
    void prompt();
    String getPrompt();
}
