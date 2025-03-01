package com.example.elektrostorageeksamen2025;

import com.example.elektrostorageeksamen2025.entity.Order;
import com.example.elektrostorageeksamen2025.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveAndFindOrder() {
        Order order = new Order();
        order.setStatus("Pending");

        Order savedOrder = orderRepository.save(order);
        assertTrue(orderRepository.findById(savedOrder.getId()).isPresent());
        assertEquals("Pending", savedOrder.getStatus());
    }
}
