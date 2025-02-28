package com.example.elektrostorageeksamen2025.controller;

import com.example.elektrostorageeksamen2025.entity.Inventory;
import com.example.elektrostorageeksamen2025.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @PostMapping("/count")
    public ResponseEntity<String> submitInventoryCount(@RequestBody Inventory inventory) {
        inventoryService.saveInventory(inventory);
        return ResponseEntity.ok("Inventory count submitted");
    }
}

