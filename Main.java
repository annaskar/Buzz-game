import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, let's play Buzz!");
        System.out.println("How many players are you 1 or 2");

        /*Scanner sc = new Scanner(System.in); //System.in is a standard input stream.
        String ok = sc.nextLine(); //reads string.

        Game a = new Game();
        if(ok.equals("1"))
            a.playGame1();
        else if(ok.equals("2"))
            a.playGame2();
        else
            return;*/
        GuiBuzz a = new GuiBuzz();
        a.playGui();
    }
}