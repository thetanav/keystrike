import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Long> reactions = new ArrayList<>();
    public static long mini = Long.MAX_VALUE;

    private static void printBanner() {
        System.out.println("""
        
         _  __          ____  _        _ _       \s
        | |/ /___ _   _/ ___|| |_ _ __(_) | _____\s
        | ' // _ \\ | | \\___ \\| __| '__| | |/ / _ \\
        | . \\  __/ |_| |___) | |_| |  | |   <  __/
        |_|\\_\\___|\\__, |____/ \\__|_|  |_|_|\\_\\___|
                  |___/                          \s
               Press [Enter] on 🔴 to strike!
        """);
    }

    public static void main(String[] args) {
        printBanner();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("> Strike [Enter] when 🔴 appears.");

                try {
                    System.out.print("🟡 ");
                    Thread.sleep((long) (Math.random() * 1000 + 500));
                    System.out.print("🟠 ");
                    Thread.sleep((long) (Math.random() * 1000 + 500));
                    System.out.println("🔴");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                ReactionTimer timer = new ReactionTimer(scanner);
                timer.start();

                long startTime = System.currentTimeMillis();
                timer.join(); // wait until the user presses Enter
                long reactionTime = timer.pressTime - startTime;

                if (reactionTime < 0 || timer.tooEarly) {
                    System.out.println(">> Too early! Try again.");
                } else {
                    System.out.printf(">> Your reaction time: %d ms.%n", reactionTime);
                    reactions.add(reactionTime);
                    mini = Math.min(mini, reactionTime);
                }

                System.out.println(">> Play again? [y/n]");
                String input = scanner.nextLine();
                if (input.trim().equalsIgnoreCase("n")) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long reactionsSum = reactions.stream().mapToLong(Long::longValue).sum();
        System.out.println("> Your average reaction time: " + (reactionsSum / reactions.size() + " ms"));
        System.out.println("> Your minimum reaction time: " + mini);
        System.out.println("> Thanks for playing! 👋 Made by Tanav.");
    }
}

class ReactionTimer extends Thread {
    private final Scanner scanner;
    public long pressTime = 0;
    public boolean tooEarly = false;

    public ReactionTimer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        long current = System.currentTimeMillis();
        scanner.nextLine(); // Wait for Enter
        pressTime = System.currentTimeMillis();

        // Optional: simulate early press detection logic
        if ((pressTime - current) < 20) {
            tooEarly = true;
        }
    }
}
