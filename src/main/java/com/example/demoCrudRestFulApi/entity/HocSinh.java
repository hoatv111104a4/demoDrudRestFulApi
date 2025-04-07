package com.example.demoCrudRestFulApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "hoc_sinh" )
public class HocSinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;
    @Column(name = "ma", unique = true, nullable = false)
    @Size(min = 3, max = 50, message = "Vui lòng nhập mã nằm từ 3-40 kí tự")
    @NotBlank(message = "Mã học sinh không được để trống")
    private String maHocSinh;

    @Column(name = "ten", nullable = false)
    @Size(min = 2, max = 50, message = "Vui lòng nhập tên nằm từ 2-50 kí tự")
    @NotBlank(message = "Tên học sinh không được để trống")
    private String tenHocSinh;

    @Column(name = "nam_sinh", nullable = false)
    @Past(message = "Năm sinh phải là một ngày trong quá khứ")
    @NotNull(message = "Năm sinh không được để trống")
    private Date namSinh;

    @Column(name = "email", unique = true, nullable = false)
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",
            message = "Vui lòng nhập email có định dạng  @gmail.com")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @Column(name = "gioi_tinh", nullable = false)
    @NotNull(message = "Vui lòng chọn giới tính")
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
