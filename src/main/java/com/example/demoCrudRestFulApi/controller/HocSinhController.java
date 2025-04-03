package com.example.demoCrudRestFulApi.controller;

import com.example.demoCrudRestFulApi.entity.HocSinh;
import com.example.demoCrudRestFulApi.entity.NhanVien;
import com.example.demoCrudRestFulApi.service.HocSinhService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hoc-sinh")
public class HocSinhController {
    private final HocSinhService hocSinhService;

    public HocSinhController(HocSinhService hocSinhService) {
        this.hocSinhService = hocSinhService;
    }
    @GetMapping
    public ResponseEntity<List<HocSinh>> getAllNhanVien(){
        List<HocSinh> hocSinhList = hocSinhService.getAllHocSinh();
        return ResponseEntity.ok(hocSinhList);
    }

    @PostMapping
    public ResponseEntity<?> addHocSinh(@RequestBody HocSinh hocSinh) {
        try {
            HocSinh saveHocSinh = hocSinhService.save(hocSinh);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveHocSinh);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Loi khi them hoc sin "+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdHocSinh(@PathVariable Long id){
        Optional<HocSinh> hocSinh = hocSinhService.findByIdHocSinh(id);
        if (hocSinh.isPresent()){
            return ResponseEntity.ok(hocSinh.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHocSinh(@PathVariable Long id,@RequestBody HocSinh updateHocSinh){
        Optional<HocSinh> existinghocSinh = hocSinhService.findByIdHocSinh(id);
        if (existinghocSinh.isPresent()){
            HocSinh hocSinh = existinghocSinh.get();
            hocSinh.setMaHocSinh(updateHocSinh.getMaHocSinh());
            hocSinh.setTenHocSinh(updateHocSinh.getTenHocSinh());
            hocSinh.setNamSinh(updateHocSinh.getNamSinh());
            hocSinh.setEmail(updateHocSinh.getEmail());
            hocSinh.setGioiTinh(updateHocSinh.getGioiTinh());
            hocSinh.setTrangThai(updateHocSinh.getTrangThai());
            hocSinhService.save(hocSinh);
            return ResponseEntity.ok(hocSinh);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay hoc sinh co id "+ id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHocSinh(@PathVariable Long id){
        Optional<HocSinh> existinghocSinh = hocSinhService.findByIdHocSinh(id);
        if (existinghocSinh.isPresent()){
            hocSinhService.deleteByIdHocSinh(id);
            return ResponseEntity.ok("Da xoa nhan vien co id : " + id);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy nhân viên có ID: " + id);
        }
    }

    @GetMapping("/tim-kiem")
    public ResponseEntity<?> timKiem(@RequestParam String tenHocSinh){
        List<HocSinh> hocSinhList = hocSinhService.findByTenHocSinh(tenHocSinh);
        if (!hocSinhList.isEmpty()){
            return ResponseEntity.ok(hocSinhList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/sap-xep")
    public ResponseEntity<List<HocSinh>> sapXep(){
        List<HocSinh> hocSinhList = hocSinhService.sortHocSinh();
        return ResponseEntity.ok(hocSinhList);
    }

    @GetMapping("/phan-trang")
    public ResponseEntity<Page<HocSinh>> pageHocSinh(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(required = false) String tenHocSinh){
        Pageable pageable = PageRequest.of(page,size);
        Page<HocSinh> hocSinhPage = hocSinhService.pageHocSinh(tenHocSinh,pageable);
        return ResponseEntity.ok(hocSinhPage);
    }
}
