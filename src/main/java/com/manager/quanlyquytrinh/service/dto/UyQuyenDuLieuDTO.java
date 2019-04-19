package com.manager.quanlyquytrinh.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UyQuyenDuLieu entity.
 */
public class UyQuyenDuLieuDTO implements Serializable {

    private Long id;

    @NotNull
    private String fromUserId;

    @NotNull
    private String toUserId;

    @NotNull
    private String role;


    private Long duLieuTienTrinhId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getDuLieuTienTrinhId() {
        return duLieuTienTrinhId;
    }

    public void setDuLieuTienTrinhId(Long duLieuTienTrinhId) {
        this.duLieuTienTrinhId = duLieuTienTrinhId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UyQuyenDuLieuDTO uyQuyenDuLieuDTO = (UyQuyenDuLieuDTO) o;
        if (uyQuyenDuLieuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uyQuyenDuLieuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UyQuyenDuLieuDTO{" +
            "id=" + getId() +
            ", fromUserId='" + getFromUserId() + "'" +
            ", toUserId='" + getToUserId() + "'" +
            ", role='" + getRole() + "'" +
            ", duLieuTienTrinh=" + getDuLieuTienTrinhId() +
            "}";
    }
}
