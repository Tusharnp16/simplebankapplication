package bankingapp.src.models;

import java.util.Random;

import bankingapp.src.exceptions.BankingException;
import bankingapp.src.interfaces.BankingOperations;

class BOB extends Bank implements BankingOperations {
    private final static String bankName = "Bank of Baroda";
    private final static String branchName = "Iscon branch";
    private static int nextAccountNumber = 1001;
    private String accountHolderName;
    private String accountNumber;
    private int balance;
    private int loanAmount;
    private int tenure;
    private double interestRate = 9.5;
    private double monthlyRate;
    private int months;
    private double emiAmount;
    Random rand = new Random();

    int ifscCode = rand.nextInt(9000, 9999);

    BOB(String accountHolderName, int balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountNumber = accounNumberGenerator();
        setNameAndBranch(bankName, branchName);
    }

    private String accounNumberGenerator() {
        String prefix = "BOB" + branchName.substring(0, 2).toUpperCase()
                + accountHolderName.substring(0, 2).toUpperCase();
        String accNumber = prefix + nextAccountNumber++;
        return accNumber;
    }

    void accountCreation() {
        super.accountCreation();
        System.out.println("Account created successfully for " + accountHolderName);
        displayAccountDetails();
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

    public String getAccountNumber() {
        return accountNumber;
    }
}
