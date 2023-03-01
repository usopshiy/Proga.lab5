import collection.RouteCollectionHandler;
import file.FileHandler;


public class Main {

    public static void main(String[] args) {
        System.out.print("\t\t\t\t Lab5 v0.1\n");
    System.out.print("""
              _____     ____    _    _   _______   ______    _____\s
             |  __ \\   / __ \\  | |  | | |__   __| |  ____|  / ____|
             | |__) | | |  | | | |  | |    | |    | |__    | (___ \s
             |  _  /  | |  | | | |  | |    | |    |  __|    \\___ \\\s
             | | \\ \\  | |__| | | |__| |    | |    | |____   ____) |
             |_|  \\_\\  \\____/   \\____/     |_|    |______| |_____/\s
            """);
        System.out.println("\t    by Egor Dashkevich aka usopshiy");


        FileHandler fileHandler = new FileHandler();
        RouteCollectionHandler collectionHandler = new RouteCollectionHandler();
        if (args.length!=0){
            fileHandler.setPath(args[0].trim());
            collectionHandler.deserializeCollection(fileHandler.read());
        }

    }
}
