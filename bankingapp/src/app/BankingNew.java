package bankingapp.src.app;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import bankingapp.src.exceptions.BankingException;
import bankingapp.src.models.BOB;

class BankingNew {

    private static BOB findAccount(Map<String, BOB> accounts, String accountNumber) {
        return accounts.get(accountNumber);
    }

    private static void BankingOperations() {
        Map<String, BOB> accounts = new HashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        try (Scanner sc = new Scanner(System.in)) {

            int choice;

            do {
                System.out.println("\n  Welcome to Bank of Baroda");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Check Interest Rate");
                System.out.println("5. Request Loan");
                System.out.println("6. Calculate EMI");
                System.out.println("7. Make Loan Payment");
                System.out.println("8. Display Account Details");
                System.out.println("9. Account Creation");
                System.out.println("0. Exit");
                System.out.println("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter account number: ");
                        String searchAccount1 = sc.nextLine();
                        System.out.println("Enter amount to deposit: ");
                        double depAmount = sc.nextDouble();
                        BOB accountToDeposit = findAccount(accounts, searchAccount1);
                        if (accountToDeposit != null) {
                            Future<?> future = executor.submit(() -> accountToDeposit.deposit(depAmount));
                            future.get();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter account number: ");
                        String searchAccount2 = sc.nextLine();
                        System.out.println("Enter amount to withdraw: ");
                        double withAmount = sc.nextDouble();
                        BOB accountToWithdraw = findAccount(accounts, searchAccount2);
                        if (accountToWithdraw != null) {
                            Future<?> future = executor.submit(() -> {
                                try {
                                    accountToWithdraw.withdraw(withAmount);
                                } catch (BankingException e) {
                                    System.out.println(e.getMessage());
                                }
                            });
                            future.get();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Enter account number: ");
                        String searchAccount3 = sc.nextLine();
                        BOB accountBalance = findAccount(accounts, searchAccount3);
                        if (accountBalance != null) {
                            accountBalance.checkBalance();
                        } else {
                            System.out.println("Account not found.");
                        }

                        break;
                    case 4:
                        System.out.println("Enter account number: ");
                        String searchAccount4 = sc.nextLine();
                        BOB interestRate = findAccount(accounts, searchAccount4);
                        if (interestRate != null) {
                            interestRate.checkInterestRate();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 5:
                        System.out.println("Enter account number: ");
                        String searchAccount5 = sc.nextLine();
                        System.out.println("Enter loan amount: ");
                        int loanAmount = sc.nextInt();
                        System.out.println("Enter tenure (in years): ");
                        int tenure = sc.nextInt();

                        BOB accountToLoan = findAccount(accounts, searchAccount5);
                        if (accountToLoan != null) {
                            Future<?> future = executor.submit(() -> accountToLoan.loan(loanAmount, tenure));
                            future.get();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 6:
                        System.out.println("Enter account number: ");
                        String searchAccount9 = sc.nextLine();
                        BOB accountToEMI = findAccount(accounts, searchAccount9);
                        if (accountToEMI != null) {
                            accountToEMI.calculateEMI();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 7:
                        System.out.println("Enter account number: ");
                        String searchAccount6 = sc.nextLine();
                        System.out.println("Enter payment amount towards loan: ");
                        double paymentAmount = sc.nextDouble();
                        BOB accountToPayment = findAccount(accounts, searchAccount6);
                        if (accountToPayment != null) {
                            executor.submit(() -> accountToPayment.loanPayment(paymentAmount));

                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 8:
                        sc.nextLine();
                        System.out.println("Enter account number: ");
                        String searchAccount7 = sc.nextLine();
                        BOB accountToDisplay = findAccount(accounts, searchAccount7);
                        if (accountToDisplay != null) {
                            accountToDisplay.displayAccountDetails();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 9:
                        sc.nextLine();
                        System.out.println("Enter Account Holder Name: ");
                        String newName = sc.nextLine();
                        System.out.println("Enter Initial Balance: ");
                        int newBalance = sc.nextInt();

                        BOB newAccount = new BOB(newName, newBalance);

                        BOB existingAccount = findAccount(accounts, newAccount.getAccountNumber());
                        if (existingAccount != null) {
                            System.out.println("Account with this number already exists.");
                            break;
                        }

                        newAccount.accountCreation();
                        accounts.put(newAccount.getAccountNumber(), newAccount);
                        break;

                    case 0:
                        System.out.println("Thank you for using Bank of Baroda.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                
            } while (choice != 0);
        } catch (InterruptedException e) {
            System.out.println("Operation was interrupted.");
        } catch (ExecutionException e) {
            System.out.println("An error occurred during the operation: " + e.getCause().getMessage());
        }

    }

    public static void main(String[] args) throws BankingException {
        BankingOperations();
    }
}