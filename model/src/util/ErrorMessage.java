package util;

public enum ErrorMessage {
	
	USER_EXISTS("\nThe User you are trying to add already exists in our Bank System"),
	USER_DOES_NOT_EXISTS("\nThe User you are trying to remove does not exists in our Bank System"),
	WRONG_USERNAME_OR_PASSWORD("\nCredentials Declined Please try again."),
	BANK_ACCOUNT_EXISTS("\nThe Bank Account you are trying to add already exists in our Bank System"),
	WRONG_PERSONAL_ID("\nThe User with the Personal ID provided, does not exists in our Bank System"),
	WRONG_AMOUNT("\nThe Amount you can deposit must be more than 0"),
	INSUFFICIENT_BALANCE("\nYou have Insufficient balance.");
	
	private String errorMessage;
	
	ErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
