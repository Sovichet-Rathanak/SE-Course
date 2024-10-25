import java.util.Scanner;

public class TP01_5 {
    private static volatile boolean GameOver = false;

    public static void main(String[] args) {
        int level = 1;
        Scanner scanner = new Scanner(System.in);

        while (!GameOver) {
            int A = getValidNumber(scanner, "Enter a positive number A (1-9): ");
            int B = getValidNumber(scanner, "Enter a positive number B (1-9): ");
            int correctAnswer = A + B;

            System.out.println("Level " + level + ": You have 2 seconds to answer!");
            System.out.println("(A + B) = ?");
            System.out.print("Enter your answer: ");

            CustomTimer timer = new CustomTimer();
            timer.start();

            int answer = -1;
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Answer must be a number.");
                scanner.nextLine(); 
            }

            if (answer == correctAnswer) {
                timer.interrupt();
                System.out.println("Correct answer!");
            } else {
                GameOver = true;
                System.out.println("Incorrect answer.");
            }

            System.out.println("Correct answer was: " + correctAnswer);
            System.out.println();

            if (GameOver) {
                System.out.println("Game Over. Baby Brain.");
            } else if (level == 10) {
                System.out.println("Congratulations! Master Brain.");
                GameOver = true;
            }
            level++;
        }
        scanner.close();
    }

    public static class CustomTimer extends Thread {
        public CustomTimer() {
            this.setDaemon(true);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("\nTime's up!");
                GameOver = true;
            } catch (InterruptedException e) {

            }
        }
    }

    public static int getValidNumber(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = scanner.nextInt();
                if (number > 0 && number < 10) {
                    return number;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 9.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
    }
}
