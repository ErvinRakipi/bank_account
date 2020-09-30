package view;

import java.util.Scanner;

import exceptions.CustomException;
import model.BankBranch;
import model.User;
import service.UserService;

public class ChangeAccountCredencials {
	
	UserService userService = new UserService();
	
	public ChangeAccountCredencials() {
		super();
	}

	public void changeAccountCredencials(User user,BankBranch bankBranch) {
		
		Scanner keyboard = new Scanner(System.in);
		 
		 System.out.println("1.Enter New Username");
		 user.setUserName(keyboard.nextLine());;
	     System.out.println("2.Enter New Password");
	     user.setPassword(keyboard.nextLine());
	     
	     try {
	    	 userService.updateUser(user);
	    	 System.out.println("\nThe User's Username and Password has been successfully updated.\n");
	    	 new ClientMenu().clientMenu(user, bankBranch);
	    	
	     } catch( CustomException exception ){
	    	 
	    	 System.out.println(exception.getMessage());
	    	new ClientMenu().clientMenu(user, bankBranch);
	     }
	     keyboard.close(); 
	}
}
