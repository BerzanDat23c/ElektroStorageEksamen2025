package com.example.elektrostorageeksamen2025.repository;


import com.example.elektrostorageeksamen2025.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {}