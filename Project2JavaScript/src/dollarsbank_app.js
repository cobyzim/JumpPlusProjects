//import CustomerAccount from './customer_account.js';
//import ApplicationView from './application_view.js';

var CustomerAccount = require("./customer_account.js");
var ApplicationView = require("./application_view.js");
const prompt = require("prompt-sync")({sigint: true});

let cust1 = new CustomerAccount("Buddy Guy", "123 Street", 1234567890, 1234, "P@$$w0rd", 5000.00, 5000.00, [5000.00], ["Initial Deposit"]);
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

        let validName = false;
        let custName = "";
        do {
            custName = prompt("Customer Name: ");
            validName = validateName(custName);
            if (!validName) {
                console.log("\nPlease enter a valid name (i.e. 'John Doe')");
            }
        } while(!validName);
    }


  }
  while(!isValidMenuChoice);
}
while(true);

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

