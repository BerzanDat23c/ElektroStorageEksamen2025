package com.example.elektrostorageeksamen2025.service;

import com.example.elektrostorageeksamen2025.entity.Component;
import com.example.elektrostorageeksamen2025.repository.ComponentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComponentService {
    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public Optional<Component> getComponentById(Long id) {
        return componentRepository.findById(id);
    }

    public Component saveComponent(Component component) {
        return componentRepository.save(component);
    }
}