package service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import exceptions.CustomException;
import model.BankBranch;
import model.User;
import repository.BankAccountRepository;
import repository.UserRepository;
import util.AccountType;
import util.ErrorMessage;
import util.OperationType;


public class UserService {
	UserRepository userRepository = new UserRepository();
	BankAccountRepository bankAccountRepository = new BankAccountRepository();
	
	public void addUser(User user, BankBranch bankBranch) {
		if (userRepository.userExists(user)) {
			throw new CustomException(ErrorMessage.USER_EXISTS.getErrorMessage());
		} else {
			
               userRepository.addUser(user, bankBranch);
              // bankAccountRepository.addBankAccount(user,bankAccount);
		}

	}

	public void deleteUser(User user) { 
			
		if (userRepository.userExists(user)) {
			userRepository.deleteUser(user);
			// bankAccountRepository.deleteBankAccount(user,bankAccount);
		} else{
					throw new CustomException(ErrorMessage.USER_DOES_NOT_EXISTS.getErrorMessage());	
				}
			} 
	public void updateUser(User user) { 
		
		if (userRepository.userExists(user)) {
			userRepository.updateUser(user);
		} else{
					throw new CustomException(ErrorMessage.USER_DOES_NOT_EXISTS.getErrorMessage());	
				}
			} 
	
	public User getUserByPersonalId(User user){
		
		if (userRepository.userExists(user)) {
		
		User searchedUser = userRepository.getUserByPersonalId(user.getPersonalId());
		
		return searchedUser;
	}else {
		throw new CustomException(ErrorMessage.WRONG_PERSONAL_ID.getErrorMessage());
		}
	}
	
	public float getUserBalance(User user) {
		
		return userRepository.getUserBalance(user);
		
	}
	
	public void deposit(User user, float amount) {
		
		if  (amount<= 0) {
			throw new CustomException(ErrorMessage.WRONG_AMOUNT.getErrorMessage());
		}else {
		float balance = userRepository.getUserBalance(user);
		if (bankAccountRepository.getBankAccount(user).getAccountType().getName() == AccountType.STANDARDBANKACCOUNT.getName()) {
		balance = balance + amount;
		} else {
		balance = balance + (amount*(bankAccountRepository.getBankAccount(user).getInterestRate()));
		}
		userRepository.updateUserBalance(user, balance);
		
		LocalDateTime dateTime = LocalDateTime.now();
		String transactionDateTime = dateTime.format(DateTimeFormatter.ofPattern("d-MMM-uuuu HH-mm-ss"));
		OperationType operationType= OperationType.DEPOSIT;
		userRepository.addAccountOperations(amount,operationType, transactionDateTime);
		}
	}
public void withdraw(User user, float amount) {
		
		if  (amount<= 0) {
			throw new CustomException(ErrorMessage.WRONG_AMOUNT.getErrorMessage());
		}else {
		float balance = userRepository.getUserBalance(user);
		if (balance<amount) {
			throw new CustomException(ErrorMessage.INSUFFICIENT_BALANCE.getErrorMessage());
		}else {
		balance = balance - amount;
		userRepository.updateUserBalance(user, balance);
			}
		}
	}

	public User authenticateUser(User user) {
		
		if (userRepository.userExistByUsernamePassword(user)) {
		userRepository.getUserByUsernameAndPassword(user);
		return user;
		}else {
			throw new CustomException(ErrorMessage.WRONG_USERNAME_OR_PASSWORD.getErrorMessage());
		}
	        	 
	}

		   public void displayAllUsersList(){
			   List<User> users = userRepository.getAllUsers();
			   
			   for(int i=0;i<users.size();i++){
				  int 	j=i+1;
				    System.out.println(j+". "+users.get(i).getName()+" "+users.get(i).getSurname()+" "+users.get(i).getRole());
				}
          		
		   }
		   
		   public void displayBranchUsersList(User user){
			   BankBranch userBankBranch = bankAccountRepository.getBankBranchByUser(user);
			   List<User> users = userRepository.getAllUsersBankBranch(userBankBranch);
			   
			   for(int i=0;i<users.size();i++){
				  int 	j=i+1;
				    System.out.println(j+". "+users.get(i).getName()+" "+users.get(i).getSurname()+" "+users.get(i).getRole());
				}
          		
		   }
		
		   public void displayBranchEmployeesList(User user){
			   BankBranch userBankBranch = bankAccountRepository.getBankBranchByUser(user);
			   List<User> users = userRepository.getAllEmployeesBankBranch(userBankBranch);
			   
			   for(int i=0;i<users.size();i++){
				  int 	j=i+1;
				    System.out.println(j+". "+users.get(i).getName()+" "+users.get(i).getSurname()+" "+users.get(i).getRole());
				}
          		
		   } 
		   
		   public void displayBranchClientsList(User user){
			   BankBranch userBankBranch = bankAccountRepository.getBankBranchByUser(user);
			   List<User> users = userRepository.getAllClientsBankBranch(userBankBranch);
			   
			   for(int i=0;i<users.size();i++){
				  int 	j=i+1;
				    System.out.println(j+". "+users.get(i).getName()+" "+users.get(i).getSurname());
				}
          		
		   }
}

