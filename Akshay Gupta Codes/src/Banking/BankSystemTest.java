package Banking;
import java.util.*;

class Transaction {
    private Date date;
    private double amount;

    public Transaction(double amount) {
        this.date = new Date();
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private String accountNumber;
    private double balance;
    private Map<String, Transaction> transactions;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new HashMap<>();
    }

    public void deposit(double amount) {
        balance += amount;
        Transaction transaction = new Transaction(amount);
        transactions.put(UUID.randomUUID().toString(), transaction);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            Transaction transaction = new Transaction(-amount);
            transactions.put(UUID.randomUUID().toString(), transaction);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions(Date startDate, Date endDate) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getDate().after(startDate) && transaction.getDate().before(endDate)) {
                result.add(transaction);
            }
        }
        return result;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class User {
    private String name;
    private Map<String, Account> accounts;

    public User(String name) {
        this.name = name;
        this.accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    public double getAccountBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        } else {
            System.out.println("Account not found");
            return 0.0;
        }
    }

    public List<Transaction> getAccountTransactions(String accountNumber, Date startDate, Date endDate) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getTransactions(startDate, endDate);
        } else {
            System.out.println("Account not found");
            return new ArrayList<>();
        }
    }
}
