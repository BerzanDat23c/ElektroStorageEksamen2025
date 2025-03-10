package com.example.elektrostorageeksamen2025.controller;

import com.example.elektrostorageeksamen2025.entity.Order;
import com.example.elektrostorageeksamen2025.entity.OrderLine;
import com.example.elektrostorageeksamen2025.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @PostMapping("/{id}/addComponent")
    public ResponseEntity<String> addComponentToOrder(@PathVariable Long id, @RequestBody OrderLine orderLine) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.getOrderLines().add(orderLine);
            orderService.saveOrder(existingOrder);
            return ResponseEntity.ok("Component added to order");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/markAsSent")
    public ResponseEntity<String> markOrderAsSent(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            Order updatedOrder = order.get();
            updatedOrder.setStatus("Sent");
            orderService.saveOrder(updatedOrder);
            return ResponseEntity.ok("Order marked as sent");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Optional<Order> optionalOrder = orderService.getOrderById(id);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setStatus(orderDetails.getStatus());  // Opdater status
            Order updatedOrder = orderService.saveOrder(existingOrder);
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.getOrderById(id).isPresent()) {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
