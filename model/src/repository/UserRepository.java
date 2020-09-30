package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BankBranch;
import model.User;
import util.ConnectionManager;
import util.OperationType;
import util.Role;

public class UserRepository {
	
	private final String ADD_USER = "INSERT INTO users (name,surname,user_name,password,personal_id,role_id,branch_id)  VALUES (?,?,?,?,?,?,?)";
	private final String DELETE_USER = "DELETE FROM users where personal_id = ?";
	private final String UPDATE_USER = "UPDATE users SET user_name = ?, password = ? where personal_id = ?";
	private final String USER_EXISTS = "SELECT COUNT(*) FROM users where personal_id =?";
	private final String USER_EXISTS_BY_USERNAME_PASSWORD = "SELECT COUNT(*) FROM users where user_name = ? AND password =?";
	private final String GET_USER_BALANCE = "SELECT bank_account.balance FROM users JOIN bank_account ON users.id = bank_account.client_id where users.personal_id = ?";
	private final String UPDATE_USER_BALANCE = "UPDATE bank_account SET balance = ? where client_id = (SELECT users.id FROM users JOIN bank_account ON users.id = bank_account.client_id where users.personal_id = ?)";
	private final String ADD_ACCOUNT_OPERATION = "INSERT INTO operation (operation_amount,operation_type,operation_date_time)  VALUES (?,?,?)";
	private final String GET_ALL_USERS = "SELECT * FROM users";
	private final String GET_ALL_USERS_OF_THE_BANKBRANCH = "SELECT * FROM users where branch_id = ? order by role_id asc";
	private final String GET_ALL_EMPLOYEES = "SELECT * FROM users where role_id !=?)";
	private final String GET_ALL_EMPLOYEES_OF_THE_BANKBRANCH = "SELECT * FROM users where role_id !=? AND branch_id = ?";
	private final String GET_ALL_CLIENTS = "SELECT * FROM users where role_id= ?"; 
	private final String GET_ALL_CLIENTS_OF_A_BANKBRANCH = "SELECT * FROM users where role_id= ? AND branch_id = ?";
	private final String GET_USER_BY_PERSONAL_ID = "SELECT * FROM users where personal_id = ?";
	private final String GET_USER_BY_USER_NAME_AND_PASSWORD = "SELECT * FROM users where user_name = ? AND password =?";
	
