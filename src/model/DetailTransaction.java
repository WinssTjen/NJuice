package model;

public class DetailTransaction {
	
	private String idTransaction2;
	private String idJuice;
	private String juiceName;
	private int qty;
	
	public DetailTransaction(String idTransaction2, String idJuice, String juiceName, int qty) {
		super();
		this.idTransaction2 = idTransaction2;
		this.idJuice = idJuice;
		this.juiceName = juiceName;
		this.qty = qty;
	}
	
	public String getIdTransaction2() {
		return idTransaction2;
	}

	public void setIdTransaction2(String idTransaction2) {
		this.idTransaction2 = idTransaction2;
	}

	public String getIdJuice() {
		return idJuice;
	}

	public void setIdJuice(String idJuice) {
		this.idJuice = idJuice;
	}

	public String getJuiceName() {
		return juiceName;
	}

	public void setJuiceName(String juiceName) {
		this.juiceName = juiceName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
