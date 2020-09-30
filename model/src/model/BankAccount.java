package model;

import java.time.LocalDateTime;
import java.util.*;

import util.AccountType;

public class BankAccount {

	private User owner;
	private float balance;
	private String accountNumber;
	private ArrayList<AccountOperations> accountOperations;
	public AccountType accountType;
	private float interestRate ;
	
	public BankAccount() {
		super();
	}
	public BankAccount(User owner) {
		super();
	}
		
	public ArrayList<AccountOperations> getAccountOperations() {
		return accountOperations;
	}
	public void setAccountOperations(ArrayList<AccountOperations> accountOperations) {
		this.accountOperations = accountOperations;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public User getOwner() {
		return this.owner;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	
	public ArrayList<AccountOperations> getOperationsAfter(LocalDateTime transactionDateTime) {
		ArrayList<AccountOperations> result = new ArrayList<AccountOperations>();
		
		for(AccountOperations accountOperation : this.accountOperations) {
			if (accountOperation.getTransactionDateTime().compareTo(transactionDateTime) >= 0) {
				result.add(accountOperation);
				}
			}
		return result;
		}
	}

