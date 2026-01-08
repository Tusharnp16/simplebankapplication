# 🏦 Banking Application (Java, OOP, Multithreading)

A feature-rich **Banking Management System** built in **Java** using:

- Object-Oriented Programming  
- Custom Exceptions  
- Multithreading with ExecutorService  
- Transaction operations (Deposit, Withdraw, Loan, EMI, Payments)  
- Dynamic Account Number Generator  
- Colored CLI Output + UTF Icons  
- Modular Packages (models, exceptions, interfaces, app)

This project simulates a real-world mini-banking system with a clean, menu-driven console UI.

---

## 🚀 Features

### ✔️ **Account Management**
- Create a new account  
- Auto-generated account number  
- Minimum balance validation  
- IFSC generator for branch  

### 💰 **Transactions**
- Deposit  
- Withdraw  
- Check balance  
- View interest rate  

### 💵 **Loan Handling**
- Request loan  
- Auto-calculate EMI  
- Make loan payments  
- Loan summary & validation  

### 🎨 **Enhanced CLI**
- UTF Icons  
- ANSI-based colored output  
- Clean menu-based interface  

### 🔧 **Technical Features**
- Thread-safe operations using `synchronized`  
- Multithreading using `ExecutorService`  
- Custom `BankingException`  
- Clean architecture (Bank → BOB → Operations)  

---

## 📁 Project Structure

bankingapp/
│
├── src/
│ ├── app/
│ │ └── BankingNew.java
│ ├── models/
│ │ ├── Bank.java
│ │ └── BOB.java
│ ├── exceptions/
│ │ └── BankingException.java
│ └── interfaces/
│ └── BankingOperations.java
│
└── README.md


---

## 🏗 How It Works

### 🧮 1. **Main Menu (with icons + colors)**  
User selects operations such as:

1.Deposit
2.Withdraw
3.Check Balance
4.Check Interest Rate
5.Request Loan
6.Calculate EMI
7.Make Loan Payment
8.Display Account Details
9.Create Account
10.Transction History
0Exit

Clone the Repository
git clone https://github.com/your-username/simplebankapplication.git

🤝 Contributing
Contributions are welcome!
If you find bugs, feel free to open an issue or submit a pull request.

📜 License
This project is licensed under the MIT License.

👨‍💻 Author
Tushar Parmar (Java Developer)x
