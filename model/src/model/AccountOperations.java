package model;

import java.time.LocalDateTime;
import util.OperationType;

public class AccountOperations {
	
	private float transactionAmount;
	public OperationType transactionType;
	private LocalDateTime transactionDateTime; // = LocalDateTime.now()
	private int transactionID;
	
	public AccountOperations(float transactionAmount,LocalDateTime transactionDateTime) {
		super();
		this.transactionAmount=transactionAmount;
		this.transactionDateTime=transactionDateTime;
	}

	public float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public OperationType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(OperationType transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(transactionAmount);
		result = prime * result + ((transactionDateTime == null) ? 0 : transactionDateTime.hashCode());
		result = prime * result + transactionID;
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		AccountOperations other = (AccountOperations) obj;
		if (Float.floatToIntBits(transactionAmount) != Float.floatToIntBits(other.transactionAmount))
			return false;
		if (transactionDateTime == null) {
			if (other.transactionDateTime != null)
				return false;
		} else if (!transactionDateTime.equals(other.transactionDateTime))
			return false;
		if (transactionID != other.transactionID)
			return false;
		if (transactionType != other.transactionType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountOperations [transactionAmount=" + transactionAmount + ", transactionType=" + transactionType
				+ ", transactionDateTime=" + transactionDateTime + ", transactionID=" + transactionID
				+ ", getTransactionAmount()=" + getTransactionAmount() + ", getTransactionType()="
				+ getTransactionType() + ", getTransactionDateTime()=" + getTransactionDateTime()
				+ ", getTransactionID()=" + getTransactionID() + ", hashCode()=" + hashCode() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}

	
	
}
