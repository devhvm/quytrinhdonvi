package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.QuyTrinhDonVi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the QuyTrinhDonVi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuyTrinhDonViRepository extends JpaRepository<QuyTrinhDonVi, Long> {
    Optional<QuyTrinhDonVi> findByCoQuanHanhChinh_CoQuanHanhChinhCode(String coQuanHanhChinhCode);
}
