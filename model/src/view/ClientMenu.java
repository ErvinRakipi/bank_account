package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.BankBranch;
import model.BankAccount;
import model.User;
import service.UserService;

public class ClientMenu {
	
	UserService userService = new UserService();

	public void clientMenu(User user, BankBranch bankBranch) {
		
		int option = 1;
		Scanner select = new Scanner(System.in);
		
			System.out.println("\nWhat would you like to do? ");
			System.out.println("\n1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. Account balance"); 
			System.out.println("4. Transaction History");
			System.out.println("5. Change your Credencials");
			System.out.println("6. Close an account");
			System.out.println("7. Go back to Start Menu");  
	        System.out.print("\nSelect an option: 1-7: ");
	        
	        while(option !=7) {
		        try {
	        option = select.nextInt();
	        
	        switch(option) {
            
            case 1 : new DepositMenu().depositMenu(user,bankBranch);
                     break;
            case 2 : new WithdrawMenu().withdrawMenu(user,bankBranch);
            		 break;
            case 3 : System.out.println("Your Balance is "+userService.getUserBalance(user)+" $");
            		 clientMenu(user, bankBranch);
            		 break;
            case 4 : new BankAccount().getOperationsAfter(null); // For example in the last 30 days
	 			 	 break;
            case 5 : new ChangeAccountCredencials().changeAccountCredencials(user,bankBranch);
		 	 		 break;
            case 6 : int whoMakesTheDelete = 3;
   		 			 new DeleteUserMenu().deleteUserMenu(user,bankBranch, whoMakesTheDelete);
			 		 break;
            case 7 : new StartMenu().startMenu();
   		 			 break;
            default: System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-7 \n");
            clientMenu(user, bankBranch);
	        } 	
		} catch (InputMismatchException e) {
	 		System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-7 \n");
	 		clientMenu(user, bankBranch);
	 		break;
	 	  } 
		}
			select.close();
		}
	
	public void depositAmount() {
		System.out.println("\ndepositAmount()\n");
	}
	public void withdrawAmount() {
		System.out.println("\nwithdrawAmount()\n");
	}
}
