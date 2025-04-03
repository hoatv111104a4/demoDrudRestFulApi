package com.example.demoCrudRestFulApi.service;

import com.example.demoCrudRestFulApi.entity.HocSinh;
import com.example.demoCrudRestFulApi.repository.HocSinhRepoITF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HocSinhService {
    private final HocSinhRepoITF hocSinhRepoITF;

    public HocSinhService(HocSinhRepoITF hocSinhRepoITF) {
        this.hocSinhRepoITF = hocSinhRepoITF;
    }

    public List<HocSinh> getAllHocSinh() {
        return hocSinhRepoITF.findAll();
    }

    public HocSinh save(HocSinh hocSinh) {
        return hocSinhRepoITF.save(hocSinh);
    }


    public Optional<HocSinh> findByIdHocSinh(Long id) {
        return hocSinhRepoITF.findById(id);
    }

    public void deleteByIdHocSinh(Long id) {
        hocSinhRepoITF.deleteById(id);
    }

    public List<HocSinh> findByTenHocSinh(String tenHocSinh) {
        return hocSinhRepoITF.findByTenHocSinhContaining(tenHocSinh);
    }

    public List<HocSinh> sortHocSinh() {
        return hocSinhRepoITF.findAll(Sort.by("tenHocSinh").ascending());
    }

    public Page<HocSinh> pageHocSinh(String tenHocSinh,Pageable pageable){
        if (tenHocSinh == null|| tenHocSinh.isEmpty()){
            return hocSinhRepoITF.findAll(pageable);
        }
        return hocSinhRepoITF.pageHocSinh(tenHocSinh,pageable);
    }
}
