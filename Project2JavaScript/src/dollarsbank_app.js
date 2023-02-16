// Imports
var CustomerAccount = require("./customer_account.js");
var ApplicationView = require("./application_view.js");
const prompt = require("prompt-sync")({sigint: true});

// Hard code a customer object and add it to empty account list
let cust1 = new CustomerAccount("Buddy Guy", "123 Street", 1234567890, 1, "P@$$w0rd", 5000.00, 5000.00, [5000.00], ["Initial Deposit"]);
let accounts  = [];
accounts.push(cust1);

let isValidMenuChoice = false;
let isValidLoginChoice = false;

let currentAccountId = 0; // Track logged in user

// start at welcome page
do {
  ApplicationView.welcome();

  let userMenuChoice = 0;
  do {
    userMenuChoice = getUserMenuChoice();
    if (userMenuChoice === 1) {
        // Go through New Account Creation
        ApplicationView.createAccountPrinter();

        // Get Valid Customer Name
        let validName = false;
        let custName = "";
        do {
            custName = prompt("Customer Name: ");
            validName = validateName(custName);
            if (!validName) {
                console.log("\nPlease enter a valid name (i.e. 'John Doe')");
            }
        } while(!validName);


        // Get Valid Customer Address
        let validAddress = false;
        let custAddress = "";
        do {
            custAddress = prompt("Customer Address: ");
            validAddress = validateAddress(custAddress);
            if (!validAddress) {
                console.log("\nPlease enter a valid address (i.e. '123 Street')");
            }
        } while(!validAddress);


        // Get Valid Customer Phone Number
        let validPhoneNumber = false;
        let custPhone = 0;
        do {
            try {
                custPhone = parseInt(prompt("Customer Contact Number: "));
                if (isNaN(custPhone)) {
                    throw "\nPlease enter a valid ten digit phone number (i.e. 1234567890)";
                }
                validPhoneNumber = validatePhoneNumber(custPhone);
                if (!validPhoneNumber) {
                    console.log("\nPlease enter a valid ten digit phone number (i.e. 1234567890)");
                }
            }
            catch(err) {
                console.log(err);
            }
        } while(!validPhoneNumber);


        // Get Valid Customer User Id
        let validIdFormat = false;
        let isTaken = false;
        let custId = 0;

        do {
            try {
                custId = parseInt(prompt("User Id: "));
                if (isNaN(custId)) {
                    throw "\nPlease enter a valid user id greater than 0 (i.e. 15)";
                }
                validIdFormat = validateUserId(custId);
                if (!validIdFormat) {
                    console.log("\nPlease enter a valid user id greater than 0 (i.e. 15)");
                }

                // Check if user id is already taken
                isTaken = false;
                for (let i = 0; i < accounts.length; i++) {
                    let accountId = accounts[i].userId;

                    if (accountId == custId) {
                        isTaken = true; // User id has already been taken
                    }
                }
                if (isTaken) {
                    console.log("\nPlease choose another user id, " + custId + " is already taken!");
                }
            }
            catch(err) {
                console.log(err);
            }
        } while(!validIdFormat || isTaken);


        // Get Valid Customer Password
        let validPassword = false;
        let password = "";

        do {
            password = prompt("Password - 8 Characters with Lower, Upper, Number & Special: ");
            validPassword = validatePassword(password);
            if (!validPassword) {
                console.log("\nPlease enter a password that has 8 characters with lower, upper, number, and special");
            }
        } while(!validPassword);


        // Get Valid Initial Deposit
        let validDepositInput = false;
        let initialDeposit = 0.0;

        do {
            try {
                initialDeposit = parseFloat(prompt("Initial Deposit Amount: "));
                if (isNaN(initialDeposit)) {
                    throw "\nPlease enter a valid positive deposit amount"
                }
                validDepositInput = validateDeposit(initialDeposit);
                if (!validDepositInput) {
                    console.log("\nPlease enter a valid positive deposit amount");
                }
            }
            catch(err) {
                console.log(err);
            }
        } while(!validDepositInput);

        
        // Add account with all validated user input to array
        let newAccount = new CustomerAccount(custName, custAddress, custPhone, custId, password, initialDeposit, initialDeposit, [initialDeposit], ["Initial Deposit"]);
        accounts.push(newAccount);
    }

    if (userMenuChoice ===  2) {
        login();
    }









  }
  while(!isValidMenuChoice);
}
while(true);


