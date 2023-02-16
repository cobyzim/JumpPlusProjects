//import CustomerAccount from "./customer_account.js";

module.exports = class ApplicationView {
    static welcome() {
        console.log("DOLLARSBANK ATM Welcomes You!!\n");
        //console.log("Enter a valid choice (1 -> Transaction, 2 -> Create New Account)");
    }

    static createAccountPrinter() {
        console.log("\n------------------------------");
        console.log("Enter Details For New Account");
        console.log("------------------------------");
    }


    
}

