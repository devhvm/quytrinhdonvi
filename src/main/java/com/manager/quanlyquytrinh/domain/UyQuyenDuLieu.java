package com.manager.quanlyquytrinh.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UyQuyenDuLieu.
 */
@Entity
@Table(name = "uy_quyen_du_lieu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UyQuyenDuLieu extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JsonIgnoreProperties("uyQuyenDuLieus")
    private DuLieuTienTrinh quyTrinhDonVi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public UyQuyenDuLieu fromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public UyQuyenDuLieu toUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getRole() {
        return role;
    }

    public UyQuyenDuLieu role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DuLieuTienTrinh getQuyTrinhDonVi() {
        return quyTrinhDonVi;
    }

    public UyQuyenDuLieu quyTrinhDonVi(DuLieuTienTrinh duLieuTienTrinh) {
        this.quyTrinhDonVi = duLieuTienTrinh;
        return this;
    }

    public void setQuyTrinhDonVi(DuLieuTienTrinh duLieuTienTrinh) {
        this.quyTrinhDonVi = duLieuTienTrinh;
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
        UyQuyenDuLieu uyQuyenDuLieu = (UyQuyenDuLieu) o;
        if (uyQuyenDuLieu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uyQuyenDuLieu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UyQuyenDuLieu{" +
            "id=" + getId() +
            ", fromUserId='" + getFromUserId() + "'" +
            ", toUserId='" + getToUserId() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
