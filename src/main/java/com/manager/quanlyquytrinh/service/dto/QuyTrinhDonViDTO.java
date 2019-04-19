package com.manager.quanlyquytrinh.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the QuyTrinhDonVi entity.
 */
public class QuyTrinhDonViDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String quyTrinhCode;

    @NotNull
    private String name;


    private Long coQuanHanhChinhId;

    private String coQuanHanhChinhName;

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

    public Long getCoQuanHanhChinhId() {
        return coQuanHanhChinhId;
    }

    public void setCoQuanHanhChinhId(Long coQuanHanhChinhId) {
        this.coQuanHanhChinhId = coQuanHanhChinhId;
    }

    public String getCoQuanHanhChinhName() {
        return coQuanHanhChinhName;
    }

    public void setCoQuanHanhChinhName(String coQuanHanhChinhName) {
        this.coQuanHanhChinhName = coQuanHanhChinhName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuyTrinhDonViDTO quyTrinhDonViDTO = (QuyTrinhDonViDTO) o;
        if (quyTrinhDonViDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quyTrinhDonViDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuyTrinhDonViDTO{" +
            "id=" + getId() +
            ", quyTrinhCode='" + getQuyTrinhCode() + "'" +
            ", name='" + getName() + "'" +
            ", coQuanHanhChinh=" + getCoQuanHanhChinhId() +
            ", coQuanHanhChinh='" + getCoQuanHanhChinhName() + "'" +
            "}";
    }
}
