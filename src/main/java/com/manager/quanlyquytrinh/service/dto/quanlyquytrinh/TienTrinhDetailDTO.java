package com.manager.quanlyquytrinh.service.dto.quanlyquytrinh;

import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the TienTrinh entity.
 */
public class TienTrinhDetailDTO implements Serializable {

    private TienTrinhDTO tienTrinhBatDau;

    private List<TienTrinhDTO> tienTrinhKetThucs;

    private DuLieuTienTrinhDTO duLieuTienTrinh;

    public TienTrinhDTO getTienTrinhBatDau() {
        return tienTrinhBatDau;
    }

    public void setTienTrinhBatDau(TienTrinhDTO tienTrinhBatDau) {
        this.tienTrinhBatDau = tienTrinhBatDau;
    }

    public List<TienTrinhDTO> getTienTrinhKetThucs() {
        return tienTrinhKetThucs;
    }

    public void setTienTrinhKetThucs(List<TienTrinhDTO> tienTrinhKetThucs) {
        this.tienTrinhKetThucs = tienTrinhKetThucs;
    }

    public DuLieuTienTrinhDTO getDuLieuTienTrinh() {
        return duLieuTienTrinh;
    }

    public void setDuLieuTienTrinh(DuLieuTienTrinhDTO duLieuTienTrinh) {
        this.duLieuTienTrinh = duLieuTienTrinh;
    }
}
