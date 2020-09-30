package view;

import model.BankBranch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
	

	public void mainMenu( BankBranch bankBranch) {
		
		
		int suboption = 1;
		Scanner subselect = new Scanner(System.in);
		
			System.out.println("\nWhat would you like to do? ");
			System.out.println("\n1. Login");
			System.out.println("2. Register");
			System.out.println("3. Start Menu");      
	        System.out.print("\nSelect an option: 1-3: ");
	        
	        while(suboption!=3){
	        try {
	        suboption= subselect.nextInt();
	        
	        switch(suboption) {
            case 1 : new LoginMenu().loginMenu(bankBranch);
                     break;
            case 2 : int whoMakesTheRegistration = 3;
            		 new RegisterMenu().registerMenu(bankBranch,whoMakesTheRegistration);
					 break;
            case 3 : new StartMenu().startMenu();
            		 break;
            default: System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-3 \n");
                     mainMenu(bankBranch);
	        } 
	        } catch (InputMismatchException e) {
	    		System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-3 \n");
	    		mainMenu(bankBranch);
	    		break;
	    	}
		} 
			subselect.close();
	}
}
