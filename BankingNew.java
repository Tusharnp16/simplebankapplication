import java.util.*;

abstract class intialBank{
    abstract void accountCreation();
}

class Bank extends intialBank{
    private String bankName;
    private String branchName;

    void setNameAndCode(String bankName,String branchName){
        this.bankName=bankName;
        this.branchName=branchName;
    }

    void accountCreation(){
        System.out.println("Account created successfully in "+bankName);
        System.out.println("Branch Name: "+branchName);
    }
}

class BOB extends Bank{
    String bankName="Bank of Baroda";
    String branchName="Iscon branch";
    String accountHolderName;
    int accountNumber;
    int balance;
    int loanAmount;
    int tenure;
    double interestRate=9.5;
    double monthlyRate;
    int months;
    double emiAmount;

    Random rand=new Random();

    int ifscCode=rand.nextInt(9000,9999);

    BOB(String accountHolderName,int accountNumber,int balance){
        this.accountHolderName=accountHolderName;
        this.accountNumber=accountNumber;
        this.balance=balance;
        setNameAndCode(bankName,branchName);
    }


    void accountCreation(){
        super.accountCreation();
        System.out.println("Account created successfully for "+accountHolderName);
    }

    public void display(){
       // super.display();
        System.out.println("Bank Name: "+bankName);
        System.out.println("IFSC Code: "+ifscCode);
        System.out.println("Account Holder Name: "+accountHolderName);
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Balance: "+balance);
    }

    void deposit(double amount){
        balance+=amount;
        System.out.println("Deposited Amount: "+amount);
        System.out.println("New Balance: "+balance);
    }


    void withdraw(double amount) throws BankingException{
        if(amount<=0){
            throw new BankingException("Invalid withdrawal amount. Value should more than 0.");
        }
        if(amount>balance){
            throw new BankingException("Insufficient balance. Please provide a valid amount.");
        }
        balance-=amount;
        System.out.println("Withdrawn Amount: "+amount);
        System.out.println("New Balance: "+balance);
    }

    void checkBalance(){
        System.out.println("Current Balance: "+balance);
    }

    void checkInterestRate(){
        System.out.println("Current Interest Rate: "+interestRate+"%");
    }

    void loan(int loanAmount, int tenure){

        try{
            if(loanAmount<=0 || tenure<=0){
                throw new Exception("Invalid loan amount or tenure. Please provide positive values.");
            }
            this.loanAmount=loanAmount;
            this.tenure=tenure;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
      
        System.out.println("Loan Amount Requested: "+loanAmount);
        System.out.println("Loan Tenure (years): "+tenure);
        System.out.println("Interest Rate: "+interestRate+"%");
    }

    void caluEMI(){
        
        try{
            if(loanAmount==0 || tenure==0){
                throw new Exception("Loan details not provided. Please request a loan first.");
            }
            monthlyRate = interestRate / 12 / 100; 
            months = tenure * 12; 
            emiAmount = Math.ceil((loanAmount * monthlyRate * Math.pow(1+monthlyRate, months)) / (Math.pow(1+monthlyRate, months) - 1));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.printf("Monthly EMI: %.2f%n", emiAmount); 
        System.out.printf("Total Payment over %d years: %.2f%n", tenure, (emiAmount * months)); 
        System.out.printf("Total Interest Paid: %.2f%n", (emiAmount * months - loanAmount));
    }

    void loanPayment(double amount){
        if(amount<=0){
            System.out.println("Invalid payment amount. Please provide a positive value.");
            return;
        }
        if(amount>loanAmount){
            System.out.println("Payment exceeds loan amount. Please provide a valid amount.");
            return;
        }

        if(emiAmount>amount) {
            System.out.println("Payment amount is less than EMI. Please pay at least the EMI amount: " + emiAmount);
            return;
        }

        loanAmount-=amount;
        System.out.println("Payment of "+amount+" made towards loan. Remaining loan amount: "+loanAmount);
    }
}

class BankingException extends Exception{
    public BankingException(String message){
        super(message);
    }
}

class BankingNew{
    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
       // Bank bank=new Bank();

        // System.out.println("Enter Account Holder Name: ");
        // String name=sc.nextLine();

        // System.out.println("Enter Account Number: ");
        // int accNum=sc.nextInt();

        // System.out.println("Enter Initial Balance: ");
        // int balance=sc.nextInt();

        List<BOB> accounts=new ArrayList<>();

        
       // BOB b1=new BOB(name,accNum,balance);
        //b1.accountCreation();

       // bank.accountCreation();

        int choice;

        do{
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
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter account number: ");
                    int searchAccount1=sc.nextInt();
                    System.out.println("Enter amount to deposit: ");
                    double depAmount=sc.nextDouble();
                    for(BOB account : accounts){
                        if(account.accountNumber==searchAccount1){
                            account.deposit(depAmount);
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter account number: ");
                    int searchAccount2=sc.nextInt();
                    System.out.println("Enter amount to withdraw: ");
                    double withAmount=sc.nextDouble();
                    try{
                        for(BOB account : accounts){
                            if(account.accountNumber==searchAccount2){
                                account.withdraw(withAmount);
                                break;
                            }
                        }
                    }catch(BankingException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Enter account number: ");
                    int searchAccount3=sc.nextInt();

                    for(BOB account : accounts){
                        if(account.accountNumber==searchAccount3){
                            account.checkBalance();
                            break;
                        }
                    }
                    break;
                case 4:
                     System.out.println("Enter account number: ");
                    int searchAccount4=sc.nextInt();

                    for(BOB account : accounts){
                        if(account.accountNumber==searchAccount4){
                            account.checkInterestRate();
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.println("Enter account number: ");
                    int searchAccount5=sc.nextInt();
                    System.out.println("Enter loan amount: ");
                    int loanAmount=sc.nextInt();
                    System.out.println("Enter tenure (in years): ");
                    int tenure=sc.nextInt();

                    for(BOB account : accounts){
                        if(account.accountNumber==searchAccount5){
                            account.loan(loanAmount,tenure);
                            break;
                        }
                    }
                    break;
                case 6:
                    System.out.println("Enter account number: ");
                    int searchAccount9=sc.nextInt();
                        for(BOB account : accounts){
                            if(account.accountNumber==searchAccount9){
                                account.caluEMI();
                                break;
                            }
                        }
                    break;
                case 7:
                    System.out.println("Enter account number: ");
                    int searchAccount6=sc.nextInt(); 
                    System.out.println("Enter payment amount towards loan: ");
                    double paymentAmount=sc.nextDouble();
                     for(BOB account : accounts){
                        if(account.accountNumber==searchAccount6){
                            account.loanPayment(paymentAmount);
                            break;
                        }
                    }
                    break;

                case 8:
                    System.out.println("Enter account number: ");
                    int searchAccount7=sc.nextInt(); 
                     for(BOB account : accounts){
                        if(account.accountNumber==searchAccount7){
                            account.display();
                            break;
                        }
                    }
                    break;

                case 9:
                   sc.nextLine();
                   System.out.println("sc:" + sc);
                    System.out.println("Enter Account Holder Name: ");
                    String newName=sc.nextLine();
                    System.out.println("Enter Account Number: ");
                    int newAccNum=sc.nextInt();
                    System.out.println("Enter Initial Balance: ");
                    int newBalance=sc.nextInt();
                    BOB newAccount=new BOB(newName,newAccNum,newBalance);
                    newAccount.accountCreation();
                    accounts.add(newAccount);
                    break;

                case 0:
                    System.out.println("Thank you for using Bank of Baroda.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }while(choice!=0);

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