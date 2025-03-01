package com.example.elektrostorageeksamen2025.service;



import com.example.elektrostorageeksamen2025.entity.Component;
import com.example.elektrostorageeksamen2025.entity.Order;
import com.example.elektrostorageeksamen2025.entity.OrderLine;
import com.example.elektrostorageeksamen2025.repository.ComponentRepository;
import com.example.elektrostorageeksamen2025.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ComponentRepository componentRepository;

    public OrderService(OrderRepository orderRepository, ComponentRepository componentRepository) {
        this.orderRepository = orderRepository;
        this.componentRepository = componentRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public String addComponentToOrder(Long orderId, OrderLine orderLine) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<Component> optionalComponent = componentRepository.findById(orderLine.getComponent().getId()); // 🟢 Nu virker denne linje

        if (optionalOrder.isPresent() && optionalComponent.isPresent()) {
            Order order = optionalOrder.get();
            Component component = optionalComponent.get();

            orderLine.setComponent(component);
            order.getOrderLines().add(orderLine);
            orderRepository.save(order);

            return "Component added to order";
        }
        return "Order or Component not found";
    }


    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
