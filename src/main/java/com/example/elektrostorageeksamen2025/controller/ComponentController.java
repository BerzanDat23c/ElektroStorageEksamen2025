package com.example.elektrostorageeksamen2025.controller;

import com.example.elektrostorageeksamen2025.entity.*;
import com.example.elektrostorageeksamen2025.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/components")
public class ComponentController {
    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    public List<Component> getAllComponents() {
        return componentService.getAllComponents();
    }

    @PostMapping
    public Component saveComponent(@RequestBody Component component) {
        return componentService.saveComponent(component);
    }

    @PutMapping("/{id}/obsolete")
    public ResponseEntity<String> markComponentObsolete(@PathVariable Long id) {
        Optional<Component> component = componentService.getComponentById(id);
        if (component.isPresent()) {
            Component updatedComponent = component.get();
            updatedComponent.setObsolete(true);
            componentService.saveComponent(updatedComponent);
            return ResponseEntity.ok("Component marked as obsolete");
        }
        return ResponseEntity.notFound().build();
    }
}