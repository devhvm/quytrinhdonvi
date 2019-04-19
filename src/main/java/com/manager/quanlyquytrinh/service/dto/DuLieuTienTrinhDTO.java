package com.manager.quanlyquytrinh.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DuLieuTienTrinh entity.
 */
public class DuLieuTienTrinhDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String tienTrinhCode;

    @NotNull
    private String duLieuCode;

    @NotNull
    private String fromUserId;

    @NotNull
    private String toUserId;

    @NotNull
    private String level;

    @NotNull
    private String note;


    private Long quyTrinhDonViId;

    private String quyTrinhDonViName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTienTrinhCode() {
        return tienTrinhCode;
    }

    public void setTienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
    }

    public String getDuLieuCode() {
        return duLieuCode;
    }

    public void setDuLieuCode(String duLieuCode) {
        this.duLieuCode = duLieuCode;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getQuyTrinhDonViId() {
        return quyTrinhDonViId;
    }

    public void setQuyTrinhDonViId(Long quyTrinhDonViId) {
        this.quyTrinhDonViId = quyTrinhDonViId;
    }

    public String getQuyTrinhDonViName() {
        return quyTrinhDonViName;
    }

    public void setQuyTrinhDonViName(String quyTrinhDonViName) {
        this.quyTrinhDonViName = quyTrinhDonViName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DuLieuTienTrinhDTO duLieuTienTrinhDTO = (DuLieuTienTrinhDTO) o;
        if (duLieuTienTrinhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), duLieuTienTrinhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DuLieuTienTrinhDTO{" +
            "id=" + getId() +
            ", tienTrinhCode='" + getTienTrinhCode() + "'" +
            ", duLieuCode='" + getDuLieuCode() + "'" +
            ", fromUserId='" + getFromUserId() + "'" +
            ", toUserId='" + getToUserId() + "'" +
            ", level='" + getLevel() + "'" +
            ", note='" + getNote() + "'" +
            ", quyTrinhDonVi=" + getQuyTrinhDonViId() +
            ", quyTrinhDonVi='" + getQuyTrinhDonViName() + "'" +
            "}";
    }
}
