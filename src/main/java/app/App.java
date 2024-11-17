package app;

import java.util.Scanner;

import app.web.WebApplication;

public class App {
    private String nameProgram = "Console app";
    private String versionProgram = "0.1.2";
    private String locale = "en";

    private Scanner scanner;
    private boolean gettingInput = true;
    private boolean debug = false;

    private WebApplication webApplication;

    private User user;

    public App(User user) {
        this.user = user;
    }

    private String scanner(String colorInput) {
        this.scanner = new Scanner(System.in, "utf-8");
        switch (colorInput) {
            case "yellow":
                System.out.print(ColorTerminal.green() + ColorTerminal.underlined() + user.getName() + ColorTerminal.end() + ColorTerminal.bold() + ColorTerminal.yellow() + " >> " + ColorTerminal.end());
                break;
            case "white":
                System.out.print(ColorTerminal.green() +  ColorTerminal.underlined() + user.getName() + ColorTerminal.end() + ColorTerminal.bold() + ColorTerminal.white() + " >> " + ColorTerminal.end());
                break;
            case "blue":
                System.out.print(ColorTerminal.green() + ColorTerminal.underlined() + user.getName() + ColorTerminal.end() + ColorTerminal.bold() + ColorTerminal.blue() + " >> " + ColorTerminal.end());
                break;
            default:
                System.out.print(ColorTerminal.green() + ColorTerminal.underlined() + user.getName() + ColorTerminal.end() + ColorTerminal.bold() + ColorTerminal.white() + " >> " + ColorTerminal.end());
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
                String sentenceError = "Неопознанный ввод, введите 'exit', чтобы завершить программу.";
                if (this.locale == "en") sentenceError = "Undefined input, type 'exit', to close this program.";
                System.out.println(ColorTerminal.red() + sentenceError + ColorTerminal.end());
        }

        if (debug) {
            String sentenceLog = "Ввод пользователя";
            if (this.locale == "en") sentenceLog = "Input user";
            Log.log(sentenceLog, userInput);
        }
    }

    private void helpInfo() {
        String sentenceHelp = "справочная информация по командам.";
        String sentenceClear = "очистка терминала.";
        
        String sentenceWebStart = "открытие веб-интерфейса:";
        String secondSentenceWebStart = "(блокирует ввод терминала).";

        String sentenceWebExit = "закрытие веб-интерфейса";
        String secondSentenceWebExit = "(закрыть можно в самом интерфейсе, для разблокировки ввода).";

        String sentenceDebug = "поменять режим отладки.";
        String sentenceExit = "завершение процесса.";

        if (this.locale == "en") {
            sentenceHelp = "help information for commands.";
            sentenceClear = "clear terminal.";

            sentenceWebStart = "open the web-gui:";
            secondSentenceWebStart = "block input in the terminal.";

            sentenceWebExit = "close the web-gui";
            secondSentenceWebExit = "(close the web interface to unlock input in the terminal).";

            sentenceDebug = "change to debug mode.";
            sentenceExit = "close this program.";
        }

        System.out.println(ColorTerminal.yellow() + "help - " + ColorTerminal.end() + sentenceHelp);
        System.out.println(ColorTerminal.yellow() + "clear - " + ColorTerminal.end() + sentenceClear);
        System.out.println(ColorTerminal.yellow() + "web start - " + ColorTerminal.end() + sentenceWebStart + " " + ColorTerminal.blue() + "http://localhost:8080" + ColorTerminal.end() + " " + ColorTerminal.green() + secondSentenceWebStart + ColorTerminal.end());
        System.out.println(ColorTerminal.yellow() + "web exit - " + ColorTerminal.end() + sentenceWebExit + " " + ColorTerminal.green() + secondSentenceWebExit + ColorTerminal.end());
        System.out.println(ColorTerminal.yellow() + "debug - " + ColorTerminal.end() + sentenceDebug);
        System.out.println(ColorTerminal.yellow() + "exit - " + ColorTerminal.end() + sentenceExit);
    }

    private void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            String sentenceError = "Ошибка очистки терминала!";
            if (this.locale == "en") sentenceError = "Error clearing terminal!";

            System.out.println(ColorTerminal.red() + sentenceError + ColorTerminal.end());
            
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
        if (debug) {
            String logName = "интерфейс";
            String logMessage = "запуск веб-интерфейса.";
            if (this.locale == "en") {
                logName = "interface";
                logMessage = "start the web interface.";
            }
            Log.log(logName, logMessage);
        }
        this.webApplication = new WebApplication();
        this.webApplication.start();
        if (debug) {
            String logName = "интерфейс";
            String logMessage = "веб-интерфейс запущен.";
            if (this.locale == "en") {
                logName = "interface";
                logMessage = "launch the web interface.";
            }
            Log.log(logName, logMessage);
        }
    }

    private void closeWebGui() {
        if (debug) {
            String logName = "интерфейс";
            String logMessage = "закрытие веб-интерфейса.";
            if (this.locale == "en") {
                logName = "interface";
                logMessage = "close the web interface.";
            }
            Log.log(logName, logMessage);
        }
        if (this.webApplication != null) {
            this.webApplication.stop();
        }
    }

    public void changeDebug() {
        if (this.debug == true) {
            this.debug = false;
            String logName = "отладка";
            String logMessage = "режим отладки выключен!";
            if (this.locale == "en") {
                logName = "debug";
                logMessage = "Disable debug mode!";
            }
            Log.log(logName, logMessage);
        } else {
            this.debug = true;
            String logName = "отладка";
            String logMessage = "режим отладки включён!";
            if (this.locale == "en") {
                logName = "debug";
                logMessage = "Enable debug mode!";
            }
            Log.log(logName, logMessage);
        }
    }

    public String getFullNameProgram() {
        return this.fullNameProgram();
    }

    public boolean getDebug() {
        return this.debug;
    }
}