// Login Method
function login() {
    let accountFound = false;
    let loginId = 0;

    do {
        ApplicationView.loginPrinter();

        try {
            loginId = parseInt(prompt("User Id: "));
            if (isNaN(loginId)) {
                throw "\nPlease enter a valid user id!";
            }
        }
        catch(err) {
            console.log(err);
            login();
        }

        let loginPassword = prompt("Password: ");

        for (let i = 0; i < accounts.length; i++) {
            let userId = accounts[i].userId;
            let userPwd = accounts[i].custPassword;

            if (userId == loginId && userPwd.localeCompare(loginPassword) == 0) {
                accountFound = true;
                currentAccountId = loginId;
                break;
            }
        }

        if (accountFound) {
            loginOptions();
        }
        else {
            console.log("\nInvalid Credentials. Try Again!");
        }
        
    } while(!accountFound);
}

// Handle Login Menu
function loginOptions() {
    do {
        ApplicationView.loginWelcomePrinter();

        let userLoginChoice = 0;
        do {
            userLoginChoice = getUserLoginChoice();
            switch (userLoginChoice) {
                case 1:
                    // Make Deposit
                    makeDeposit();
                    break;
                case 2:
                    // Make Withdrawal
                    makeWithdrawal();
                    break;
                case 3:
                    // Transfer Funds
                    transferFunds();
                    break;
                case 4:
                    // View 5 Recent Transactions
                    viewRecentTransactions();
                    break;
                case 5:
                    // Display Customer Information
                    displayCustInfo();
                    break;
                case 6:
                    welcome();
                default:
                    loginOptions();
            }
        } while(!isValidLoginChoice)
    } while (true);
}


// Determine if customer wants to login or create a new account
function getUserMenuChoice() {
  let userMenuChoice = 0;
  try {
      userMenuChoice = parseInt(prompt("Enter a valid choice (1 -> Create New Account, 2 -> Login): "));
      
      if (userMenuChoice == 1 || userMenuChoice == 2) {
        isValidMenuChoice = true;
        console.log("You'be entered a valid choice\n");
      }
      else if (isNaN(userMenuChoice)) {
        throw "Please Enter a Valid Numeric Choice (1 or 2): \n";
      }
      else {
        console.log("Please Enter a Valid Choice (1 or 2): \n");
      }
  }
  catch(err) {
    console.log(err);
  }
  return userMenuChoice;
}

// Validate Cust Name
function validateName(name) {
    let custNameRegex = /^[A-Z]\w+\s[A-Z]\w+/;
    let result = custNameRegex.test(name);

    return result;
}

// Validate Cust Address
function validateAddress(address) {
    let custAddressRegex = /\d+\s+([a-zA-Z]+|[a-zA-Z]+\s[a-zA-Z]+)/;
    let result = custAddressRegex.test(address);

    return result;
}

// Validate Cust Phone
function validatePhoneNumber(number) {
    let custPhoneRegex = /[0-9]{10}/;
    let numString = "" + number;
    let result = custPhoneRegex.test(numString);

    return result;
}

// Validate User Id
function validateUserId(number) {
    let custUserIdRegex = /[1-9]+/;
    let numString = "" + number;
    let result = custUserIdRegex.test(numString);

    return result;
}

// Validate Cust Password
function validatePassword(password) {
    let custPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8}$/;
    let result = custPasswordRegex.test(password);

    return result;
}

// Validate Deposit
function validateDeposit(deposit) {
    let custDepositRegex = /^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/;
    let depositString = "" + deposit;
    let result = custDepositRegex.test(depositString);

    return result;
}




