package model;

public class Cart {
	private String juiceName;
	private int price;
	private int qty;
	private String formatString;

	public Cart(String juiceName, int price, int qty, String formatString) {
		super();
		this.juiceName = juiceName;
		this.price = price;
		this.qty = qty;
		this.formatString = formatString;
	}

	public String getJuiceName() {
		return juiceName;
	}
	
	public void setJuiceName(String juiceName) {
		this.juiceName = juiceName;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public String getFormatString() {
		return formatString;
	}

	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}
	
}
