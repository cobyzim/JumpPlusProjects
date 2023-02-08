package com.dollarsbank.model;

public class SavingsAccount {
    private int custUserId;
    private String custPassword;
    private float custBalance;

    public SavingsAccount() {

    }

    public SavingsAccount(int custUserId, String custPassword, float custBalance) {
        this.custUserId = custUserId;
        this.custPassword = custPassword;
        this.custBalance = custBalance;
    }

    public int getCustUserId() {
        return custUserId;
    }
    public void setCustUserId(int custUserId) {
        this.custUserId = custUserId;
    }
    public String getCustPassword() {
        return custPassword;
    }
    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }
    public float getCustBalance() {
        return custBalance;
    }
    public void setCustBalance(float custBalance) {
        this.custBalance = custBalance;
    }

    @Override
    public String toString() {
        return "SavingsAccount [custUserId=" + custUserId + ", custPassword=" + custPassword
                 + ", custBalance=" + custBalance + "]";
    }

}
