package com.example.elektrostorageeksamen2025.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class BOM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "bom", cascade = CascadeType.ALL)
    private List<BOMComponent> bomComponents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BOMComponent> getBomComponents() {
        return bomComponents;
    }

    public void setBomComponents(List<BOMComponent> bomComponents) {
        this.bomComponents = bomComponents;
    }

    // Getters and Setters
}
