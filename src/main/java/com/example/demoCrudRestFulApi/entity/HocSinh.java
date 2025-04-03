package com.example.demoCrudRestFulApi.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "hoc_sinh")
public class HocSinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ma")
    private String maHocSinh;
    @Column(name = "ten")
    private String tenHocSinh;
    @Column(name = "nam_sinh")
    private Date namSinh;
    @Column(name = "email")
    private String email;
    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;
    @Column(name = "trang_thai")
    private Boolean trangThai;

    public HocSinh() {
    }

    public HocSinh(Long id, String maHocSinh, String tenHocSinh, Date namSinh, String email, Boolean gioiTinh, Boolean trangThai) {
        this.id = id;
        this.maHocSinh = maHocSinh;
        this.tenHocSinh = tenHocSinh;
        this.namSinh = namSinh;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHocSinh() {
        return maHocSinh;
    }

    public void setMaHocSinh(String maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    public String getTenHocSinh() {
        return tenHocSinh;
    }

    public void setTenHocSinh(String tenHocSinh) {
        this.tenHocSinh = tenHocSinh;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }
}
