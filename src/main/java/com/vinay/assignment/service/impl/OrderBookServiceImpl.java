package com.vinay.assignment.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinay.assignment.dto.OrderBookDto;
import com.vinay.assignment.entity.OrderBook;
import com.vinay.assignment.exception.OrderException;
import com.vinay.assignment.repository.OrderBookRepository;
import com.vinay.assignment.service.OrderBookService;
import com.vinay.assignment.util.OrderConstants;

@Service
public class OrderBookServiceImpl implements OrderBookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderBookServiceImpl.class);

	@Autowired
	private OrderBookRepository orderBookRepository;

	@Override
	public OrderBook addOrder(OrderBookDto orderDto) throws OrderException {
		LOGGER.info("Add order method invoked in OrderBookServiceImpl.");
		OrderBook orderBook = new OrderBook(0, orderDto.getPrice(), orderDto.getQuantity(),
				orderDto.getOrderSide().toUpperCase(), new Date(), new Date());
		return orderBookRepository.save(orderBook);
	}

	@Override
	public OrderBook modifyOrder(Integer orderId, Long quantity) throws OrderException {
		LOGGER.info("Modify order method invoked in OrderBookServiceImpl.");
		OrderBook modifiedOrder = null;
		try {
			Optional<OrderBook> orderOptional = orderBookRepository.findById(orderId);
			if (orderOptional.isPresent()) {
				OrderBook existingOrder = orderOptional.get();
				existingOrder.setQuantity(quantity);
				existingOrder.setUpdatedDate(new Date());
				modifiedOrder = orderBookRepository.save(existingOrder);
			} else {
				throw new OrderException(OrderConstants.ORDER_NOT_FOUND + orderId);
			}
		} catch (OrderException e) {
			throw new OrderException(OrderConstants.ORDER_NOT_FOUND + orderId);
		}
		return modifiedOrder;
	}

	@Override
	public String deleteOrder(Integer orderId) throws OrderException {
		LOGGER.info("Delete order method invoked in OrderBookServiceImpl.");
		String message = null;
		try {
			orderBookRepository.deleteById(orderId);
			message = OrderConstants.DELETE_ORDER_SUCCESS;
		} catch (Exception e) {
			throw new OrderException(OrderConstants.EMPTY_ORDER_ID + orderId);
		}
		return message;
	}

	@Override
	public List<OrderBook> findAllOrders() throws OrderException {
		LOGGER.info("find all orders method invoked in OrderBookServiceImpl.");
		List<OrderBook> orders = new ArrayList<>();
		try {
			orders = orderBookRepository.findAll();
			if (orders.isEmpty())
				throw new OrderException("No orders found");
		} catch (OrderException e) {
			LOGGER.error(e.getMessage());
		}
		return orders.stream().sorted(Comparator.comparing(OrderBook::getUpdatedDate)).collect(Collectors.toList());
	}

	@Override
	public OrderBook findOrderByOrderId(Integer orderId) throws OrderException {
		LOGGER.info("Find order by order id method invoked in OrderBookServiceImpl.");
		try {
			Optional<OrderBook> order = orderBookRepository.findById(orderId);
			if (order.isPresent())
				return order.get();
			else
				throw new OrderException(OrderConstants.ORDER_NOT_FOUND + orderId);

		} catch (OrderException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

}
