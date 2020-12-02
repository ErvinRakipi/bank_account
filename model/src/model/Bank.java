package model;

import java.util.ArrayList;

// Whats up Ervin?

public class Bank {

	private String name;
	
	private ArrayList<BankBranch> branches;
	
	
	public Bank() {
		super();
	}

	public Bank(String name) {
		super();
		this.name = name;
		this.branches = new ArrayList<BankBranch> ();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bank [name=" + name + ", branches=" + branches + ", getName()=" + getName() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
