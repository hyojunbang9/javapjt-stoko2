package model;

import java.util.Objects;

public class ProductVO {

	private int stokoNum;
	private String productName;
	private int quantity;
	private int price;
	private String stockInDate;

	public ProductVO() {
		super();
	}

	public ProductVO(int stokoNum, String productName, int quantity, int price, String stockInDate) {
		super();
		this.stokoNum = stokoNum;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.stockInDate = stockInDate;
	}

	public int getStokoNum() {
		return stokoNum;
	}

	public void setStokoNum(int stokoNum) {
		this.stokoNum = stokoNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStockInDate() {
		return stockInDate;
	}



	public void setStockInDate(String stockInDate) {
		this.stockInDate = stockInDate;
	}

	@Override
	public String toString() {
		return "ProductVO [stokoNum=" + stokoNum + ", productName=" + productName + ", quantity=" + quantity
				+ ", price=" + price + ", stockInDate=" + stockInDate + "]";
	}

}