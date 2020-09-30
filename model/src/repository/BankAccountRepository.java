package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.BankAccount;
import model.BankBranch;
import model.User;
import util.AccountType;
import util.ConnectionManager;

public class BankAccountRepository {
	
	private final String GET_BANK_BRANCH_BY_ID = "SELECT * FROM bank_branch where id= ?";
	private final String GET_BANK_BRANCH_ID_BY_USER_PERSONAL_ID = "SELECT users.branch_id FROM users where personal_id= ?";
	
	private final String GET_BANK_ACCOUNT_BY_USER_PERSONAL_ID = "SELECT * FROM users \n" + 
			"JOIN bank_account ON users.id = bank_account.client_id \n" + 
			"JOIN account_type ON bank_account.account_type_id = account_type.id \n" + 
			"where users.personal_id =?";
	
	
	public BankBranch getBankBranchById(int Id) {
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_BANK_BRANCH_BY_ID);) {
			preparedStatement.setInt(1, Id);
			ResultSet rs = preparedStatement.executeQuery();
			BankBranch bankBranch = new BankBranch();
			while (rs.next()) {
				bankBranch.setBranchId(rs.getInt("id"));
				bankBranch.setBranchCity(rs.getString("city"));
				bankBranch.setBranchAddress(rs.getString("address"));
				bankBranch.setBranchName(rs.getString("name"));
				
				}
			return bankBranch;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public BankBranch getBankBranchByUser(User user) {
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_BANK_BRANCH_ID_BY_USER_PERSONAL_ID);) {
			preparedStatement.setString(1, user.getPersonalId());
			ResultSet rs = preparedStatement.executeQuery();
			BankBranch bankBranch = new BankBranch();
			while (rs.next()) {
				bankBranch.setBranchId(rs.getInt("branch_id"));
				
				}
			return bankBranch;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public BankAccount getBankAccount(User user) {
		
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_BANK_ACCOUNT_BY_USER_PERSONAL_ID);) {
			preparedStatement.setString(1, user.getPersonalId());
			ResultSet rs = preparedStatement.executeQuery();
			BankAccount bankAccount = new BankAccount();
			while (rs.next()) {
				bankAccount.setBalance(rs.getFloat("balance"));;
				bankAccount.setAccountNumber(rs.getString("account_number"));
				
				int accountTypeId = rs.getInt("account_type_id");
				if (accountTypeId == AccountType.STANDARDBANKACCOUNT.getId()) {
					bankAccount.setAccountType(AccountType.STANDARDBANKACCOUNT);
				} else {
					bankAccount.setAccountType(AccountType.INTERESTBANKACCOUNT);
				}
				bankAccount.setInterestRate(rs.getFloat("interest_rate"));
				}
			return bankAccount;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
/*public void addBankAccount(BankAccount bankAccount, BankBranch bankBranch) {
	bankBranch.getBankAccounts().put(bankAccount.getAccountNumber(), bankAccount);
	}

	public boolean accountExists(BankAccount bankAccount, BankBranch bankBranch) {
		if (bankBranch.getBankAccounts().containsKey(bankAccount.getAccountNumber())){
			
			return true;
		}
		return false;
	}

	public void removeBankAccount(BankAccount bankAccount, BankBranch bankBranch) {
		bankBranch.getBankAccounts().remove(bankAccount.getAccountNumber(), bankAccount);
		}*/
}
