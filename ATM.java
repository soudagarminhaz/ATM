import java.util.Scanner;
import java.util.ArrayList;

class Account {
    private int accountNumber;
    private int pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        transactionHistory = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setPin(int newPin) {
        pin = newPin;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

public class ATM {
    private Account account;

    public ATM(Account account) {
        this.account = account;
    }

    public void checkBalance() {
        System.out.println("Your balance is: $" + account.getBalance());
    }

    public void withdrawCash(double amount) {
        account.withdraw(amount);
        System.out.println("Withdrawal successful.");
    }

    public void depositCash(double amount) {
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    public void changePin(int newPin) {
        account.setPin(newPin);
        System.out.println("PIN changed successfully.");
    }

    public void checkTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        // Sample account data
        Account account = new Account(123456, 1234, 1000.0);

        ATM atm = new ATM(account);

        Scanner scanner = new Scanner(System.in);

        // Simulate ATM interface
        System.out.println("Welcome to the ATM");
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (enteredPin == account.getPin()) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nATM Menu");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw Cash");
                System.out.println("3. Deposit Cash");
                System.out.println("4. Change PIN");
                System.out.println("5. Check Transaction History");
                System.out.println("6. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        atm.withdrawCash(withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        atm.depositCash(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter new PIN: ");
                        int newPin = scanner.nextInt();
                        atm.changePin(newPin);
                        break;
                    case 5:
                        atm.checkTransactionHistory();
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Thank you for using the ATM.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Incorrect PIN.");
        }
    }
}
