package bankingapp.src.app;
import bankingapp.src.exceptions.BankingException;
import bankingapp.src.models.BOB;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static bankingapp.src.models.BOB.*;


class BankingNew {

    public static final String ICON_BANK = "\uD83C\uDFE6";
    public static final String ICON_MONEY = "\uD83D\uDCB0";
    public static final String ICON_DEPOSIT = "\u2705";
    public static final String ICON_WITHDRAW = "\u2B07\uFE0F";
    public static final String ICON_BALANCE = "\uD83D\uDCB8";
    public static final String ICON_INTEREST = "\uD83D\uDCCA";
    public static final String ICON_LOAN = "\uD83D\uDCB5";
    public static final String ICON_EMI = "\uD83D\uDCCB";
    public static final String ICON_PAYMENT = "\uD83D\uDCB3";
    public static final String ICON_DETAILS = "\u2139\uFE0F";
    public static final String ICON_ACCOUNT = "\uD83D\uDD11";
    public static final String ICON_EXIT = "\u274C";

    private static BOB findAccount(Map<String, BOB> accounts, String accountNumber) {
        return accounts.get(accountNumber);
    }

    private static void BankingOperations() {
        Map<String, BOB> accounts = new HashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        try (Scanner sc = new Scanner(System.in)) {

            int choice;

            do {
                System.out.println(BLUE + "\n" + ICON_BANK + "  Welcome to Bank of Baroda" + RESET);
                System.out.println(GREEN + "============================================" + RESET);
                System.out.println(GREEN  + "1.  " + ICON_DEPOSIT  + "  Deposit Money" + RESET);
                System.out.println(GREEN  + "2.  " + ICON_WITHDRAW + "  Withdraw Money" + RESET);
                System.out.println(GREEN   + "3.  " + ICON_BALANCE  + "  Check Balance" + RESET);
                System.out.println(GREEN   + "4.  " + ICON_INTEREST + "  Check Interest Rate" + RESET);
                System.out.println(YELLOW + "5.  " + ICON_LOAN     + "  Request Loan" + RESET);
                System.out.println(YELLOW + "6.  " + ICON_EMI      + "  Calculate EMI" + RESET);
                System.out.println(YELLOW + "7.  " + ICON_PAYMENT  + "  Make Loan Payment" + RESET);
                System.out.println(BLUE   + "8.  " + ICON_DETAILS  + "  Display Account Details" + RESET);
                System.out.println(BLUE   + "9.  " + ICON_ACCOUNT  + "  Create New Account" + RESET);
                System.out.println(RED    + "0.  " + ICON_EXIT     + "  Exit" + RESET);
                System.out.println(GREEN + "============================================" + RESET);
                System.out.println("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter account number: ");
                        String searchAccount1 = sc.nextLine();
                        System.out.println("Enter amount to deposit: ");
                        double depAmount = sc.nextDouble();
                        if(depAmount<=0){
                            System.out.println("Amount should be more than 0");
                            break;
                        }
                        BOB accountToDeposit = findAccount(accounts, searchAccount1);
                        if (accountToDeposit != null) {
                            Future<?> future = executor.submit(() -> accountToDeposit.deposit(depAmount));
                            future.get();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 2:
                        sc.nextLine();
                        System.out.println("Enter account number: ");
                        String searchAccount2 = sc.nextLine();
                        System.out.println("Enter amount to withdraw: ");
                        double withAmount = sc.nextDouble();
                        BOB accountToWithdraw = findAccount(accounts, searchAccount2);
                        if (accountToWithdraw != null) {
                            if(accountToWithdraw.getBalance()-withAmount<=2000){
                                System.out.println("Minimum balance should remain more than 2000 in your account Your Current Balance " + accountToWithdraw.getBalance());
                                break;
                            }
                            Future<?> future = executor.submit(() -> {
                                try {
                                    accountToWithdraw.withdraw(withAmount);
                                } catch (BankingException  e) {
                                    System.out.println(e.getMessage());
                                }
                            });
                            future.get();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 3:
                        sc.nextLine();
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
                        sc.nextLine();
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
                        sc.nextLine();
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
                        sc.nextLine();
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
                        sc.nextLine();
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

                        if(newName.length()<2){
                            System.out.println("Length Length Shouble be more than 2");
                            break;

                        }

                        BOB newAccount = new BOB(newName, newBalance);

                        BOB existingAccount = findAccount(accounts, newAccount.getAccountNumber());
                        if (existingAccount != null) {
                            System.out.println("Account with this number already exists.");
                            break;
                        }

                        if(newBalance<2000){
                            System.out.println("Initial Balance Should be More Than 2000");
                            break;
                        }

                        newAccount.accountCreation();
                        accounts.put(newAccount.getAccountNumber(), newAccount);
                        break;

                    case 10:
                        sc.nextLine();
                        System.out.println("Enter account number: ");
                        String searchAccount10 = sc.nextLine();
                        BOB checkTransactionHistory = findAccount(accounts,searchAccount10);

                        if(checkTransactionHistory!=null){
                            checkTransactionHistory.checkTransactionHistory();
                        }else{
                            System.out.println("Account Not found");
                        }


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