	public void addUser(User user, BankBranch bankBranch) { 
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER))

		{
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getUserName());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getPersonalId());
			preparedStatement.setInt(6, user.getRole().getId());
			preparedStatement.setInt(7, bankBranch.getBranchId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void deleteUser(User user) {  //In BankAccount Repository delete BankAccount if role ==3 Client
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);) {
			preparedStatement.setString(1, user.getPersonalId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void updateUser(User user) {
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getPersonalId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public boolean userExists(User user) {
		
			boolean userExists = false;
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(USER_EXISTS)){
			preparedStatement.setString(1, user.getPersonalId());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
			if (rs.getInt(1)!=0) {
				userExists = true;
			}else {
				userExists = false;
			}
		}
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
		return userExists;
	}
	
	public boolean userExistByUsernamePassword(User user) {
		
	boolean userExists = false;
	try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(USER_EXISTS_BY_USERNAME_PASSWORD)){
		preparedStatement.setString(1, user.getUserName());
		preparedStatement.setString(2, user.getPassword());
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
		if (rs.getInt(1)!=0) {
			userExists = true;
		}else {
			userExists = false;
		}
	}
	} catch (SQLException e) {
		System.out.println("error " + e);
	}
	return userExists;
}
	public float getUserBalance(User user) {
	
	float balance=0;
	try (Connection connection = ConnectionManager.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BALANCE)){
		preparedStatement.setString(1, user.getPersonalId());
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
		balance=rs.getFloat("balance");
	}
	} catch (SQLException e) {
		System.out.println("error " + e);
	}
	return balance;
}
	public void updateUserBalance(User user, float balance) {
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BALANCE)) {
			preparedStatement.setFloat(1, balance);;
			preparedStatement.setString(2, user.getPersonalId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error " + e);
		}
	}
	
	public void addAccountOperations(float amount,OperationType operationType, String transactionDateTime) {
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_ACCOUNT_OPERATION)) {
				preparedStatement.setFloat(1, amount);
				preparedStatement.setInt(2, operationType.getId());
				preparedStatement.setString(3, transactionDateTime);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				System.out.println("error " + e);
			}
		}
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPersonalId(rs.getString("personal_id"));
				int roleId = rs.getInt("role_id");
				if (roleId == Role.CLIENT.getId()) {
					user.setRole(Role.CLIENT);
				} else if (roleId == Role.BANKER.getId()) {
					user.setRole(Role.BANKER);
				} else {
					user.setRole(Role.MANAGER);
				}
				
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public List<User> getAllUsersBankBranch(BankBranch bankBranch) {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS_OF_THE_BANKBRANCH);) {
			preparedStatement.setInt(1, bankBranch.getBranchId());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPersonalId(rs.getString("personal_id"));
				int roleId = rs.getInt("role_id");
				if (roleId == Role.CLIENT.getId()) {
					user.setRole(Role.CLIENT);
				} else if (roleId == Role.BANKER.getId()) {
					user.setRole(Role.BANKER);
				} else {
					user.setRole(Role.MANAGER);
				}
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	public List<User> getAllEmployees() {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES);) {
			preparedStatement.setInt(1, 3);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPersonalId(rs.getString("personal_id"));
				int roleId = rs.getInt("role_id");
				if (roleId == Role.BANKER.getId()) {
					user.setRole(Role.BANKER);
				} else {
					user.setRole(Role.MANAGER);
				}
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public List<User> getAllEmployeesBankBranch(BankBranch bankBranch) {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES_OF_THE_BANKBRANCH);) {
			
				preparedStatement.setInt(1, 3);
				preparedStatement.setInt(2, bankBranch.getBranchId());
				
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPersonalId(rs.getString("personal_id"));
				int roleId = rs.getInt("role_id");
				if (roleId == Role.BANKER.getId()) {
					user.setRole(Role.BANKER);
				} else {
					user.setRole(Role.MANAGER);
				}
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public List<User> getAllClients() {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLIENTS);) {
			preparedStatement.setInt(1, 3);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPersonalId(rs.getString("personal_id"));
				user.setRole(Role.CLIENT);
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public List<User> getAllClientsBankBranch(BankBranch bankBranch) {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLIENTS_OF_A_BANKBRANCH);) {
			
				preparedStatement.setInt(1, 3);
				preparedStatement.setInt(2, bankBranch.getBranchId());
				
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPersonalId(rs.getString("personal_id"));
				user.setRole(Role.CLIENT);
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}
	
	public User getUserByPersonalId(String personalId) {
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_PERSONAL_ID);) {
			preparedStatement.setString(1, personalId);
			ResultSet rs = preparedStatement.executeQuery();
			User user = new User();
			while (rs.next()) {
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPersonalId(rs.getString("personal_id"));
				
				int roleId = rs.getInt("role_id");
				if (roleId == Role.CLIENT.getId()) {
					user.setRole(Role.CLIENT);
				} else if (roleId == Role.BANKER.getId()) {
					user.setRole(Role.BANKER);
				} else {
					user.setRole(Role.MANAGER);
				}
			}
			return user;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}

	public User getUserByUsernameAndPassword(User user) {
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USER_NAME_AND_PASSWORD);) {
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPersonalId(rs.getString("personal_id"));
				
				int roleId = rs.getInt("role_id");
				if (roleId == Role.CLIENT.getId()) {
					user.setRole(Role.CLIENT);
				} else if (roleId == Role.BANKER.getId()) {
					user.setRole(Role.BANKER);
				} else {
					user.setRole(Role.MANAGER);
				}
			}
			return user;
		} catch (SQLException e) {
			System.out.println("error " + e);
			return null;
		}
	}

}

