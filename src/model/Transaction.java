package model;

public class Transaction {
	
	private String idTransaction;
	private String paymentType;
	private String username;
	
	public Transaction(String idTransaction, String paymentType, String username) {
		super();
		this.idTransaction = idTransaction;
		this.paymentType = paymentType;
		this.username = username;
	}
	
	public String getIdTransaction() {
		return idTransaction;
	}
	
	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}