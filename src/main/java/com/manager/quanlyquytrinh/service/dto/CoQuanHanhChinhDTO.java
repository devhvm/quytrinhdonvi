package com.manager.quanlyquytrinh.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CoQuanHanhChinh entity.
 */
public class CoQuanHanhChinhDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String coQuanHanhChinhCode;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String maDinhDanhCode;

    @NotNull
    private String level;

    @NotNull
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoQuanHanhChinhCode() {
        return coQuanHanhChinhCode;
    }

    public void setCoQuanHanhChinhCode(String coQuanHanhChinhCode) {
        this.coQuanHanhChinhCode = coQuanHanhChinhCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaDinhDanhCode() {
        return maDinhDanhCode;
    }

    public void setMaDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CoQuanHanhChinhDTO coQuanHanhChinhDTO = (CoQuanHanhChinhDTO) o;
        if (coQuanHanhChinhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coQuanHanhChinhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoQuanHanhChinhDTO{" +
            "id=" + getId() +
            ", coQuanHanhChinhCode='" + getCoQuanHanhChinhCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", maDinhDanhCode='" + getMaDinhDanhCode() + "'" +
            ", level='" + getLevel() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
