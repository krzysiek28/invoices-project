package web.mvc.domain;

public class BankAccount {

    private String bankAccount;
    private User user;

    BankAccount(){}

    public BankAccount(String bankAccount, User user) {
        this.bankAccount = bankAccount;
        this.user = user;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
