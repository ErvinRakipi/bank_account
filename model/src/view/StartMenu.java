package view;

import java.util.InputMismatchException;

import java.util.Scanner;
import model.Bank;
import service.BankAccountService;

public class StartMenu {
	BankAccountService bankAccountService = new BankAccountService();
	
	public void startMenu() {
	Bank bank = new Bank();
	bank.setName("Intesa SanPaolo Bank");
	
	for(int i = 0; i< 31; i++) {
		System.out.print("=");
	}
	System.out.println("\nWelcome to "+bank.getName());
	for(int i = 0; i< 31; i++) {
	System.out.print("=");
	}
		int option=1;
		Scanner select = new Scanner(System.in);
		
		System.out.println("\nChoose your City ");
		System.out.println("\n1. "+bankAccountService.getBankBranchById(1).getBranchName());
		System.out.println("2. "+bankAccountService.getBankBranchById(2).getBranchName());
		System.out.println("3. "+bankAccountService.getBankBranchById(3).getBranchName());
		System.out.println("4. Quit");      
		System.out.print("\nSelect an option: 1-4: ");
		
		while(option!=4){
			
     try {  
        option = select.nextInt();
        
        switch(option) {
        case 1 : new MainMenu().mainMenu(bankAccountService.getBankBranchById(1));
                 break;
        case 2 : new MainMenu().mainMenu(bankAccountService.getBankBranchById(2));
				 break;
        case 3 : new MainMenu().mainMenu(bankAccountService.getBankBranchById(3));
                 break;
        case 4 : System.out.println("\nWe were glad to see you");
                 break;
        default: System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-4 \n");
        		 startMenu();
        } 
     } catch (InputMismatchException e) {
 		System.out.println("\nInvalid option. Please enter a valid Options from the Menu: 1-4 \n");
 		startMenu();
 		break;
 	  } 
	}
		select.close();
	}
}
