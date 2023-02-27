package com.vinay.assignment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class OrderBookDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5183323624189880704L;
	@NotNull(message = "Price can not be empty or null")
	private BigDecimal price;
	@NotNull(message = "Quantity can not be empty or null")
	private Long quantity;
	@NotNull(message = "OrderSide can not be empty or null")
	private String orderSide;
	public OrderBookDto() {
		super();
	}
	public OrderBookDto(BigDecimal price, Long quantity, String orderSide) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.orderSide = orderSide;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getOrderSide() {
		return orderSide;
	}
	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}
	@Override
	public String toString() {
		return "OrderBookDto [price=" + price + ", quantity=" + quantity + ", orderSide=" + orderSide + "]";
	}
}
