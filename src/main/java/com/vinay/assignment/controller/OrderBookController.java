package com.vinay.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.assignment.dto.OrderBookDto;
import com.vinay.assignment.entity.OrderBook;
import com.vinay.assignment.exception.OrderException;
import com.vinay.assignment.service.OrderBookService;

@RestController
@RequestMapping("/order")
@Validated
public class OrderBookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderBookController.class);

	@Autowired
	private OrderBookService orderBookService;

	@PostMapping("/add")
	public ResponseEntity<OrderBook> addOrder(@RequestBody @Valid OrderBookDto orderDto) throws OrderException {
		LOGGER.info("Add order method invoked.");
		OrderBook orderBook = new OrderBook();
		try {
			orderBook = orderBookService.addOrder(orderDto);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(orderBook, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer orderId) throws OrderException {
		LOGGER.info("Delete order method invoked.");
		String message = null;
		try {
			message = orderBookService.deleteOrder(orderId);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PostMapping("/modify/{orderId}")
	public ResponseEntity<OrderBook> modifyOrder(@PathVariable Integer orderId, @RequestParam Long quantity)
			throws OrderException {
		LOGGER.info("Modify order method invoked.");
		OrderBook orderBook = new OrderBook();
		try {
			orderBook = orderBookService.modifyOrder(orderId, quantity);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(orderBook, HttpStatus.CREATED);
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<List<OrderBook>> findAllOrders() throws OrderException {
		LOGGER.info("Find all orders method invoked.");
		List<OrderBook> orderBookList = new ArrayList<>();
		try {
			orderBookList = orderBookService.findAllOrders();
		} catch (OrderException e) {
			throw new OrderException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(orderBookList, HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderBook> findOrderByOrderId(@PathVariable Integer orderId) throws OrderException {
		LOGGER.info("Find order by order id method invoked.");
		OrderBook orderBook = new OrderBook();
		try {
			orderBook = orderBookService.findOrderByOrderId(orderId);
		} catch (OrderException e) {
			throw new OrderException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(orderBook, HttpStatus.OK);
	}

}
