package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.BankBranch;
import model.BankAccount;
import model.User;
import service.UserService;

public class BankerMenu {

	UserService userService = new UserService();
	
public void bankerMenu(User user, BankBranch bankBranch) {
		
		int option = 1;
		Scanner select = new Scanner(System.in);
		
			System.out.println("\nWhat would you like to do? ");
			System.out.println("\n1. Open a new Client BankAccount");
			System.out.println("2. Close a Client BankAccount");
			System.out.println("3. Deposit");
			System.out.println("4. Withdraw");
			System.out.println("5. Account balance"); 
			System.out.println("6. Transaction History"); 
			System.out.println("7. Go back to Start Menu"); 
	        System.out.print("\nSelect an option: 1-7: ");
	        
	        while(option !=8) {
		        try {
	        option = select.nextInt();
	        
	        switch(option) {
            case 1 : int whoMakesTheRegistration = 2;
            		 new RegisterMenu().registerMenu(bankBranch,whoMakesTheRegistration);
			 		 break;
            case 2 : int whoMakesTheDelete = 2;
   		 			 new DeleteUserMenu().deleteUserMenu(user,bankBranch,whoMakesTheDelete);
					 break;
            case 3 : depositAmount();
                     break;
            case 4 : withdrawAmount();
            		 break;
            case 5 : userService.getUserBalance(user);
   		 			 bankerMenu(user, bankBranch);
            		 break;
            case 6 : new BankAccount().getOperationsAfter(null); // For example in the last 30 days
   		 			 break;
            case 7 : new StartMenu().startMenu();
            		 break;
            default: System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-8 \n");
            bankerMenu(user, bankBranch);
	        } 	
		} catch (InputMismatchException e) {
	 		System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-8 \n");
	 		bankerMenu(user, bankBranch);
	 		break;
	 	  } 
		}
			select.close();
		}
	public void createAccount() {
		System.out.println("\ncreateAccount()\n");
	}
	public void accountBalance() {
		System.out.println("\naccountBalance()\n");
	}
	public void depositAmount() {
		System.out.println("\ndepositAmount()\n");
	}
	public void withdrawAmount() {
		System.out.println("\nwithdrawAmount()\n");
	}
	public void closeAccount() {
		System.out.println("\ncloseAccount()\n");
	}
}
