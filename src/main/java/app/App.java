package app;

import java.util.Scanner;

import app.web.WebApplication;

public class App {
    private String nameProgram = "Console app";
    private String versionProgram = "0.0.2";
    private Scanner scanner;
    private boolean gettingInput = true;
    private boolean debug = false;
    private WebApplication webApplication;

    private String scanner(String colorInput) {
        this.scanner = new Scanner(System.in, "utf-8");
        switch (colorInput) {
            case "yellow":
                System.out.print(ColorTerminal.bold() + ColorTerminal.yellow() + ">> " + ColorTerminal.end());
                break;
            case "white":
                System.out.print(ColorTerminal.bold() + ColorTerminal.white() + ">> " + ColorTerminal.end());
                break;
            case "blue":
                System.out.print(ColorTerminal.bold() + ColorTerminal.blue() + ">> " + ColorTerminal.end());
                break;
            default:
                System.out.print(ColorTerminal.bold() + ColorTerminal.white() + ">> " + ColorTerminal.end());
                break;
        }
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void userInput() {
        System.out.println(this.fullNameProgram());

        while (this.gettingInput) {
            String userInput = this.scanner("yellow");
            this.choiceUserInTerminal(userInput);   
        }
    }

    private void choiceUserInTerminal(String userInput) {
        switch (userInput) {
            case "exit":
                this.scanner.close();
                System.exit(0);
                break;
            case "clear":
                this.clearTerminal();           
                break;
            case "web start":
                this.gettingInput = false;
                this.openWebGui();
                break;
            case "web close":
                this.closeWebGui();
                break;
            case "debug":
                this.changeDebug();
                break;
            case "help":
                this.helpInfo();
                break;
            default:
                System.out.println(ColorTerminal.red() + "Неопознанный ввод, введите 'exit', чтобы завершить программу." + ColorTerminal.end());
        }

        if (debug) {
            Log.log("Ввод пользователя", userInput);
        }
    }

    private void helpInfo() {
        System.out.println(ColorTerminal.yellow() + "help" + ColorTerminal.end() + " - справочная информация по командам.");
        System.out.println(ColorTerminal.yellow() + "clear" + ColorTerminal.end() + " - очистка терминала.");
        System.out.println(ColorTerminal.yellow() + "web start" + ColorTerminal.end() + " - открытие веб-интерфейса: " + ColorTerminal.blue() + "http://localhost:8080" + ColorTerminal.end() + " " + ColorTerminal.green() + "(блокирует ввод терминала)." + ColorTerminal.end());
        System.out.println(ColorTerminal.yellow() + "web exit" + ColorTerminal.end() + " - закрытие веб-интерфейса" + " " + ColorTerminal.green() + "(закрыть можно в самом интерфейсе, для разблокировки ввода).");
        System.out.println(ColorTerminal.yellow() + "debug" + ColorTerminal.end() + " - поменять режим отладки.");
        System.out.println(ColorTerminal.yellow() + "exit" + ColorTerminal.end() + " - завершение процесса.");
    }

    private void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(ColorTerminal.red() + "Ошибка очистки терминала!" + ColorTerminal.end());
            if (debug) {
                System.out.println(ColorTerminal.green() + "Рекомендация: отжаться 10 раз." + ColorTerminal.end());
            }
        }
        System.out.println(this.fullNameProgram());
    }

    private String fullNameProgram() {
        return nameProgram + " " + versionProgram;
    }

    private void openWebGui() {
        if (debug) Log.log("интерфейс", "запуск веб-интерфейса.");
        this.webApplication = new WebApplication();
        this.webApplication.start();
        if (debug) Log.log("интерфейс", "веб-интерфейс запущен.");
    }

    private void closeWebGui() {
        if (debug) Log.log("интерфейс", "закрытие веб-интерфейса.");
        if (this.webApplication != null) {
            this.webApplication.stop();
        }
    }

    public void changeDebug() {
        if (this.debug == true) {
            this.debug = false;
            Log.log("Отладка", "режим отладки выключен!");
        } else {
            this.debug = true;
            Log.log("Отладка", "режим отладки включён!");
        }
    }

    public String getFullNameProgram() {
        return this.fullNameProgram();
    }

    public boolean getDebug() {
        return this.debug;
    }
}
