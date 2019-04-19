package com.manager.quanlyquytrinh.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A CoQuanHanhChinh.
 */
@Entity
@Table(name = "co_quan_hanh_chinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CoQuanHanhChinh implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "co_quan_hanh_chinh_code", nullable = false)
    private String coQuanHanhChinhCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "ma_dinh_danh_code", nullable = false)
    private String maDinhDanhCode;

    @NotNull
    @Column(name = "jhi_level", nullable = false)
    private String level;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "coQuanHanhChinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuyTrinhDonVi> quyTrinhDonVis = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoQuanHanhChinhCode() {
        return coQuanHanhChinhCode;
    }

    public CoQuanHanhChinh coQuanHanhChinhCode(String coQuanHanhChinhCode) {
        this.coQuanHanhChinhCode = coQuanHanhChinhCode;
        return this;
    }

    public void setCoQuanHanhChinhCode(String coQuanHanhChinhCode) {
        this.coQuanHanhChinhCode = coQuanHanhChinhCode;
    }

    public String getName() {
        return name;
    }

    public CoQuanHanhChinh name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public CoQuanHanhChinh description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaDinhDanhCode() {
        return maDinhDanhCode;
    }

    public CoQuanHanhChinh maDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
        return this;
    }

    public void setMaDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
    }

    public String getLevel() {
        return level;
    }

    public CoQuanHanhChinh level(String level) {
        this.level = level;
        return this;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public CoQuanHanhChinh status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<QuyTrinhDonVi> getQuyTrinhDonVis() {
        return quyTrinhDonVis;
    }

    public CoQuanHanhChinh quyTrinhDonVis(Set<QuyTrinhDonVi> quyTrinhDonVis) {
        this.quyTrinhDonVis = quyTrinhDonVis;
        return this;
    }

    public CoQuanHanhChinh addQuyTrinhDonVi(QuyTrinhDonVi quyTrinhDonVi) {
        this.quyTrinhDonVis.add(quyTrinhDonVi);
        quyTrinhDonVi.setCoQuanHanhChinh(this);
        return this;
    }

    public CoQuanHanhChinh removeQuyTrinhDonVi(QuyTrinhDonVi quyTrinhDonVi) {
        this.quyTrinhDonVis.remove(quyTrinhDonVi);
        quyTrinhDonVi.setCoQuanHanhChinh(null);
        return this;
    }

    public void setQuyTrinhDonVis(Set<QuyTrinhDonVi> quyTrinhDonVis) {
        this.quyTrinhDonVis = quyTrinhDonVis;
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
        CoQuanHanhChinh coQuanHanhChinh = (CoQuanHanhChinh) o;
        if (coQuanHanhChinh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coQuanHanhChinh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoQuanHanhChinh{" +
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
