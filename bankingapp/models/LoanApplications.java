package bankingapp.src.models;

public class LoanApplications {

    private String string;

    private enum Status{PENDING,ACCEPT,REJECTED};

    private String accountNumber;
    private int loanAmount;
    private int tenure;
    private Status status;
    private String remark;

    public LoanApplications(String accountNumber, int loanAmount, int tenure) {
        this.accountNumber = accountNumber;
        this.loanAmount = loanAmount;
        this.tenure = tenure;
        this.status = Status.PENDING;
        this.remark = "In Review Process";
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public int getTenure() {
        return tenure;
    }

    public Status getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public void approved(){
        this.status=Status.ACCEPT;
        remark="Loan has been approved";
    }

    public void rejected(String remark){
        this.status=Status.REJECTED;
        this.remark=remark;
    }

    public void display() {
        System.out.println("Account Number  : " + accountNumber);
        System.out.println("Loan Amount : " + loanAmount);
        System.out.println("Tenure  : " + tenure);
        System.out.println("Status : " + status);
        System.out.println("Remark : " + remark);
        System.out.println("-----------------------------");

    }
}
