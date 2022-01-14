package com.example.springbatchexample.repo;

import com.example.springbatchexample.entity.ReqData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqDataRepository extends JpaRepository<ReqData, String> {
}
