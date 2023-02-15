const custNameRegex = new RegExp('^[A-Z]\\w+\\s[A-Z]\\w+');

module.exports = class CustomerAccount {
    constructor(custName, custAddress, custPhone, custPin, custPassword, custInitialDeposit, custBalance, transactions = [], transactionTypes = []) {
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
}

// const element = <h1>Hello World</h1>;
// ReactDOM.render(element, document.getElementById('root'));



// const lib = require("./customer_account.js") how to import