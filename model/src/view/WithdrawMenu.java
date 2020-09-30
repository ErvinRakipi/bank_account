package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.CustomException;
import model.BankBranch;
import model.User;
import service.UserService;

public class WithdrawMenu {

UserService userService = new UserService();
	
	public WithdrawMenu() {
		super();
	}
	
public void withdrawMenu(User user,BankBranch bankBranch) {
		
		Scanner keyboard = new Scanner (System.in);

	    System.out.println("\nEnter the Amount you want to Withdraw : ");
	    System.out.println("\nPress any letter to Go Back to the Menu ");
	    
	    try {
	    	float amount=keyboard.nextFloat();
	    	
	    	userService.withdraw(user, amount);	
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
