package model;

public class SalesVO {
	private int receiptNo;
	private String productNameCus;
	private int quantityCus;
	private int totalPriceCus;
	private String stockInDateCus;

	public SalesVO() {
		super();
	}

	public SalesVO(int receiptNo, String productNameCus, int quantityCus, int totalPriceCus, String stockInDateCus) {
		super();
		this.receiptNo = receiptNo;
		this.productNameCus = productNameCus;
		this.quantityCus = quantityCus;
		this.totalPriceCus = totalPriceCus;
		this.stockInDateCus = stockInDateCus;
	}

	public int getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getProductNameCus() {
		return productNameCus;
	}

	public void setProductNameCus(String productNameCus) {
		this.productNameCus = productNameCus;
	}

	public int getQuantityCus() {
		return quantityCus;
	}

	public void setQuantityCus(int quantityCus) {
		this.quantityCus = quantityCus;
	}

	public int getTotalPriceCus() {
		return totalPriceCus;
	}

	public void setTotalPriceCus(int totalPriceCus) {
		this.totalPriceCus = totalPriceCus;
	}

	public String getStockInDateCus() {
		return stockInDateCus;
	}

	public void setStockInDateCus(String stockInDateCus) {
		this.stockInDateCus = stockInDateCus;
	}

	@Override
	public String toString() {
		return "SalesVO [receiptNo=" + receiptNo + ", productNameCus=" + productNameCus + ", quantityCus=" + quantityCus
				+ ", totalPriceCus=" + totalPriceCus + ", stockInDateCus=" + stockInDateCus + "]";
	}

}
