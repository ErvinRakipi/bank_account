package util;

public enum OperationType {
	
	DEPOSIT(1,"Deposit"), WITHDRAW(2,"Withdraw");

	private String name;
	private int id;
	
	OperationType(int id, String name) {
		
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
