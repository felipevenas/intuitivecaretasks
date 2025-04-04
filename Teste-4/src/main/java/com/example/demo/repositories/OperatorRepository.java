package com.example.demo.repositories;

import com.example.demo.domain.entities.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
