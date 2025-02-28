package com.example.elektrostorageeksamen2025.controller;


import com.example.elektrostorageeksamen2025.entity.BOM;
import com.example.elektrostorageeksamen2025.service.BOMService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/assemblies")
public class BOMController {
    private final BOMService bomService;

    public BOMController(BOMService bomService) {
        this.bomService = bomService;
    }

    @GetMapping
    public List<BOM> getAllBOMs() {
        return bomService.getAllBOMs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BOM> getBOMById(@PathVariable Long id) {
        return bomService.getAllBOMs().stream()
                .filter(bom -> bom.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}