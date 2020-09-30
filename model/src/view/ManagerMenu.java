package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.BankBranch;
import model.User;
import service.UserService;


public class ManagerMenu {
	
	UserService userService = new UserService();
	
public void managerMenu(User user, BankBranch bankBranch) {
		
		int option = 1;
		Scanner select = new Scanner(System.in);
		

			System.out.println("\nWhat would you like to do? ");
			System.out.println("\n1. Show Branch Users List ");
			System.out.println("2. Show Branch Employees List");
			System.out.println("3. Show Branch Clients List");
			System.out.println("4. Create Interest Norms"); 
			System.out.println("5. Register a new Banker"); 
			System.out.println("6. Remove a Banker");
			System.out.println("7. Show All Users List");
			System.out.println("8. Go back to Start Menu"); 
	        System.out.print("\nSelect an option: 1-8: ");
	        
	        while(option !=8) {
	        try {
	        option = select.nextInt();
	        
	        switch(option) {
            case 1 : userService.displayBranchUsersList(user);
            		 managerMenu(user, bankBranch);
            		 break;
            case 2 : userService.displayBranchEmployeesList(user);
   		 			 managerMenu(user, bankBranch);
					 break;
            case 3 : userService.displayBranchClientsList(user);
	 			     managerMenu(user, bankBranch);
			         break;
            case 4 : createInterestNorms();
            		 managerMenu(user, bankBranch);
                     break;
            case 5 : int whoMakesTheRegistration = 1;
            		 new RegisterMenu().registerMenu(bankBranch,whoMakesTheRegistration);
   		 			 break;
            case 6 : int whoMakesTheDelete = 1;
            		 new DeleteUserMenu().deleteUserMenu(user,bankBranch, whoMakesTheDelete);
            		 break;
            case 7 : userService.displayAllUsersList();
   		 			 managerMenu(user, bankBranch);
   		 			 break;
            case 8 : new StartMenu().startMenu();
            		 break;
            default: System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-8 \n");
            managerMenu(user, bankBranch);
	        } 	
		} catch (InputMismatchException e) {
	 		System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-8 \n");
	 		managerMenu(user, bankBranch);
	 		break;
	 	  } 
		}
	        select.close();
		}

	public void createInterestNorms() {
		System.out.println("\nCreate Interest Norms\n");
	}
	
}
