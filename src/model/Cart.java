package model;

public class Cart {
	private int qty;
	private String juiceName;
	private int juicePrice;
	private String formattedData;


	public Cart(int qty, String juiceName, int juicePrice, String formattedData) {
		super();
		this.qty = qty;
		this.juiceName = juiceName;
		this.juicePrice = juicePrice;
		this.formattedData = formattedData;
	}
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getJuiceName() {
		return juiceName;
	}

	public void setJuiceName(String juiceName) {
		this.juiceName = juiceName;
	}

	public int getJuicePrice() {
		return juicePrice;
	}

	public void setJuicePrice(int juicePrice) {
		this.juicePrice = juicePrice;
	}
	
	public String getFormattedData() {
		return formattedData;
	}
	
	public void setFormattedData(String formattedData) {
		this.formattedData = formattedData;
	}
	
}
