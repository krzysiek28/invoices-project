package web.mvc.domain;

public class BankAccount {

    private String bankAccount;
    private Usery user;

    BankAccount(){}

    public BankAccount(String bankAccount, Usery user) {
        this.bankAccount = bankAccount;
        this.user = user;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Usery getUser() {
        return user;
    }

    public void setUser(Usery user) {
        this.user = user;
    }
}
