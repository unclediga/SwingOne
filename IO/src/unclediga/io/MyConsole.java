package unclediga.io;

import java.io.Console;

public class MyConsole {

    public static void main(String[] args) {

        Console c = System.console();
        String ln = "";
        while (!ln.toLowerCase().equals("exit") ){
            c.format("Enter line:");
            ln = c.readLine();
            System.out.format("Entered: %s\n",ln);
        }

    }
}
