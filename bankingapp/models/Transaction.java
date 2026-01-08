package bankingapp.src.models;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    private String tranctionID;
    private String type;
    private double amount;
    private Date date;
    private String descrption;

    public Transaction(String type, double amount, String descrption) {
        this.tranctionID = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.date = new Date();
        this.descrption = descrption;
    }

    public void display() {
        System.out.println("-----------------------------");
        System.out.println("Transaction ID  : " + tranctionID);
        System.out.println("Type : " + type);
        System.out.println("Amount  : " + amount);
        System.out.println("Date : " + date);
        System.out.println("Account Number : " + descrption);

    }


}
