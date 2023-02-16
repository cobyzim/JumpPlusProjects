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

    static loginPrinter() {
        console.log("\n--------------------");
        console.log("Enter Login Details");
        console.log("--------------------");
    }

    static loginWelcomePrinter() {
        console.log("\n--------------------")
        console.log("WELCOME Customer!!!");
        console.log("--------------------");
        console.log("1. Deposit Amount");
        console.log("2. Withdraw Amount");
        console.log("3. Funds Transfer");
        console.log("4. View 5 Recent Transactions");
        console.log("5. Display Customer Information");
        console.log("6. Sign Out\n");
        console.log("Enter Choice (1, 2, 3, 4, 5, or 6): ");
    }


    
}

