package com.manager.quanlyquytrinh.service.dto.quanlyquytrinh;

import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the QuyTrinhDetail entity.
 */
public class QuyTrinhDetailDTO implements Serializable {

    private Long id;

    @NotNull
    private String quyTrinhCode;

    @NotNull
    private String name;

    private Long loaiQuyTrinhId;

    private String methodName;

    private String entityName;

    private List<TienTrinhDetailDTO> tienTrinhXuLys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuyTrinhCode() {
        return quyTrinhCode;
    }

    public void setQuyTrinhCode(String quyTrinhCode) {
        this.quyTrinhCode = quyTrinhCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLoaiQuyTrinhId() {
        return loaiQuyTrinhId;
    }

    public void setLoaiQuyTrinhId(Long loaiQuyTrinhId) {
        this.loaiQuyTrinhId = loaiQuyTrinhId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public List<TienTrinhDetailDTO> getTienTrinhXuLys() {
        return tienTrinhXuLys;
    }

    public void setTienTrinhXuLys(List<TienTrinhDetailDTO> tienTrinhXuLys) {
        this.tienTrinhXuLys = tienTrinhXuLys;
    }
}
