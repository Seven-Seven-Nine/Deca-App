package app;

public class Main {
    public static void main(String[] args) {
        User user = new User("deca", "user", 0);
        App app = new App(user);

        if (args.length > 0) {
            for (int index = 0; index < args.length; index++) {
                if (args[index].equals("--debug")) {
                    app.changeDebug();
                } else if (args[index].equals("--help")) {
                    System.out.println("Доступные аргументы для " + app.getFullNameProgram());
                    System.out.println(ColorTerminal.bold() + ColorTerminal.yellow() + "--debug" + ColorTerminal.end() + " - режим отладки.");
                    System.out.println(ColorTerminal.bold() + ColorTerminal.yellow() + "--help" + ColorTerminal.end() + " - справочная информация.");
                    System.exit(0);
                } else {
                    System.out.println(ColorTerminal.red() + "Неизвестный аргумент: " + ColorTerminal.end() + args[index]);
                    System.exit(1);
                }
            }
        }
        app.userInput();
    }
}
