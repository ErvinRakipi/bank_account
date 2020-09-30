package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import exceptions.CustomException;
import model.BankBranch;
import model.User;
import service.UserService;


public class DepositMenu {

	UserService userService = new UserService();
	
	public DepositMenu() {
		super();
	}
	
public void depositMenu(User user,BankBranch bankBranch) {
		
		Scanner keyboard = new Scanner (System.in);

	    System.out.println("\nEnter the Amount you want to Deposit : ");
	    System.out.println("\nPress any letter to Go Back to the Menu ");
	    
	    try {
	    	float amount=keyboard.nextFloat();
	    	userService.deposit(user, amount);
	    	
	    	System.out.println("Your Balance is "+userService.getUserBalance(user)+" $");
	    	new ClientMenu().clientMenu(user, bankBranch);
	    	
	     } catch( CustomException exception ){
	    	 
	    	 System.out.println(exception.getMessage());
	    	 new ClientMenu().clientMenu(user, bankBranch);
	     }catch (InputMismatchException e) {
		 		System.out.println("\nInvalid option. Please enter a number");
		 		new ClientMenu().clientMenu(user, bankBranch);
		 	  } 
	    keyboard.close(); 
	} 

}
