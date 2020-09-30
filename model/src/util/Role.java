package util;

public enum Role {

	MANAGER(1,"Manager"), BANKER(2,"Banker"), CLIENT(3,"Client");

	private String name;
	private int id;
	
	Role(int id, String name) {
		
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
