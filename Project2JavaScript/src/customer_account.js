module.exports = class CustomerAccount {
    constructor(custName, custAddress, custPhone, custPassword, custInitialDeposit, custBalance, transactions = [], transactionTypes = []) {
        this.custName = custName;
        this.custAddress = custAddress;
        this.custPhone = custPhone;
        this.custPassword = custPassword;
        this.custInitialDeposit = custInitialDeposit;
        this.custBalance = custBalance;
        this.transactions = transactions;
        this.transactionTypes = transactionTypes;
    }

    get getCustName() {
        return this.custName;
    }
    set setCustName(custName) {
        // if (custNameRegex.test(name) !== true) {
        //     // TODO: Do some kind of error handling like 'throw 'the name not valid''
        //     console.log("Name not valid!");
        // }
        this.custName = custName;
    }

    get getCustAddress() {
        return this.custAddress;
    }
    set setCustAddress(custAddress) {
        this.custAddress = custAddress;
    }

    get getCustPhone() {
        return this.custPhone;
    }
    set setCustPhone(custPhone) {
        this.custPhone = custPhone;
    }

    get getCustPassword() {
        return this.custPassword;
    }
    set setCustPassword(custPassword) {
        this.custPassword = custPassword;
    }

    get getCustInitialDeposit() {
        return this.custInitialDeposit;
    }
    set setCustInitialDeposit(custInitialDeposit) {
        this.custInitialDeposit = custInitialDeposit;
    }

    get getCustBalance() {
        return this.custBalance;
    }
    set setCustBalance(custBalance) {
        this.custBalance = custBalance;
    }

    get getTransactions() {
        return this.transactions;
    }
    set setTransactions(transactions) {
        this.transactions = transactions;
    }

    get getTransactionTypes() {
        return this.transactionTypes;
    }
    set setTransactionTypes(transactionTypes) {
        this.transactionTypes = transactionTypes;
    }

}

// const element = <h1>Hello World</h1>;
// ReactDOM.render(element, document.getElementById('root'));



// const lib = require("./customer_account.js") -> how to import class