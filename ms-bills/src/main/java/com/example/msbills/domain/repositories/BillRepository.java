package com.example.msbills.domain.repositories;

import com.example.msbills.domain.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, String> {
}
