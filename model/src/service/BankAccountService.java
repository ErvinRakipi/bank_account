package service;

import model.BankAccount;
import model.BankBranch;
import model.User;
import repository.BankAccountRepository;

public class BankAccountService {
	
	BankAccountRepository bankAccountRepository = new BankAccountRepository();
	
	//BankAccountService.assignBankAccountToUser(user);
	// delete BankAccount
	// update BankAccount
	
	/*public void addBankAccount(BankAccount bankAccount, BankBranch bankBranch) {
		
		bankBranch.getBankAccounts().put(bankAccount.getAccountNumber(), bankAccount);
		}*/
	
	public BankBranch getBankBranchById(int Id) {
		return bankAccountRepository.getBankBranchById(Id);
	}
	
	public BankAccount getBankAccount(User user) {
		return bankAccountRepository.getBankAccount(user);
	}
	
}
