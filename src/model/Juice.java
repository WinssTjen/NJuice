package model;

public class Juice {
	private String juiceID, juiceName, juiceDesc;
	private int juicePrice;
	
	public String getJuiceID() {
		return juiceID;
	}
	
	public void setJuiceID(String juiceID) {
		this.juiceID = juiceID;
	}
	
	public String getJuiceName() {
		return juiceName;
	}
	
	public void setJuiceName(String juiceName) {
		this.juiceName = juiceName;
	}
	
	public String getJuiceDesc() {
		return juiceDesc;
	}
	
	public void setJuiceDesc(String juiceDesc) {
		this.juiceDesc = juiceDesc;
	}
	
	public int getJuicePrice() {
		return juicePrice;
	}
	
	public void setJuicePrice(int juicePrice) {
		this.juicePrice = juicePrice;
	}
	
	public Juice(String juiceID, String juiceName, int juicePrice, String juiceDesc) {
		super();
		this.juiceID = juiceID;
		this.juiceName = juiceName;
		this.juicePrice = juicePrice;
		this.juiceDesc = juiceDesc;
	}
	
}
