package util;

public enum AccountType {
	
	STANDARDBANKACCOUNT(1,"StandardBankAccount"), INTERESTBANKACCOUNT(2,"InterestBankAccount");

	private String name;
	private int id;
	
	AccountType(int id, String name) {
		
		this.id = id;
		this.name = name;
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
