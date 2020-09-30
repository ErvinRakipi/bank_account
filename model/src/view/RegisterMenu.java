package view;

import exceptions.CustomException;
import model.BankBranch;
import model.User;
import service.UserService;
import util.Role;

import java.util.Scanner;

public class RegisterMenu {
	
	UserService userService = new UserService();
	
	 public RegisterMenu() {
		super();
	}
	 
	 public void registerMenu(BankBranch bankBranch, int whoMakesTheRegistration) {
		 
		 
		 Scanner keyboard = new Scanner(System.in);
		 
		 User userToRegister = new User();
		 
		 System.out.println("1.Enter the Name");
		 userToRegister.setName(keyboard.nextLine());
	     System.out.println("2.Enter the Surname");
	     userToRegister.setSurname(keyboard.nextLine());
	 	 System.out.println("3.Enter the Username");
	 	 userToRegister.setUserName(keyboard.nextLine());
	     System.out.println("4.Enter the Password");
	     userToRegister.setPassword(keyboard.nextLine());
	     System.out.println("5.Enter the Personal Identification Number");
	     userToRegister.setPersonalId(keyboard.nextLine());
	     if (whoMakesTheRegistration ==3) {
	     userToRegister.setRole(Role.CLIENT);
	     } else if(whoMakesTheRegistration ==2){
	     userToRegister.setRole(Role.BANKER);
	     }else{
	     userToRegister.setRole(Role.MANAGER); 
	     }
	     try {
	    	 userService.addUser(userToRegister, bankBranch);
	    	 //BankAccountService.assignBankAccountToUser(user);
	    	 System.out.println("\nThe User "+userToRegister.getName()+" "+userToRegister.getSurname()+" has been successfully registered.\n");
	    	 if (whoMakesTheRegistration == 1) {
	    		 new ManagerMenu().managerMenu(userToRegister,bankBranch);
	    		} else if (whoMakesTheRegistration == 2){
	    			new BankerMenu().bankerMenu(userToRegister,bankBranch);
				}else {
					new MainMenu().mainMenu(bankBranch);
				}
	     } catch( CustomException exception ){
	    	 
	    	 System.out.println(exception.getMessage());
	    	 new MainMenu().mainMenu(bankBranch);
	     }
	     keyboard.close(); 
	 } 
  }