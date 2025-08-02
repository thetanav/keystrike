import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

        for (;;) {
            System.out.println("> Strike space on red emoji.");
            long startTime = 0;
            try {
                Thread.sleep((long) (Math.random()*1000 + 1000));
                System.out.print("\uD83D\uDFE1 ");
                Thread.sleep((long) (Math.random()*1000 + 500));
                System.out.print("\uD83D\uDFE0 ");
                Thread.sleep((long) (Math.random()*1000 + 500));
                System.out.println("\uD83D\uDD34");
                startTime = System.currentTimeMillis();
            } catch (InterruptedException _) {
            }

            scanner.nextLine();
            long endTime = System.currentTimeMillis();
            if (startTime == 0) {
                System.out.println(">> Too early try again.");
            } else {
                System.out.printf(">> Your reaction time: %d ms.\n", endTime - startTime);
            }
            System.out.println(">> Play again? [y?/n]");
            String in = scanner.nextLine();
            if (!in.isEmpty() && in.charAt(0) == 'n') {
                break;
            }
        }
        scanner.close();
    }
}