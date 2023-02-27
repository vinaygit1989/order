package com.vinay.assignment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_book")
public class OrderBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -648066994052097678L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer id;
	private BigDecimal price;
	private Long quantity;
	private String orderSide;
	private Date createdDate;
	private Date updatedDate;

	public OrderBook() {
		super();
	}

	public OrderBook(Integer id, BigDecimal price, Long quantity, String orderSide, Date createdDate,
			Date updatedDate) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.orderSide = orderSide;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "OrderBook [id=" + id + ", price=" + price + ", quantity=" + quantity + ", orderSide=" + orderSide
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
}
