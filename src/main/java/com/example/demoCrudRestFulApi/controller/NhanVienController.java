package com.example.demoCrudRestFulApi.controller;

import com.example.demoCrudRestFulApi.dto.NhanVienDto;
import com.example.demoCrudRestFulApi.entity.NhanVien;
import com.example.demoCrudRestFulApi.service.NhanVienService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienController {
    private final NhanVienService nhanVienService;

    public NhanVienController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    @GetMapping
    public ResponseEntity<List<NhanVien>> getAllNhanVien(){
        List<NhanVien> nhanVienDtoList = nhanVienService.getAlLNhanVien();
        return ResponseEntity.ok(nhanVienDtoList);
    }
    @PostMapping
    public ResponseEntity<?> addNhanVien(@RequestBody NhanVien nhanVien){
        try {
            NhanVien savedNhanVien = nhanVienService.save(nhanVien);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNhanVien);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi thêm nhân viên: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NhanVien> getByIdNhanVien(@PathVariable Long id) {
        Optional<NhanVien> nhanVien = nhanVienService.findById(id);
        if (nhanVien.isPresent()) {
            return ResponseEntity.ok(nhanVien.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNhanVien(@PathVariable Long id, @RequestBody NhanVien updatedNhanVien) {
        Optional<NhanVien> existingNhanVien = nhanVienService.findById(id);

        if (existingNhanVien.isPresent()) {
            NhanVien nhanVien = existingNhanVien.get();
            nhanVien.setMaNhanVien(updatedNhanVien.getMaNhanVien());
            nhanVien.setTenNhanVien(updatedNhanVien.getTenNhanVien());
            nhanVien.setNamSinh(updatedNhanVien.getNamSinh());
            nhanVien.setGioiTinh(updatedNhanVien.getGioiTinh());
            nhanVien.setEmail(updatedNhanVien.getEmail());
            nhanVienService.save(nhanVien); // Lưu vào database
            return ResponseEntity.ok(nhanVien); // Trả về 200 OK + dữ liệu nhân viên đã cập nhật
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy nhân viên có ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable Long id) {
        Optional<NhanVien> nhanVien = nhanVienService.findById(id);

        if (nhanVien.isPresent()) {
            nhanVienService.deleteById(id);  // Xóa nhân viên
            return ResponseEntity.ok("Đã xóa nhân viên có ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy nhân viên có ID: " + id);
        }
    }

}
