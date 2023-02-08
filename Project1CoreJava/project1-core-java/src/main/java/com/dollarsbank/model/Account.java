package com.dollarsbank.model;

public class Account {
    private String custName;
    private String custAddress;
    private int custPhoneNumber;
    private int custUserId;
    private String custPassword;
    private float custInitialDepositAmount;
    private float custBalance;

    public Account() {

    }

    public Account(String custName, String custAddress, int custPhoneNumber, int custUserId, String custPassword,
            float custInitialDepositAmount, float custBalance) {
        this.custName = custName;
        this.custAddress = custAddress;
        this.custPhoneNumber = custPhoneNumber;
        this.custUserId = custUserId;
        this.custPassword = custPassword;
        this.custInitialDepositAmount = custInitialDepositAmount;
        this.custBalance = custBalance;
    }

    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustAddress() {
        return custAddress;
    }
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
    public int getCustPhoneNumber() {
        return custPhoneNumber;
    }
    public void setCustPhoneNumber(int custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
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
    public float getCustInitialDepositAmount() {
        return custInitialDepositAmount;
    }
    public void setCustInitialDepositAmount(float custInitialDepositAmount) {
        this.custInitialDepositAmount = custInitialDepositAmount;
    }
    public float getCustBalance() {
        return custBalance;
    }
    public void setCustBalance(float custBalance) {
        this.custBalance = custBalance;
    }

    @Override
    public String toString() {
        return "Account [custName=" + custName + ", custAddress=" + custAddress + ", custPhoneNumber=" + custPhoneNumber
                + ", custUserId=" + custUserId + ", custPassword=" + custPassword + ", custInitialDepositAmount="
                + custInitialDepositAmount + "]";
    }

    
    
}
