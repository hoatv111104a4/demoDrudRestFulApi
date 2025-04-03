package com.example.demoCrudRestFulApi.repository;


import com.example.demoCrudRestFulApi.entity.HocSinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HocSinhRepoITF extends JpaRepository<HocSinh,Long> {


    List<HocSinh> findByTenHocSinhContaining(String tenHocSinh);

    @Query(value = "select * from hoc_sinh where ten like CONCAT('%', ?1, '%') ",nativeQuery = true)
    Page<HocSinh> pageHocSinh(String tenHocSinh,Pageable pageable);
}
