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
 * A DuLieuTienTrinh.
 */
@Entity
@Table(name = "du_lieu_tien_trinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DuLieuTienTrinh extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tien_trinh_code", nullable = false)
    private String tienTrinhCode;

    @NotNull
    @Column(name = "du_lieu_code", nullable = false)
    private String duLieuCode;

    @NotNull
    @Column(name = "from_user_id", nullable = false)
    private String fromUserId;

    @NotNull
    @Column(name = "to_user_id", nullable = false)
    private String toUserId;

    @NotNull
    @Column(name = "jhi_level", nullable = false)
    private String level;

    @NotNull
    @Column(name = "note", nullable = false)
    private String note;

    @OneToMany(mappedBy = "quyTrinhDonVi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UyQuyenDuLieu> uyQuyenDuLieus = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("duLieuTienTrinhs")
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

    public DuLieuTienTrinh tienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
        return this;
    }

    public void setTienTrinhCode(String tienTrinhCode) {
        this.tienTrinhCode = tienTrinhCode;
    }

    public String getDuLieuCode() {
        return duLieuCode;
    }

    public DuLieuTienTrinh duLieuCode(String duLieuCode) {
        this.duLieuCode = duLieuCode;
        return this;
    }

    public void setDuLieuCode(String duLieuCode) {
        this.duLieuCode = duLieuCode;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public DuLieuTienTrinh fromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public DuLieuTienTrinh toUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getLevel() {
        return level;
    }

    public DuLieuTienTrinh level(String level) {
        this.level = level;
        return this;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNote() {
        return note;
    }

    public DuLieuTienTrinh note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<UyQuyenDuLieu> getUyQuyenDuLieus() {
        return uyQuyenDuLieus;
    }

    public DuLieuTienTrinh uyQuyenDuLieus(Set<UyQuyenDuLieu> uyQuyenDuLieus) {
        this.uyQuyenDuLieus = uyQuyenDuLieus;
        return this;
    }

    public DuLieuTienTrinh addUyQuyenDuLieu(UyQuyenDuLieu uyQuyenDuLieu) {
        this.uyQuyenDuLieus.add(uyQuyenDuLieu);
        uyQuyenDuLieu.setQuyTrinhDonVi(this);
        return this;
    }

    public DuLieuTienTrinh removeUyQuyenDuLieu(UyQuyenDuLieu uyQuyenDuLieu) {
        this.uyQuyenDuLieus.remove(uyQuyenDuLieu);
        uyQuyenDuLieu.setQuyTrinhDonVi(null);
        return this;
    }

    public void setUyQuyenDuLieus(Set<UyQuyenDuLieu> uyQuyenDuLieus) {
        this.uyQuyenDuLieus = uyQuyenDuLieus;
    }

    public QuyTrinhDonVi getQuyTrinhDonVi() {
        return quyTrinhDonVi;
    }

    public DuLieuTienTrinh quyTrinhDonVi(QuyTrinhDonVi quyTrinhDonVi) {
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
        DuLieuTienTrinh duLieuTienTrinh = (DuLieuTienTrinh) o;
        if (duLieuTienTrinh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), duLieuTienTrinh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DuLieuTienTrinh{" +
            "id=" + getId() +
            ", tienTrinhCode='" + getTienTrinhCode() + "'" +
            ", duLieuCode='" + getDuLieuCode() + "'" +
            ", fromUserId='" + getFromUserId() + "'" +
            ", toUserId='" + getToUserId() + "'" +
            ", level='" + getLevel() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
