package com.manager.quanlyquytrinh.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A QuyTrinhDonVi.
 */
@Entity
@Table(name = "quy_trinh_don_vi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuyTrinhDonVi extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "quy_trinh_code", nullable = false)
    private String quyTrinhCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "quyTrinhDonVi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DuLieuTienTrinh> duLieuTienTrinhs = new HashSet<>();
    @OneToMany(mappedBy = "quyTrinhDonVi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UyQuyenTienTrinh> uyQuyenTienTrinhs = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("quyTrinhDonVis")
    private CoQuanHanhChinh coQuanHanhChinh;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuyTrinhCode() {
        return quyTrinhCode;
    }

    public QuyTrinhDonVi quyTrinhCode(String quyTrinhCode) {
        this.quyTrinhCode = quyTrinhCode;
        return this;
    }

    public void setQuyTrinhCode(String quyTrinhCode) {
        this.quyTrinhCode = quyTrinhCode;
    }

    public String getName() {
        return name;
    }

    public QuyTrinhDonVi name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DuLieuTienTrinh> getDuLieuTienTrinhs() {
        return duLieuTienTrinhs;
    }

    public QuyTrinhDonVi duLieuTienTrinhs(Set<DuLieuTienTrinh> duLieuTienTrinhs) {
        this.duLieuTienTrinhs = duLieuTienTrinhs;
        return this;
    }

    public QuyTrinhDonVi addDuLieuTienTrinh(DuLieuTienTrinh duLieuTienTrinh) {
        this.duLieuTienTrinhs.add(duLieuTienTrinh);
        duLieuTienTrinh.setQuyTrinhDonVi(this);
        return this;
    }

    public QuyTrinhDonVi removeDuLieuTienTrinh(DuLieuTienTrinh duLieuTienTrinh) {
        this.duLieuTienTrinhs.remove(duLieuTienTrinh);
        duLieuTienTrinh.setQuyTrinhDonVi(null);
        return this;
    }

    public void setDuLieuTienTrinhs(Set<DuLieuTienTrinh> duLieuTienTrinhs) {
        this.duLieuTienTrinhs = duLieuTienTrinhs;
    }

    public Set<UyQuyenTienTrinh> getUyQuyenTienTrinhs() {
        return uyQuyenTienTrinhs;
    }

    public QuyTrinhDonVi uyQuyenTienTrinhs(Set<UyQuyenTienTrinh> uyQuyenTienTrinhs) {
        this.uyQuyenTienTrinhs = uyQuyenTienTrinhs;
        return this;
    }

    public QuyTrinhDonVi addUyQuyenTienTrinh(UyQuyenTienTrinh uyQuyenTienTrinh) {
        this.uyQuyenTienTrinhs.add(uyQuyenTienTrinh);
        uyQuyenTienTrinh.setQuyTrinhDonVi(this);
        return this;
    }

    public QuyTrinhDonVi removeUyQuyenTienTrinh(UyQuyenTienTrinh uyQuyenTienTrinh) {
        this.uyQuyenTienTrinhs.remove(uyQuyenTienTrinh);
        uyQuyenTienTrinh.setQuyTrinhDonVi(null);
        return this;
    }

    public void setUyQuyenTienTrinhs(Set<UyQuyenTienTrinh> uyQuyenTienTrinhs) {
        this.uyQuyenTienTrinhs = uyQuyenTienTrinhs;
    }

    public CoQuanHanhChinh getCoQuanHanhChinh() {
        return coQuanHanhChinh;
    }

    public QuyTrinhDonVi coQuanHanhChinh(CoQuanHanhChinh coQuanHanhChinh) {
        this.coQuanHanhChinh = coQuanHanhChinh;
        return this;
    }

    public void setCoQuanHanhChinh(CoQuanHanhChinh coQuanHanhChinh) {
        this.coQuanHanhChinh = coQuanHanhChinh;
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
        QuyTrinhDonVi quyTrinhDonVi = (QuyTrinhDonVi) o;
        if (quyTrinhDonVi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quyTrinhDonVi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuyTrinhDonVi{" +
            "id=" + getId() +
            ", quyTrinhCode='" + getQuyTrinhCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
