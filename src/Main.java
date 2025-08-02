import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("""
        
         _  __          ____  _        _ _       \s
        | |/ /___ _   _/ ___|| |_ _ __(_) | _____\s
        | ' // _ \\ | | \\___ \\| __| '__| | |/ / _ \\
        | . \\  __/ |_| |___) | |_| |  | |   <  __/
        |_|\\_\\___|\\__, |____/ \\__|_|  |_|_|\\_\\___|
                  |___/                          \s
               Press [Enter] on 🔴 to strike!
        """);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("> Strike [Enter] when 🔴 appears.");

            try {
                Thread.sleep((long) (Math.random() * 1000 + 1000));
                System.out.print("\uD83D\uDFE1 ");
                Thread.sleep((long) (Math.random() * 1000 + 500));
                System.out.print("\uD83D\uDFE0 ");
                Thread.sleep((long) (Math.random() * 1000 + 500));
                System.out.println("\uD83D\uDD34"); // RED
            } catch (InterruptedException ignored) {}

            long startTime = System.currentTimeMillis();
            String key = scanner.nextLine(); // read one key
            long endTime = System.currentTimeMillis();

            if (startTime == endTime) {
                System.out.println(">> Too early! Try again.");
            } else {
                System.out.printf(">> Your reaction time: %d ms.%n", endTime - startTime);
            }

            System.out.println(">> Play again? [y/n]");
            String inp = scanner.nextLine();
            if (inp.charAt(0) == 'n' || inp.charAt(0) == 'N') {
                break;
            }
        }

        scanner.close();
        System.out.println(">> Thanks for playing! 👋");
    }
}
