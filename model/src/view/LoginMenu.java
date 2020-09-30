package view;

import exceptions.CustomException;
import java.util.Scanner;

import model.BankBranch;
import model.User;
import service.UserService;
import util.Role;

public class LoginMenu {
	 
	UserService userService = new UserService();
	 
	public LoginMenu() {
		super();
	}
	public void loginMenu(BankBranch bankBranch) {
		
		Scanner keyboard = new Scanner (System.in);
		User user = new User();

	    System.out.println("Enter your Username : ");
	    user.setUserName(keyboard.nextLine());
	    System.out.println("Enter your Password : ");
	    user.setPassword(keyboard.nextLine()); // gets input from user
	    
	    try {
	    	User loggedUser = userService.authenticateUser(user);
	    	
	    	System.out.println(loggedUser.getName()+" "+loggedUser.getSurname()+" "+loggedUser.getRole());
	    		
	    		if (loggedUser.getRole().equals(Role.CLIENT)) {
	    			new ClientMenu().clientMenu(loggedUser, bankBranch);
	    		} else if (loggedUser.getRole().equals(Role.BANKER)){
	    			new BankerMenu().bankerMenu(loggedUser, bankBranch);
				} else {
					new ManagerMenu().managerMenu(loggedUser, bankBranch);
				} 
	    		
	     } catch( CustomException exception ){
	    	 
	    	 System.out.println(exception.getMessage());
	    	 this.loginMenu(bankBranch);
	     }
	    keyboard.close(); 
	} 
}
