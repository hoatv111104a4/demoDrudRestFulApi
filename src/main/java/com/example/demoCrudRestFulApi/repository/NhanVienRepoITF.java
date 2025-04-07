package com.example.demoCrudRestFulApi.repository;

import com.example.demoCrudRestFulApi.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface NhanVienRepoITF extends JpaRepository<NhanVien,Long> {


    @Query("select nv from NhanVien nv")
    Page<NhanVien> pageNhanVien(Pageable pageable);

    @Query("select nv from NhanVien nv where nv.tenNhanVien like %:tenNhanVien% ")
    Page<NhanVien> pageNhanVien2(@RequestParam("tenNhanVien") String tenNhanVien, Pageable pageable);
}
