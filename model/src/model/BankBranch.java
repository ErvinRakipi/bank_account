package model;

import java.util.*;

public class BankBranch {
	private int branchId;
	private String branchCity;
	private String branchAddress;
	private String branchName;
	private User manager;
	private ArrayList<User> employees = new ArrayList<>();
	private ArrayList<User> clients = new ArrayList<>();
	//private ArrayList<BankAccount> bankAccounts = new ArrayList<>();
	
	private Map<String, BankAccount> bankAccounts;
	
	public BankBranch() {
		super();
		this.bankAccounts = new HashMap<String,BankAccount>();
		
	}
	
	public Map<String, BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Map<String, BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public BankBranch(int branchId,String branchCity, String branchAddress, String branchName) {
		super();
		this.branchId = branchId;
		this.branchCity = branchCity;
		this.branchAddress = branchAddress;
		this.branchName = branchName;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}
	
	public String getBranchAddress() {
		return branchAddress;
	}
	
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	public ArrayList<User> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<User> employees) {
		this.employees = employees;
	}

	public ArrayList<User> getClients() {
		return clients;
	}

	public void setClients(ArrayList<User> clients) {
		this.clients = clients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankAccounts == null) ? 0 : bankAccounts.hashCode());
		result = prime * result + ((branchAddress == null) ? 0 : branchAddress.hashCode());
		result = prime * result + ((branchCity == null) ? 0 : branchCity.hashCode());
		result = prime * result + branchId;
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankBranch other = (BankBranch) obj;
		if (bankAccounts == null) {
			if (other.bankAccounts != null)
				return false;
		} else if (!bankAccounts.equals(other.bankAccounts))
			return false;
		if (branchAddress == null) {
			if (other.branchAddress != null)
				return false;
		} else if (!branchAddress.equals(other.branchAddress))
			return false;
		if (branchCity == null) {
			if (other.branchCity != null)
				return false;
		} else if (!branchCity.equals(other.branchCity))
			return false;
		if (branchId != other.branchId)
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (clients == null) {
			if (other.clients != null)
				return false;
		} else if (!clients.equals(other.clients))
			return false;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankBranch [branchId=" + branchId + ", branchCity=" + branchCity + ", branchAddress=" + branchAddress
				+ ", branchName=" + branchName + ", manager=" + manager + ", employees=" + employees + ", clients="
				+ clients + ", bankAccounts=" + bankAccounts + ", getBankAccounts()=" + getBankAccounts()
				+ ", getBranchId()=" + getBranchId() + ", getBranchCity()=" + getBranchCity() + ", getBranchAddress()="
				+ getBranchAddress() + ", getBranchName()=" + getBranchName() + ", getManager()=" + getManager()
				+ ", getEmployees()=" + getEmployees() + ", getClients()=" + getClients() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}

	
	
	/*public ArrayList<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
*/
	
	
 }

