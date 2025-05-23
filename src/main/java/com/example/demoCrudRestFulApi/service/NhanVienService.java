package com.example.demoCrudRestFulApi.service;

import com.example.demoCrudRestFulApi.dto.NhanVienDto;
import com.example.demoCrudRestFulApi.entity.NhanVien;
import com.example.demoCrudRestFulApi.repository.NhanVienRepoITF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {
    private final NhanVienRepoITF nhanVienRepoITF;

    public NhanVienService(NhanVienRepoITF nhanVienRepoITF) {
        this.nhanVienRepoITF = nhanVienRepoITF;
    }

    public List<NhanVien> getAlLNhanVien() {
        return nhanVienRepoITF.findAll();
    }


    public NhanVien save(NhanVien nhanVien) {
        return nhanVienRepoITF.save(nhanVien);
    }

    public Optional<NhanVien> findById(Long id) {
        return nhanVienRepoITF.findById(id);
    }


    public void deleteById(Long id) {
        nhanVienRepoITF.deleteById(id);
    }

    public Page<NhanVien> pageNhanVien(Pageable pageable) {
        return nhanVienRepoITF.pageNhanVien(pageable);
    }

    public Page<NhanVien> pageNhanVien2(String tenNhanVien, Pageable pageable) {
        if (tenNhanVien==null || tenNhanVien.trim().isEmpty()){
            return nhanVienRepoITF.findAll(pageable);
        }
        return nhanVienRepoITF.pageNhanVien2(tenNhanVien,pageable);
    }
}
