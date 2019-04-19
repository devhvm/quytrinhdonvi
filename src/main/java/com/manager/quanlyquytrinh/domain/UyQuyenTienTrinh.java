package com.manager.quanlyquytrinh.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UyQuyenTienTrinh.
 */
@Entity
@Table(name = "uy_quyen_tien_trinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UyQuyenTienTrinh extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tien_trinh_code", nullable = false)
    private String tienTrinhCode;

    @NotNull
    @Column(name = "from_user_id", nullable = false)
    private String fromUserId;

    @NotNull
    @Column(name = "to_user_id", nullable = false)
    private String toUserId;

    @NotNull
    @Column(name = "jhi_role", nullable = false)
    private String role;

    @ManyToOne
    @JsonIgnoreProperties("uyQuyenTienTrinhs")
    private QuyTrinhDonVi quyTrinhDonVi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTienTrinhCode() {
        return tienTrinhCode;
    }

    public UyQuyenTienTrinh tienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
        return this;
    }

    public void setTienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public UyQuyenTienTrinh fromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public UyQuyenTienTrinh toUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getRole() {
        return role;
    }

    public UyQuyenTienTrinh role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public QuyTrinhDonVi getQuyTrinhDonVi() {
        return quyTrinhDonVi;
    }

    public UyQuyenTienTrinh quyTrinhDonVi(QuyTrinhDonVi quyTrinhDonVi) {
        this.quyTrinhDonVi = quyTrinhDonVi;
        return this;
    }

    public void setQuyTrinhDonVi(QuyTrinhDonVi quyTrinhDonVi) {
        this.quyTrinhDonVi = quyTrinhDonVi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UyQuyenTienTrinh uyQuyenTienTrinh = (UyQuyenTienTrinh) o;
        if (uyQuyenTienTrinh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uyQuyenTienTrinh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UyQuyenTienTrinh{" +
            "id=" + getId() +
            ", tienTrinhCode='" + getTienTrinhCode() + "'" +
            ", fromUserId='" + getFromUserId() + "'" +
            ", toUserId='" + getToUserId() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
