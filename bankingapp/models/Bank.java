package bankingapp.src.models;

public class Bank extends IntialBank {
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
