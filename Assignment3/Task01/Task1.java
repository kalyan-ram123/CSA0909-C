import java.util.InputMismatchException;
import java.util.Scanner;

public class Task1 {
    private static final double ACCOUNT_BALANCE = 5000.0;

    private static void withdraw(double amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Enter an amount greater than zero.");
            }
            if (amount > ACCOUNT_BALANCE) {
                throw new ArithmeticException("Insufficient account balance.");
            }
            System.out.printf("Withdrawal successful. Balance: %.2f%n", ACCOUNT_BALANCE - amount);
        } catch (IllegalArgumentException | ArithmeticException ex) {
            System.out.println("Transaction rejected: " + ex.getMessage());
        } finally {
            System.out.println("Transaction attempt finished.");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter withdrawal amount: ");
            withdraw(scanner.nextDouble());
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}
