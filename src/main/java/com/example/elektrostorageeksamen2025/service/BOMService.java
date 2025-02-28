package com.example.elektrostorageeksamen2025.service;

import com.example.elektrostorageeksamen2025.entity.BOM;
import com.example.elektrostorageeksamen2025.repository.BOMRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BOMService {
    private final BOMRepository bomRepository;

    public BOMService(BOMRepository bomRepository) {
        this.bomRepository = bomRepository;
    }

    public List<BOM> getAllBOMs() {
        return bomRepository.findAll();
    }

    public BOM saveBOM(BOM bom) {
        return bomRepository.save(bom);
    }
}