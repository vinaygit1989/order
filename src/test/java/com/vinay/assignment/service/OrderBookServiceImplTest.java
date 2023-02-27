package com.vinay.assignment.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import com.vinay.assignment.OrderBookServiceApplication;
import com.vinay.assignment.dto.OrderBookDto;
import com.vinay.assignment.entity.OrderBook;
import com.vinay.assignment.exception.OrderException;
import com.vinay.assignment.repository.OrderBookRepository;
import com.vinay.assignment.service.impl.OrderBookServiceImpl;

@PropertySource("classpath:/application.properties")
@ContextConfiguration(classes = { OrderBookServiceApplication.class })
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Tag("ComponentTest")
class OrderBookServiceImplTest {
	
	@InjectMocks
	private OrderBookServiceImpl orderBookServiceImpl;
	
	@Mock
	private OrderBookRepository orderBookRepository;
	
	@Test
	void testAddOrder() throws OrderException{
		OrderBookDto dto = new OrderBookDto(BigDecimal.valueOf(15.5),10l,"buy");
		OrderBook orderBook = new OrderBook(0, BigDecimal.valueOf(15.5), 10l,
				"BUY", new Date(), new Date());
		Mockito.when(orderBookRepository.save(Mockito.any())).thenReturn(orderBook);
		OrderBook order = orderBookServiceImpl.addOrder(dto);
		assertNotNull(order);
	}
	
	@Test
	void testModifyOrder() throws OrderException{
		OrderBook orderBook = new OrderBook(1, BigDecimal.valueOf(15.5), 10l,
				"BUY", new Date(), new Date());
		Mockito.when(orderBookRepository.findById(Mockito.any())).thenReturn(Optional.of(orderBook));
		Mockito.when(orderBookRepository.save(Mockito.any())).thenReturn(orderBook);
		OrderBook order = orderBookServiceImpl.modifyOrder(1,15l);
		assertNotNull(order);
	}
	
	@Test
	void testFindOrderByOrderId() throws OrderException{
		OrderBook orderBook = new OrderBook(1, BigDecimal.valueOf(15.5), 10l,
				"BUY", new Date(), new Date());
		Mockito.when(orderBookRepository.findById(Mockito.any())).thenReturn(Optional.of(orderBook));
		OrderBook order = orderBookServiceImpl.findOrderByOrderId(1);
		assertNotNull(order);
	}
	
	@Test
	void testFindAllOrders() throws OrderException{
		OrderBook orderBook = new OrderBook(1, BigDecimal.valueOf(15.5), 10l,
				"BUY", new Date(), new Date());
		List<OrderBook> list = new ArrayList<>();
		list.add(orderBook);
		Mockito.when(orderBookRepository.findAll()).thenReturn(list);
		List<OrderBook> order = orderBookServiceImpl.findAllOrders();
		assertNotNull(order);
	}
	
	@Test
	void testDeleteOrder() throws OrderException{
		OrderBook orderBook = new OrderBook(1, BigDecimal.valueOf(15.5), 10l,
				"BUY", new Date(), new Date());
		Mockito.lenient().when(orderBookRepository.findById(Mockito.any())).thenReturn(Optional.of(orderBook));
		orderBookServiceImpl.deleteOrder(1);
		verify(orderBookRepository,times(1)).deleteById(1);
	}
}
