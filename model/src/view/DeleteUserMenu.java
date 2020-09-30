package view;

import java.util.Scanner;

import exceptions.CustomException;
import model.BankBranch;
import model.User;
import service.UserService;

public class DeleteUserMenu {

	UserService userService = new UserService();
	
	 public DeleteUserMenu() {
		super();
	}
	 
	 public void deleteUserMenu(User user,BankBranch bankBranch, int whoMakesTheDelete) {
		 
		 
		 Scanner keyboard = new Scanner(System.in);
		 
		 User userToDelete = new User();
		 
		 System.out.println("1.Enter User's to Delete Name");
		 userToDelete.setName(keyboard.nextLine());
	     System.out.println("2.Enter User's to Delete Surname");
	     userToDelete.setSurname(keyboard.nextLine());
	     System.out.println("3.Enter User's to Delete Personal Identification Number");
	     userToDelete.setPersonalId(keyboard.nextLine());
	     
	     try {
	    	 userService.deleteUser(userToDelete);
	    	 System.out.println("\nThe User "+userToDelete.getName()+" "+userToDelete.getSurname()+" has been successfully removed.\n");
	    	 
	    	 if (whoMakesTheDelete == 1) {
	    		 new ManagerMenu().managerMenu(user,bankBranch);
	    		} else if (whoMakesTheDelete == 2){
	    			new BankerMenu().bankerMenu(user,bankBranch);
				}else {
					new MainMenu().mainMenu(bankBranch);
				}
	     } catch( CustomException exception ){
	    	 
	    	 System.out.println(exception.getMessage());
	    	 if (whoMakesTheDelete == 1) {
	    		 new ManagerMenu().managerMenu(user,bankBranch);
	    		} else if (whoMakesTheDelete == 2){
	    			new BankerMenu().bankerMenu(user,bankBranch);
				}else {
					new MainMenu().mainMenu(bankBranch);
				}
	     }
	     keyboard.close(); 
	 } 
}
