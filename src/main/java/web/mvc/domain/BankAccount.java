package web.mvc.domain;

public class BankAccount {

    private String bankAccount;
    private String additionalData;

    BankAccount(){}

    public BankAccount(String bankAccount, String additionalData) {
        this.bankAccount = bankAccount;
        this.additionalData = additionalData;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {this.additionalData=additionalData;}
}
