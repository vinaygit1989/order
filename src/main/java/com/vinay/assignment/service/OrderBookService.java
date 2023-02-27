package com.vinay.assignment.service;

import java.util.List;

import com.vinay.assignment.dto.OrderBookDto;
import com.vinay.assignment.entity.OrderBook;
import com.vinay.assignment.exception.OrderException;

public interface OrderBookService {

	OrderBook addOrder(OrderBookDto orderDto) throws OrderException;

	OrderBook modifyOrder(Integer orderId, Long quantity) throws OrderException;

	String deleteOrder(Integer orderId) throws OrderException;

	OrderBook findOrderByOrderId(Integer orderId) throws OrderException;

	List<OrderBook> findAllOrders() throws OrderException;

}
