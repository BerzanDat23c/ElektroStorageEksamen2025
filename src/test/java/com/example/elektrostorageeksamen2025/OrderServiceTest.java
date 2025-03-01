package com.example.elektrostorageeksamen2025;

import com.example.elektrostorageeksamen2025.entity.Order;
import com.example.elektrostorageeksamen2025.repository.OrderRepository;
import com.example.elektrostorageeksamen2025.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void testGetAllOrders() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<Order> orders = orderService.getAllOrders();
        assertEquals(1, orders.size());
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(1L);
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.saveOrder(order);
        assertEquals(1L, result.getId());
    }
}
