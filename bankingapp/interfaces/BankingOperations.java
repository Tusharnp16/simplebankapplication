package bankingapp.src.interfaces;
import bankingapp.src.exceptions.BankingException;

public interface BankingOperations {
    void deposit(double amount);

    void withdraw(double amount) throws BankingException;

    void checkBalance();

    void checkInterestRate();

    void loan(int loanAmount, int tenure);

    void calculateEMI();

    void loanPayment(double amount);
}