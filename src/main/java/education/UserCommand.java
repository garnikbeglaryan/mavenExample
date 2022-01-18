package education;

public interface UserCommand {


    String LOGIN = "1";
    String REGISTOR = "2";
    String EXIT = "0";


    static void printCommands(){
        System.out.println("\u001B[34m" + "please input " + LOGIN + " for Login");
        System.out.println("please input " + REGISTOR + " for Registor");
        System.out.println("please input " + EXIT + " for Exit" + "\u001B[34m");
    }
}
