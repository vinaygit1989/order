package com.vinay.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinay.assignment.entity.OrderBook;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Integer> {


}
