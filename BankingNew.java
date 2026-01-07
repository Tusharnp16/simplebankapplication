import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

interface BankingOperations {
    void deposit(double amount);

    void withdraw(double amount) throws BankingException;

    void checkBalance();

    void checkInterestRate();

    void loan(int loanAmount, int tenure);

    void calculateEMI();

    void loanPayment(double amount);
}

abstract class IntialBank {
    abstract void accountCreation();
}

class Bank extends IntialBank {
    private String bankName;
    private String branchName;

    void setNameAndBranch(String bankName, String branchName) {
        this.bankName = bankName;
        this.branchName = branchName;
    }

    void accountCreation() {
        System.out.println("Account created successfully in " + bankName);
        System.out.println("Branch Name: " + branchName);
    }
}

class BOB extends Bank implements BankingOperations {
    private String bankName = "Bank of Baroda";
    private String branchName = "Iscon branch";
    private String accountHolderName;
    private int accountNumber;
    private int balance;
    private int loanAmount;
    private int tenure;
    private double interestRate = 9.5;
    private double monthlyRate;
    private int months;
    private double emiAmount;

    Random rand = new Random();

    int ifscCode = rand.nextInt(9000, 9999);

    BOB(String accountHolderName, int accountNumber, int balance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        setNameAndBranch(bankName, branchName);
    }

    void accountCreation() {
        super.accountCreation();
        System.out.println("Account created successfully for " + accountHolderName);
    }

    public void displayAccountDetails() {
        // super.display();
        System.out.println("Bank Name: " + bankName);
        System.out.println("IFSC Code: " + ifscCode);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    public synchronized void deposit(double amount) {
        System.out.println("Depositing Amount....Please wait...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           System.out.println("Deposit operation interrupted.");
        }
        balance += amount;
        System.out.println("Amount Deposited : " + amount);
        System.out.println("New Balance: " + balance);
    }

    public synchronized void withdraw(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Invalid withdrawal amount. Value should more than 0.");
        }
        if (amount > balance) {
            throw new BankingException("Insufficient balance. Please provide a valid amount.");
        }
        System.out.println("Withdrawing Amount....Please wait...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Withdraw operation interrupted.");
        }
        balance -= amount;
        System.out.println("Withdrawn Amount: " + amount);
        System.out.println("New Balance: " + balance);
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    public void checkInterestRate() {
        System.out.println("Current Interest Rate: " + interestRate + "%");
    }

    public synchronized void loan(int loanAmount, int tenure) {

        try {
            if (loanAmount <= 0 || tenure <= 0) {
                throw new Exception("Invalid loan amount or tenure. Please provide positive values.");
            }
            this.loanAmount = loanAmount;
            this.tenure = tenure;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Loan Amount Requested: " + loanAmount);
        System.out.println("Loan Tenure (years): " + tenure);
        System.out.println("Interest Rate: " + interestRate + "%");
    }

    public void calculateEMI() {

        try {
            if (loanAmount == 0 || tenure == 0) {
                throw new Exception("Loan details not provided. Please request a loan first.");
            }
            monthlyRate = interestRate / 12 / 100;
            months = tenure * 12;
            emiAmount = Math.ceil((loanAmount * monthlyRate * Math.pow(1 + monthlyRate, months))
                    / (Math.pow(1 + monthlyRate, months) - 1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.printf("Monthly EMI: %.2f%n", emiAmount);
        System.out.printf("Total Payment over %d years: %.2f%n", tenure, (emiAmount * months));
        System.out.printf("Total Interest Paid: %.2f%n", (emiAmount * months - loanAmount));
    }

    public synchronized void loanPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount. Please provide a positive value.");
            return;
        }
        if (amount > loanAmount) {
            System.out.println("Payment exceeds loan amount. Please provide a valid amount.");
            return;
        }

        if (emiAmount > amount) {
            System.out.println("Payment amount is less than EMI. Please pay at least the EMI amount: " + emiAmount);
            return;
        }

        loanAmount -= amount;
        System.out.println("Payment of " + amount + " made towards loan. Remaining loan amount: " + loanAmount);
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
}

class BankingNew {

    private static BOB findAccount(List<BOB> accounts, int accountNumber) {
        for (BOB account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) throws BankingException {

        // Bank bank=new Bank();

        // System.out.println("Enter Account Holder Name: ");
        // String name=sc.nextLine();

        // System.out.println("Enter Account Number: ");
        // int accNum=sc.nextInt();

        // System.out.println("Enter Initial Balance: ");
        // int balance=sc.nextInt();

        List<BOB> accounts = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // BOB b1=new BOB(name,accNum,balance);
        // b1.accountCreation();

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
                        System.out.println("Enter account number: ");
                        int searchAccount1 = sc.nextInt();
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
                        int searchAccount2 = sc.nextInt();
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
                        int searchAccount3 = sc.nextInt();
                         BOB accountBalance = findAccount(accounts, searchAccount3);
                        if (accountBalance != null) {
                            accountBalance.checkBalance();
                        } else {
                            System.out.println("Account not found.");
                        }

                        break;
                    case 4:
                        System.out.println("Enter account number: ");
                        int searchAccount4 = sc.nextInt();
                        BOB interestRate = findAccount(accounts, searchAccount4);
                        if (interestRate != null) {
                            interestRate.checkInterestRate();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 5:
                        System.out.println("Enter account number: ");
                        int searchAccount5 = sc.nextInt();
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
                        int searchAccount9 = sc.nextInt();
                        BOB accountToEMI = findAccount(accounts, searchAccount9);
                        if (accountToEMI != null) {
                            accountToEMI.calculateEMI();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 7:
                        System.out.println("Enter account number: ");
                        int searchAccount6 = sc.nextInt();
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
                        System.out.println("Enter account number: ");
                        int searchAccount7 = sc.nextInt();
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
                        System.out.println("Enter Account Number: ");
                        int newAccNum = sc.nextInt();
                        System.out.println("Enter Initial Balance: ");
                        int newBalance = sc.nextInt();

                        BOB existingAccount = findAccount(accounts, newAccNum);
                        if (existingAccount != null) {
                            System.out.println("Account with this number already exists.");
                            break;
                        }
                        
                        BOB newAccount = new BOB(newName, newAccNum, newBalance);
                        newAccount.accountCreation();
                        accounts.add(newAccount);
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

        // b1.display();
        // b1.deposit(5000);
        // b1.withdraw(2000);
        // b1.checkBalance();
        // b1.checkInterestRate();
        // b1.loan(5000,5);
        // b1.caluEMI();
        // b1.loanPayment(106);
        // b1.caluEMI();
        // b1.loanPayment(106);
        // b1.caluEMI();

    }
}